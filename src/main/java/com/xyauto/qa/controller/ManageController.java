package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.CommonCons.User_Type_Flag;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.cons.LogAction;
import com.xyauto.qa.cons.ViewKeyCons;
import com.xyauto.qa.core.annotation.QaLog;
import com.xyauto.qa.entity.*;
import com.xyauto.qa.mapper.BrandMapper;
import com.xyauto.qa.mapper.CityMapper;
import com.xyauto.qa.mapper.UserCarMapper;
import com.xyauto.qa.service.*;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.qa.util.TemplateUtils;
import com.xyauto.utils.HttpUtil;
import com.xyauto.utils.enumutil.EnumUtils.ActivityType;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shiqm on 2017/3/23.
 */
@Controller
@RequestMapping("user/manage")
public class ManageController extends AjaxBaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BlocksService blocksService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private SeriesService seriesService;

	@Autowired
	private RUserService rUserService;

	@Autowired
	private QaUserService qaUserService;

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private TemplateUtils templateUtils;
	@Autowired
	private AdminLogsService adminLogsService;

	@Autowired
	private IUserCityService userCityService;
	
	@Autowired
	private UserCarMapper userCarMapper;

	@RequestMapping("expertList")
	public void expertList(ModelMap modelMap, User user, String nickName) {
		if (StringUtils.isNotBlank(nickName)) {
			Long uid = userService.getUserIdByNickName(nickName);
			user.setUid(uid);
			modelMap.put("nickName", nickName);
		}
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXPERT);
		PageInfo pageInfo = userService.getUserList(user);
		modelMap.put("pager", pageInfo);
		modelMap.put("user", user);
	}

	@RequestMapping("normalList")
	public void normalList(ModelMap modelMap, User user, Integer userType,
			String nickName) {
		if (StringUtils.isNotBlank(nickName)) {
			Long uid = userService.getUserIdByNickName(nickName);
			user.setUid(uid);
			modelMap.put("nickName", nickName);
		}
		if (userType != null && userType == 9) {
			user.setIsOfficial(1);
		} else if (userType != null) {
			user.setType(userType);
		}
		// user.setType(CommonCons.User_Type_Flag.USER_TYPE_PERSON);
		PageInfo pageInfo = userService.getUserList(user);
		modelMap.put("pager", pageInfo);
		modelMap.put("user", user);
		modelMap.put("userType", userType);
	}

	@RequestMapping(value = "frozenUser", method = RequestMethod.POST)
	@ResponseBody
	public QaResult frozenUser(User user) {
		userService.frozenExpert(user);
		return new QaResult();
	}

	@QaLog(action = LogAction.SETMODEL_USER, cmd = "setModel")
	@RequestMapping(value = "setModel", method = RequestMethod.POST)
	@ResponseBody
	public QaResult setModel(User user) {
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXAMPLE);
		userService.updateUserType(user);
		return new QaResult();
	}

	@QaLog(action = LogAction.SETEXPERT_USER, cmd = "setExpert")
	@RequestMapping(value = "setExpert", method = RequestMethod.POST)
	@ResponseBody
	public QaResult setExpert(User user) {
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXPERT);
		userService.updateUserType(user);
		return new QaResult();
	}

	@QaLog(action = LogAction.SETADVISER_USER, cmd = "setAdviser")
	@RequestMapping(value = "setAdviser", method = RequestMethod.POST)
	@ResponseBody
	public QaResult setAdviser(User user) {
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_ADVISER);
		userService.updateUserType(user);
		return new QaResult();
	}

	@RequestMapping(value = "updateAdviser", method = RequestMethod.POST)
	@ResponseBody
	public QaResult updateAdviser(User user) {
		try {
			user = userService.get(user.getUid());
			if (user != null
					&& (user.getType() == User_Type_Flag.USER_TYPE_PERSON || user
							.getType() == User_Type_Flag.USER_TYPE_ADVISER)) {
				/**
				 * 获取车顾问信息
				 */
				String appKey = Constants.appKey;
				String appsecret = Constants.appsecret;
				int time = (int) (System.currentTimeMillis() / 1000);
				String encPass = appKey + appsecret + time+user.getUid();
				MessageDigest sha1 = MessageDigest.getInstance("SHA1");
				byte[] sha1Passbytes = sha1.digest(encPass.getBytes());
				String Signature = "";
				if (sha1Passbytes != null) {
					Signature = new Base64().encodeToString(sha1Passbytes);
					Signature = URLEncoder.encode(Signature);
				}
				String url = Constants.url + "?AppKey=" + appKey
						+ "&Signature=" + Signature + "&Timestamp=" + time;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("uid", user.getUid());
				JSONObject result = HttpUtil.doPost(url, jsonObject);
				Integer code = result.getInteger("ErrorCode");
				if (code != null && code == 0) {
					JSONObject resultData = JSONObject.parseObject(result
							.getString("Data"));
					if (StringUtils.isNotBlank(resultData.toJSONString())) {
						// Long brokerID=resultData.getLong("BrokerID");
						String expert_name = resultData.getString("BrokerName");
						// String mobile=resultData.getString("Mobile");
						String expert_avatar = resultData.getString("PhotoUrl");
						String title = resultData.getString("BrokerTypeName");
						String intro = resultData.getString("Introduction");
						Integer dealer_id = resultData.getInteger("DealerID");
						String website = resultData.getString("url");
						Integer city_id = resultData.getInteger("city_id");
						/**
						 * 保存车顾问信息
						 */
						User entity = new User();
						entity.setUid(user.getUid());
						entity.setExpert_name(expert_name);
						entity.setExpert_avatar(expert_avatar);
						entity.setTitle(title);
						entity.setIntro(intro);
						entity.setDealer_id(dealer_id);
						entity.setWebsite(website);
						entity.setType(User_Type_Flag.USER_TYPE_ADVISER);
						userService.updateSelective(entity);
						UserCity ucity = new UserCity();
						ucity.setUid(user.getUid());
						ucity.setCity_id(city_id);
						userCityService.updateSelective(ucity);
						/**
						 * 保存车顾问品牌信息
						 */
						JSONArray serialArray = resultData
								.getJSONArray("serial");
						List<Integer> serialIds = new ArrayList<Integer>();
						for (int i = 0; i < serialArray.size(); i++) {
							JSONObject array = serialArray.getJSONObject(i);
							Integer serialId = array.getInteger("SerialID");
							serialIds.add(serialId);
						}
						//查询品牌信息
						List<Series> seriesList= seriesService.selectListBySerieIds(serialIds);
						if (seriesList==null||seriesList.isEmpty()) {
							logger.info("该用户没有车型信息,用户id是：" + user.getUid());
							return new QaResult();
						}
						List<UserCar> userCarList=new ArrayList<UserCar>();
						for (Series series : seriesList) {
							try {
								UserCar userCar=new UserCar();
								userCar.setUid(user.getUid());
								userCar.setBrandId(series.getBrandId());
								userCar.setSeriesId(series.getSeriesId());
								List<UserCar> oldlist=userCarMapper.select(userCar);
								if (oldlist==null||oldlist.size()==0) {
									userCarList.add(userCar);
								}else {
									logger.info("存在相同数据的内容,用户id:"+user.getUid()+"车系："+userCar.getSeriesId());
								}								
							} catch (Exception e) {
								// TODO: handle exception
								continue;
							}						
						}
						userCarMapper.saveUserCarList(userCarList);
						
					}
				} else if (code != null && code == -10) {
					// -10，经纪人接口查询不出经纪人信息包含该用户不存在的情况
					user.setType(User_Type_Flag.USER_TYPE_PERSON);
					userService.updateSelective(user);
					return new QaResult(ErrorCode.OVER, "该用户无法升级成经纪人");
				} else {
					logger.info("查询经纪人信息失败，参数是uid:" + user.getUid() + " 返回结果是："
							+ result.toJSONString());
					return new QaResult(ErrorCode.OVER);
				}
			} else {
				logger.info("该用户无法成为车顾问，用户信息是：" + user.toString());
				return new QaResult(ErrorCode.OVER);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new QaResult(ErrorCode.OVER);
		}

		return new QaResult();
	}

	@RequestMapping(value = "thawUser", method = RequestMethod.POST)
	@ResponseBody
	public QaResult thawUser(User user) {
		userService.thawUser(user);
		return new QaResult();
	}

	@RequestMapping("expertEdit")
	public void expertEdit(ModelMap modelMap, Long uid) {
		User user = userService.getUserAndInfo(uid);
		modelMap.put("user", user);
//		if (user.getType() == 1 || user.getType() == 2) {
			City usecity = userService.selectCityByuid(uid);
			modelMap.put("usecity", usecity);
			City city = new City();
			city.setParent(0);
			modelMap.put("provinceList", cityMapper.select(city));
			if (usecity != null) {
				city.setParent(usecity.getParent());
				modelMap.put("curCitys", cityMapper.select(city));
			}
//		}
		Category category = new Category();
		category.setParent(0);
		modelMap.put("categoryList", categoryService.get(category));
		modelMap.put("brandList", brandService.selectBrandOrderByName());
		modelMap.put("userTypeMap", ConvertCons.UserTypeCover.getMap());

	}

	@RequestMapping(value = "getSeries", method = RequestMethod.POST)
	@ResponseBody
	public QaResult getSeries(Integer brandId) {
		Series series = new Series();
		series.setBrandId(brandId);
		return new QaResult(seriesService.get(series));
	}

	@RequestMapping(value = "getCitys", method = RequestMethod.POST)
	@ResponseBody
	public QaResult getCitys(Integer provinceId) {
		City city = new City();
		city.setParent(provinceId);
		return new QaResult(cityMapper.select(city));
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public QaResult updateUser(User user, String[] seriesIds,
			String[] categoryIds, Integer cityId) {
		if (categoryIds == null) {
			user.setCategory_ids("");
		} else {
			user.setCategory_ids(org.apache.commons.lang.StringUtils.join(
					categoryIds, ","));
		}
		userService.saveUser(user, seriesIds);
		/**
		 * 用户和城市目前暂定一对一,由于历史遗留问题，每次选择获取最后一条记录
		 */

		UserCity userCity = new UserCity();
		userCity.setUid(user.getUid());
		List<UserCity> list = userCityService.get(userCity);
		userCity.setCity_id(cityId);
		userCity.setCreated_at((int) (new Date().getTime() / 1000));
		userCity.setDeleted_at(0);
		if (!list.isEmpty()) {
			userCity.setUser_city_id(list.get(list.size() - 1)
					.getUser_city_id());
			userCityService.update(userCity);
		} else {
			userCityService.save(userCity);
		}

		return new QaResult();
	}

	@RequestMapping(value = "resetUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public QaResult resetUserInfo(String nickName) {
		if (StringUtils.isEmpty(nickName)) {
			return new QaResult(ErrorCode.OVER, "昵称不能为空");
		}
		String uidStr = rUserService.getUserIdByNickName(nickName);
		if (StringUtils.isEmpty(uidStr) || "0".equals(uidStr)) {
			return new QaResult(ErrorCode.OVER, "ID为空");
		}
		qaUserService.getUserDesc(uidStr);
		return new QaResult(uidStr);
	}

	@RequestMapping("modelList")
	public void modelList(ModelMap modelMap, User user, String nickName) {
		if (StringUtils.isNotBlank(nickName)) {
			Long uid = userService.getUserIdByNickName(nickName);
			user.setUid(uid);
			modelMap.put("nickName", nickName);
		}
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXAMPLE);
		PageInfo pageInfo = userService.getUserList(user);
		modelMap.put("pager", pageInfo);
		modelMap.put("user", user);
	}

	@RequestMapping("specialList")
	public void specialList(ModelMap modelMap) {
		User user = new User();
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXPERT);
		modelMap.put("pager", userService.getUserList(user));
		modelMap.put("specialList", userService
				.getUserByBlock(CommonCons.Blocks_Flag.USER_EXPERT_RECOMMEND));
	}

	@RequestMapping(value = "specialListAssist", method = RequestMethod.POST)
	@ResponseBody
	public QaResult specialListAssist(User user) {
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXPERT);
		System.out.println(user.getExpert_name());
		return new QaResult(templateUtils.parse(
				ViewKeyCons.MANAGE_LIST_SPECIAL, userService.getUserList(user)));
	}

	@RequestMapping(value = "modelListAssist", method = RequestMethod.POST)
	@ResponseBody
	public QaResult modelListAssist(User user, String nickName) {
		if (StringUtils.isNotBlank(nickName)) {
			Long uid = userService.getUserIdByNickName(nickName);
			user.setUid(uid);
		}
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXAMPLE);
		return new QaResult(templateUtils.parse(
				ViewKeyCons.MANAGE_LIST_SPECIAL_MODEL,
				userService.getUserList(user)));
	}

	@RequestMapping(value = "saveSpecial", method = RequestMethod.POST)
	@ResponseBody
	public QaResult saveSpecial(String target, String content) {
		logger.info("人员保存内容:" + content);
		JSONArray jsonArray = JSON.parseArray(content);
		logger.info("转换:" + jsonArray.toString());
		if (StringUtils.isEmpty(target)) {
			return new QaResult(ErrorCode.OVER, "类型不能为空");
		}
		Blocks blocks = new Blocks();
		if ("special".equals(target)) {
			blocks.setName(CommonCons.Blocks_Flag.USER_EXPERT_RECOMMEND);
		} else if ("specialExpert".equals(target)) {
			blocks.setName(CommonCons.Blocks_Flag.USER_EXPERT_BEST);
		} else if ("specialModel".equals(target)) {
			blocks.setName(CommonCons.Blocks_Flag.USER_MODEL_BEST);
		} else {
			return new QaResult(ErrorCode.OVER, "不存在的类型");
		}

		List<Blocks> list = blocksService.get(blocks);
		if (list.get(0) == null) {
			blocks.setContent(content);
			blocksService.save(blocks);
		} else {
			blocks = list.get(0);
			blocks.setContent(content);
			blocksService.update(blocks);
		}
		return new QaResult();
	}

	@RequestMapping("specialExpertList")
	public void specialExpertList(ModelMap modelMap, User user) {
		if ("".equals(user.getExpert_name())) {
			user.setExpert_name(null);
		}
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXPERT);
		modelMap.put("pager", userService.getUserList(user));
		modelMap.put("specialList", userService
				.getUserByBlock(CommonCons.Blocks_Flag.USER_EXPERT_BEST));
		modelMap.put("user", user);
	}

	@RequestMapping("specialModelList")
	public void specialModelList(ModelMap modelMap, User user) {
		if ("".equals(user.getExpert_name())) {
			user.setExpert_name(null);
		}
		user.setType(CommonCons.User_Type_Flag.USER_TYPE_EXAMPLE);
		modelMap.put("pager", userService.getUserList(user));
		modelMap.put("specialList", userService
				.getUserByBlock(CommonCons.Blocks_Flag.USER_MODEL_BEST));
		modelMap.put("user", user);
	}

	/**
	 * 获取模糊查询品牌
	 *
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "findBrand", method = RequestMethod.POST)
	@ResponseBody
	public Object findBrand(String name) {
		List list = brandMapper.findBrand(name);
		return list;
	}

	@RequestMapping("frozenUserList")
	public void selectfrozenUser(User user, ModelMap map) {
		user.setStatus(-2);
		PageInfo<User> userList = userService.getUserList(user);
		for (User user2 : userList.getList()) {
			// 查询封禁人和时间
			AdminLogs adminLogs = adminLogsService.selectfrozenUser(user2
					.getUid());
			if (adminLogs != null) {
				user2.setForbbiden_time(adminLogs.getCreated_at());
				user2.setForbbiden_user_name(adminLogs.getUsername());
			}
		}
		map.put("pager", userList);
	}

	@RequestMapping("setEnableDistribute")
	@ResponseBody
	public QaResult setEnableDistribute(User user) {
		if (user.getEnableDistribute() == 0) {
			qaUserService.delDistribute(user.getUid());
		} else {
			qaUserService.addDistribute(user.getUid().toString());
		}
		userService.updateSelective(user);
		return new QaResult();
	}
	@RequestMapping("activeUserList")
	public void activeUser(UserApply userApply,ModelMap map){
		if (userApply==null||userApply.getStatus()==null) {
			userApply.setStatus((short)0);
		}
		PageInfo<UserApply> pageInfo=userService.selectActiveUserList(userApply);
		map.put("statusMap", ActivityType.getMap());
		map.put("userApply", userApply);
		map.put("pager", pageInfo);
	}

	@RequestMapping("activeUser")
	public void toActiveUser(Long id,ModelMap map){
		UserApply userApply=userService.selectActiveUser(id);
		String[] images=userApply.getIdentityImages().split(",");
		map.put("images", images);
		map.put("userApply", userApply);
		map.put("statusMap", ActivityType.getMap());
	}
	
	@RequestMapping(value="updateActiveUser",method=RequestMethod.POST)
	@ResponseBody
	public QaResult updateActiveUser(UserApply userApply){
		userService.updateActiveUser(userApply);
		return new QaResult();
	}
	
	@ModelAttribute("urlObj")
	public JSONObject getUrlObj() {
		return super.getUrlObj();
	}

}
