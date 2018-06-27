package com.xyauto.qa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.CommonCons.User_Type_Flag;
import com.xyauto.qa.entity.City;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.entity.UserApply;
import com.xyauto.qa.entity.UserCar;
import com.xyauto.qa.exceptions.UpdateFailedException;
import com.xyauto.qa.mapper.CategoryMapper;
import com.xyauto.qa.mapper.CityMapper;
import com.xyauto.qa.mapper.UserApplyMapper;
import com.xyauto.qa.mapper.UserCarMapper;
import com.xyauto.qa.mapper.UserMapper;
import com.xyauto.qa.service.BlocksService;
import com.xyauto.qa.service.CategoryService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.utils.cache.RedisKey.AnswerEnum;
import com.xyauto.utils.cache.RedisKey.QuestionEnum;
import com.xyauto.utils.cache.RedisKeyUtil;
import com.xyauto.utils.cache.RedisKeyUtil.Model;
import com.xyauto.utils.cache.service.ICacheService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by shiqm on 2017/3/23.
 */
@Service
public class UserServiceImpl extends ABaseServiceImpl<User, Long> implements
		UserService {

	@Autowired
	private RUserService rUserService;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BlocksService blocksService;

	@Autowired
	private UserCarMapper userCarMapper;

	@Autowired
	private QaUserService qaUserService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private UserApplyMapper userApplyMapper;

	private JSONArray obj;

	@Override
	public PageInfo getUserList(User user) {
		if ("".equals(user.getExpert_name())) {
			user.setExpert_name(null);
		}
		PageInfo pageInfo = this.findByPager(user);
		// 获取列表后需要从User服务获取用户信息
		List<User> users = pageInfo.getList();
		for (int i = 0; i < users.size(); i++) {
			User tempUser = users.get(i);
			Long tempUid = tempUser.getUid();
			JSONObject tempRes = JSON.parseObject(rUserService
					.getUserInfo(String.valueOf(tempUid)));
			logger.info("获取用户信息,code:{},：" + tempRes.toString(),
					tempRes.getInteger("code"));
			if (CommonCons.Cloud_User_Flag.CODE_OK.equals(tempRes
					.getInteger("code"))) {
				tempUser.setUserInfo(tempRes);
			}
			if (!StringUtils.isEmpty(tempUser.getCategory_ids())) {
				tempUser.setCategories(categoryMapper.getCategoryByIds(tempUser
						.getCategory_ids()));
			}

		}
		return pageInfo;
	}

	@Override
	@Transactional
	public int frozenExpert(User user) {
		user.setStatus(-1);
		int i = userMapper.updateUserStatus(user);
		if (i != 1) {
			throw new UpdateFailedException("冻结专家时失败");
		}
		return i;
	}

	@Override
	@Transactional
	public int thawUser(User user) {
		user.setStatus(1);
		int i = userMapper.updateUserStatus(user);
		if (i != 1) {
			throw new UpdateFailedException("恢复用户状态失败");
		}
		return i;
	}

	@Override
	public User getUserAndInfo(Long uid) {
		User user = userMapper.getUserBrandsById(uid);
		if (user == null) {
			user = new User();
		}
		JSONObject tempRes = JSON.parseObject(rUserService.getUserInfo(String
				.valueOf(uid)));
		logger.info("获取用户信息,code:{},：" + tempRes.toString(),
				tempRes.getInteger("code"));
		if (CommonCons.Cloud_User_Flag.CODE_OK.equals(tempRes
				.getInteger("code"))) {
			user.setUserInfo(tempRes);
		}
		if (!StringUtils.isEmpty(user.getCategory_ids())) {
			user.setCategories(categoryMapper.getCategoryByIds(user
					.getCategory_ids()));
		}
		return user;
	}

	@Override
	public List<User> getUserByBlock(String name) {
		JSONArray jsonArray = blocksService.getBlockByName(name);
		List<User> list = new ArrayList<>();
		for (Object uid : jsonArray.toArray()) {
			list.add(this.getUserAndInfo(Long.valueOf(String.valueOf(uid))));
		}
		return list;
	}

	@Override
	@Transactional
	public void saveUser(User user, String[] seriesIds) {

		if (userMapper.updateUser(user) != 1) {
			throw new UpdateFailedException("用户更新失败");
		}
		List<UserCar> userCarsList = userCarMapper.getAllByUid(user.getUid());
		logger.info("==========================");
		logger.info(Arrays.toString(seriesIds));
		logger.info("==========================");
		if (seriesIds == null) {
			logger.info("没有收到车系ID，后台删除擅长的车系");
			if (userCarsList.size() > 0) {
				for (int i = 0; i < userCarsList.size(); i++) {
					UserCar tempUserCar = userCarsList.get(i);
					logger.info("清空删除擅长的车系：{}", tempUserCar.getUserCarId());
					userCarMapper.deleteById(tempUserCar.getUserCarId());
				}
			}
		} else {
			logger.info("开始比对更新车系");
			// 取出需要增加的
			List<String> addList = new ArrayList<>();
			// 取新的擅长车系
			for (String seriesIdAndBrandId : seriesIds) {
				String tempSeriesId = seriesIdAndBrandId.split("\\_")[0];
				String tempBrandId = seriesIdAndBrandId.split("\\_")[1];
				logger.info("前台的车系{}", tempSeriesId);
				boolean saveIs = false;
				for (int i = 0; i < userCarsList.size(); i++) {
					UserCar tempUserCar = userCarsList.get(i);
					logger.info("现有的车系{}", tempUserCar.getSeriesId());
					if (tempSeriesId.equals(String.valueOf(tempUserCar
							.getSeriesId()))
							&& tempBrandId.equals(String.valueOf(tempUserCar
									.getBrandId()))) {
						logger.info("比对拥有的车系:{}", tempSeriesId);
						saveIs = true;
						break;
					}
				}
				// 不存在的需要增加
				if (!saveIs) {
					logger.info("需要新添的车系:{}", tempSeriesId);
					addList.add(seriesIdAndBrandId);
				}
			}
			// 保存新创建的
			for (String seriesIdAndBrandId : addList) {
				UserCar userCar = new UserCar();
				userCar.setUid(user.getUid());
				logger.info(seriesIdAndBrandId);
				userCar.setSeriesId(Integer.valueOf(seriesIdAndBrandId
						.split("\\_")[0]));
				userCar.setBrandId(Integer.valueOf(seriesIdAndBrandId
						.split("\\_")[1]));
				logger.info("正在保存车系:{}", seriesIdAndBrandId);
				if (userCarMapper.saveUserCar(userCar) != 1) {
					throw new UpdateFailedException("保存新创建的车系失败");
				}
			}
			// 取出需要删除的的
			List<Long> delList = new ArrayList<>();
			// 删除存在的
			for (int i = 0; i < userCarsList.size(); i++) {
				UserCar tempUserCar = userCarsList.get(i);
				boolean deleteIs = false;
				for (String seriesIdAndBrandId : seriesIds) {
					String tempSeriesId = seriesIdAndBrandId.split("\\_")[0];
					String tempBrandId = seriesIdAndBrandId.split("\\_")[1];
					if (tempSeriesId.equals(String.valueOf(tempUserCar
							.getSeriesId()))
							&& tempBrandId.equals(String.valueOf(tempUserCar
									.getBrandId()))) {
						deleteIs = true;
						break;
					}
				}
				if (!deleteIs) {
					logger.info("需要删除的车系:{}", tempUserCar.getUserCarId());
					delList.add(tempUserCar.getUserCarId());
				}
			}
			// 保存新创建的
			for (Long tempUserCarId : delList) {
				logger.info("正在删除车系:{}", tempUserCarId);
				if (userCarMapper.deleteById(tempUserCarId) != 1) {
					throw new UpdateFailedException("删除旧的车系失败");
				}
			}
		}
	}

	@Override
	@Transactional
	public void updateUserType(User user) {
		if (user.getUid() == null) {
			throw new UpdateFailedException("用户ID不能为空");
		}
		if (userMapper.updateUserType(user) != 1) {
			throw new UpdateFailedException("更新用户类型失败");
		}
	}

	@Override
	@Transactional
	public int frozenUserAndExpert(User user, boolean is) {
		if (is) {
			user.setStatus(-2);
		} else {
			user.setStatus(1);
		}
		int i = userMapper.updateUserStatus(user);
		if (i != 1) {
			throw new UpdateFailedException("封禁用户失败");
		}
		return i;
	}

	@Override
	public Long getUserIdByNickName(String nickName) {
		String uid = null;
		if (StringUtils.isNotBlank(nickName)) {
			uid = rUserService.getUserIdByNickName(nickName);
		}
		// 先检查该用户是否在用户表中，没有则先插入
		if (StringUtils.isNotBlank(uid)) {
			User user2 = this.get(Long.parseLong(uid));
			if (user2 == null) {
				qaUserService.getUserDesc(uid);
			}
			return Long.parseLong(uid);
		}
		return null;
	}

	@Override
	public List<User> selectUserByQueCountOrAnsCount(String startTime,
			String endTime, String type) {
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if (type.equalsIgnoreCase("answer")) {
			return userMapper.selectUserByAnswercount(map);
		} else {
			return userMapper.selectUserByQuestioncount(map);
		}

	}

	@Override
	public Map<Long, String> selectNickName(String uids) {
		String str = rUserService.getUserNameList(uids);
		Map<Long, String> map = new HashMap<Long, String>();
		org.json.JSONArray jsonArray = new org.json.JSONArray(str);
		for (int i = 0; i < jsonArray.length(); i++) {
			org.json.JSONObject obj = jsonArray.getJSONObject(i);
			String nickName;
			if (StringUtils.isBlank(obj.getString("nick_name"))) {
				nickName = "未知";
			} else {
				nickName = obj.getString("nick_name");
			}
			map.put(obj.getLong("user_id"), nickName);
		}
		return map;
	}

	@Override
	public int frozenUserAndExpert(Long uid, Integer time, Boolean delQue,
			Boolean delAns, String content) {
		User user = new User();
		user.setUid(uid);
		user.setStatus(-2);
		user.setForbbiden_reason(content);
		if (time!=null&&time==0) {
			user.setReleased_at(0);
		}else{
			Date date = DateUtils.beforDate(new Date(), -time);
			user.setReleased_at((int) (date.getTime() / 1000));
		}		
		int i = userMapper.updateUserStatus(user);
		if (i != 1) {
			throw new UpdateFailedException("封禁用户失败");
		}
		// 封禁成功删除问题和回复
		String key = null;
		if (delQue) {

			key = RedisKeyUtil.getKey(Model.question,
					QuestionEnum.del_question_uid.getKey());
			 
			List<Long> uidList = (List<Long>) cacheService.getObject(key);
			if (uidList==null) {
				 uidList=new ArrayList<Long>();
			}
			uidList.add(uid);
			cacheService.set(key, uidList);

		}
		if (delAns) {
			key = RedisKeyUtil.getKey(Model.answer,
					AnswerEnum.del_answer_uid.getKey());
			List<Long> uidList = (List<Long>) cacheService.getObject(key);
			if (uidList==null) {
				 uidList=new ArrayList<Long>();
			}
			uidList.add(uid);
			cacheService.set(key, uidList);
		}
		return 0;
	}

	@Override
	public int recoverUser() {
		
		return userMapper.recoverUser();
	}

	@Override
	public City selectCityByuid(Long uid) {
		List<City> list=cityMapper.selectCityByuid(uid);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int updateQuestionCount(Long uid) {
		return userMapper.updateQuestionCount( uid);
	}

	@Override
	public int updateAnswerCount(Long uid) {
		// TODO Auto-generated method stub
		return userMapper.updateAnswerCount(uid);
	}

	@Override
	public List<User> selectAviser() {
		// TODO Auto-generated method stub
		return userMapper.selectAviser();
	}

	@Override
	public void updateUserCar(List<Series> seriesList, Long uid) {
		List<UserCar> userCarList = new ArrayList<UserCar>();
		UserCar userCar = new UserCar();
		userCar.setUid(uid);
		//获取用户所有的当前对应的车型
		List<UserCar> oldlist = userCarMapper
				.select(userCar);
		//获取需要删除的车型数据
		List<UserCar> delList = oldlist.stream().filter(u->{
		    return seriesList.stream().filter(e->u.getSeriesId()==e.getSeriesId()).count()<=0;
		}).collect(Collectors.toList());

		//获取需要添加的数据
		List<Series> addList=seriesList.stream().filter(s->{
			return oldlist.stream().filter(u->u.getSeriesId()==s.getSeriesId()).count()<=0;
		}).collect(Collectors.toList());
		//删除数据
        for (UserCar userCar2 : delList) {
        	userCarMapper.delete(userCar2);
		}
      //添加数据
		for (Series series : addList) {
			UserCar adduserCar = new UserCar();
			adduserCar.setUid(uid);
			adduserCar.setBrandId(series.getBrandId());
			adduserCar.setSeriesId(series.getSeriesId());
			userCarList.add(adduserCar);
		}		
		if (!userCarList.isEmpty()) {
			userCarMapper.saveUserCarList(userCarList);
		}
	}

	@Override
	public List<Long> getUserIdByType(Object[] array, int userType) {
		// TODO Auto-generated method stub
		return userMapper.getUserIdByType(array, userType);
	}

	@Override
	public PageInfo<UserApply> selectActiveUserList(UserApply userApply) {
		// TODO Auto-generated method stub
		PageInfo<UserApply> pageInfo = PageHelper.startPage(
				userApply.getPageNum(), userApply.getPageSize())
				.doSelectPageInfo(
						() -> userApplyMapper.selectActiveUserList(userApply));
		return pageInfo;
	}

	@Override
	public UserApply selectActiveUser(Long id) {
		// TODO Auto-generated method stub
		if (id==null) {
			return null;
		}
		return userApplyMapper.selectActiveUser(id);
	}

	@Override
	public void updateActiveUser(UserApply userApply) {
		userApplyMapper.updateActiveUser(userApply);		
	}
	

}
