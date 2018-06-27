package com.xyauto.qa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.entity.*;
import com.xyauto.qa.exceptions.UpdateFailedException;
import com.xyauto.qa.mapper.*;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QuestionUtil;
import com.xyauto.utils.excel.ExcelUtils;
import com.xyauto.utils.excel.ExcelVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by shiqm on 2017/3/28.
 */
@Service
public class QuestionServiceImpl extends ABaseServiceImpl<Question, Long>
		implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private QuestionCarMapper questionCarMapper;

	@Autowired
	private SeriesMapper seriesMapper;
	
	@Autowired
    private Constants constants;

	@Override
	public PageInfo<Question> getQuestionList(Question question) {
		PageInfo pageInfo = PageHelper.startPage(question.getPageNum(),
				question.getPageSize()).doSelectPageInfo(
				() -> questionMapper.getQuestionList(question));
		List<Question> list = pageInfo.getList();
		for (Question temp : list) {
			User user = userService.getUserAndInfo(temp.getUid());
			temp.setUser(user);
			List<Attach> attachList = temp.getAttaches();
			for (Attach attach : attachList) {
				if (attach.getFileType() == 0) {
					String pathUrl = attach.getFile();
					if (pathUrl.toLowerCase().startsWith("http://")
							|| pathUrl.toLowerCase().startsWith("https://")) {
					}
					if (pathUrl.startsWith("group1")) {
						pathUrl = constants.getAvatarGroup1Root() + pathUrl;
					}
					if (pathUrl.startsWith("group2")) {
						pathUrl = constants.getAvatarGroup2Root() + pathUrl;
					}
					attach.setFile(pathUrl);
				}
			}
		}
		return pageInfo;
	}

	@Override
	@Transactional
	public void updateGood(Question question, boolean is) {
		if (question.getQuestionId() == null) {
			throw new UpdateFailedException("问题ID不能为空");
		}
		if (is) {
			question.setIsGood(1);
		} else {
			question.setIsGood(0);
		}

		if (questionMapper.updateGood(question) != 1) {
			throw new UpdateFailedException("更新问题精华失败");
		}
	}

	@Override
	@Transactional
	public void updateTop(Question question, boolean is) {
		if (question.getQuestionId() == null) {
			throw new UpdateFailedException("问题ID不能为空");
		}
		if (is) {
			question.setIsTop(1);
			if (question.getTopSort() == null || question.getTopSort() <= 0) {
				throw new UpdateFailedException("置顶权重不能为Null");
			}
		} else {
			question.setIsTop(0);
			question.setTopSort(0);
		}
		if (questionMapper.updateTop(question) != 1) {
			throw new UpdateFailedException("更新问题置顶失败");
		}
	}

	@Override
	@Transactional
	public void deleteQuestionById(Long questionId) {
		if (questionId == null) {
			throw new UpdateFailedException("问题ID不能为空");
		}
		if (questionMapper.deleteQuestionById(questionId) != 1) {
			throw new UpdateFailedException("删除问题失败");
		}
	}

	@Override
	public PageInfo<Question> getDelQuestionList(Question question) {
		PageInfo pageInfo = PageHelper.startPage(question.getPageNum(),
				question.getPageSize()).doSelectPageInfo(
				() -> questionMapper.getDelQuestionList(question));
		List<Question> list = pageInfo.getList();
		for (Question temp : list) {
			User user = userService.getUserAndInfo(temp.getUid());
			temp.setUser(user);
			List<Attach> attachList = temp.getAttaches();
			for (Attach attach : attachList) {
				if (attach.getType() == 0) {
					String pathUrl = attach.getFile();
					if (pathUrl.toLowerCase().startsWith("http://")
							|| pathUrl.toLowerCase().startsWith("https://")) {
					}
					if (pathUrl.startsWith("group1")) {
						pathUrl = constants.getAvatarGroup1Root() + pathUrl;
					}
					if (pathUrl.startsWith("group2")) {
						pathUrl = constants.getAvatarGroup2Root() + pathUrl;
					}
					attach.setFile(pathUrl);
				}
			}
		}
		return pageInfo;
	}

	@Override
	@Transactional
	public void recoveryQuestionById(Long questionId) {
		if (questionId == null) {
			throw new UpdateFailedException("问题ID不能为空");
		}
		if (questionMapper.recoveryQuestionById(questionId) != 1) {
			throw new UpdateFailedException("恢复问题失败");
		}
	}

	@Override
	@Transactional
	public void verifyQuestion(Long questionId) {
		if (questionId == null) {
			throw new UpdateFailedException("问题ID不能为空");
		}
		if (questionMapper.verifyQuestionById(questionId) != 1) {
			throw new UpdateFailedException("审核问题失败");
		}
	}

	@Override
	public PageInfo<Question> getQuestionListByMapParam(Question question,
			String keyWord, String nickName, Integer userType, Brand brand,
			Series series, Integer statue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", question.getUid());
		map.put("questionId", question.getQuestionId());
		map.put("categoryId", question.getCategoryId());
		map.put("hasAttach", question.getHasAttach());
		map.put("isGood", question.getIsGood());
		map.put("isTop", question.getIsTop());
		map.put("cityId", question.getCityId());
		map.put("startTime", question.getCreatedAt());
		map.put("endTime", question.getUpdatedAt());
		map.put("answerCount", question.getAnswerCount());
		map.put("key", question.getKey());
		map.put("source", question.getSource());
		map.put("deletedSelf", question.getDeletedSelf());
		map.put("expert_name", nickName);
		if (brand != null) {
			map.put("brandId", brand.getBrandId());
		}
		if (series != null) {
			map.put("seriesId", series.getSeriesId());
		}
		map.put("keyWord", keyWord);
		if (userType != null && userType == 9) {
			map.put("isOfficial", userType);
		} else {
			map.put("userType", userType);
		}

		if (statue == CommonCons.Quesiotn_Flag.DELETE_QUESTION) {
			return this.getDelQuestionListByMapParam(question, map);
		}
		if (statue == CommonCons.Quesiotn_Flag.REVIEW_QUESTION) {
			if (question.getStatus() == null || question.getStatus() == 0) {
				map.put("status", -1);
			} else {
				map.put("status", question.getStatus());
			}
			return this.getQuestionListByMapParam(question, map);
		}
		if (statue == CommonCons.Quesiotn_Flag.RUBBISH_QUESTION) {
			return null;
		}
		return this.getQuestionListByMapParam(question, map);
	}

	private PageInfo<Question> getQuestionListByMapParam(Question question,
			Map map) {
		int total = questionMapper.getQuestionCountByMapParam(map);
		if (total <= 0) {
			return null;
		}
		/*PageInfo<Question> pageInfo = PageHelper.startPage(
				question.getPageNum(), question.getPageSize())
				.doSelectPageInfo(
						() -> questionMapper.getQuestionListByMapParam(map));*/
		
		PageInfo<Question> pageInfo =new PageInfo<Question>();
		int num=question.getPageNum();
		int size=question.getPageSize();
		List<Question> list=questionMapper.getQuestionListByMapParam(map);
		if (list!=null&&list.size()>0) {
			int endNum=num*size;
			if (endNum>list.size()) {
				endNum=list.size();
			}
			pageInfo.setList(list.subList((num-1)*size, endNum));
			pageInfo.setTotal(list.size());
			int pages=list.size()/size;
			if (list.size()%size>0) {
				pages++;
			}
			pageInfo.setPages(pages);
		}		
		pageInfo.setPageNum(num);
		pageInfo.setPageSize(size);
		
		return this.getQuestionListInfo(pageInfo);
	}

	public PageInfo<Question> getDelQuestionListByMapParam(Question question,
			Map<String, Object> map) {

		List<Question> list = questionMapper.getDelQuestionListByMapParam(map);
		if (list == null || list.size() <= 0) {
			return null;
		}
		PageInfo<Question> pageInfo = PageHelper.startPage(
				question.getPageNum(), question.getPageSize())
				.doSelectPageInfo(
						() -> questionMapper.getDelQuestionListByMapParam(map));
		pageInfo.setTotal(list.size());
		return this.getQuestionListInfo(pageInfo);
	}

	private PageInfo<Question> getQuestionListInfo(PageInfo<Question> pageInfo) {
		List<Question> list = (List<Question>) pageInfo.getList();
		for (Question temp : list) {			
			User user = userService.getUserAndInfo(temp.getUid());
			// User user=new User();
			temp.setUser(user);
			List<Attach> attachList = temp.getAttaches();
			for (Attach attach : attachList) {
				if (attach.getFileType() == 0) {
					String pathUrl = attach.getFile();
					if (pathUrl.toLowerCase().startsWith("http://")
							|| pathUrl.toLowerCase().startsWith("https://")) {
					}
					if (pathUrl.startsWith("group1")) {
						pathUrl = constants.getAvatarGroup1Root() + pathUrl;
					}
					if (pathUrl.startsWith("group2")) {
						pathUrl = constants.getAvatarGroup2Root() + pathUrl;
					}
					attach.setFile(pathUrl);
				}
			}
			//处理图文混排的帖子
			if (temp.getIs_html()==1||temp.getSource()==103) {
				List<String> imgList = QuestionUtil.getImageSrc(temp.getContent());
				temp.setContent(QuestionUtil.removeHtmlTag(temp.getContent()));
				if (StringUtils.isNotBlank(temp.getCover())) {
					imgList.add(temp.getCover());
				}				
				for (String string : imgList) {
					Attach attach =new Attach();
					attach.setFileType(0);
					attach.setFile(string);
					attachList.add(attach);
				}
			}
		}
		return pageInfo;
	}

	@Override
	public List<Question> selectQuestionByAnswercount(String startTime,
			String endTime) {
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			logger.info("参数有误");
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return questionMapper.selectQuestionByAnswercount(map);
	}

	@Override
	public Question getQuestionDesc(Long questionId) {
		Question question = questionMapper.getQuestionDesc(questionId);
		// 获取用户详情
		User user = userService.getUserAndInfo(question.getUid());
		question.setUser(user);
		// 转换附件路径
		this.transAttachList(question.getAttaches());
		return question;
	}

	@Override
	@Transactional
	public void updateQuestion(Question question, String[] attachs,
			String[] seriesIds) {
		if (questionMapper.updateQuestion(question) != 1) {
			throw new UpdateFailedException("问题更新失败");
		}
		Question _temp = this.getQuestionDesc(question.getQuestionId());
		List attachList = _temp.getAttaches();
		// 处理修改的附件图片
		logger.info("=============前台图片=============");
		logger.info(Arrays.toString(attachs));
		if (attachs == null) {
			logger.info("没有收到图片信息，后台删除所有图片");
			for (int i = 0; i < attachList.size(); i++) {
				Attach attach = (Attach) attachList.get(i);
				logger.info("删除图片：{}", attach.getAttachId());
				attachMapper.deleteById(attach.getAttachId());
			}
		} else {
			logger.info("开始比对更新附件");
			// 取出需要增加的
			List<String> addList = new ArrayList<>();
			// 取新的附件
			for (String attachFile : attachs) {
				logger.info("前台的附件{}", attachFile);
				boolean saveIs = false;
				for (int i = 0; i < attachList.size(); i++) {
					Attach attach = (Attach) attachList.get(i);
					if (attach.getFileType() == 0) {
						logger.info("现有的附件{}", attach.getFile());
						if (attachFile.equals(attach.getFile())) {
							logger.info("比对拥有的附件:{}", attachFile);
							saveIs = true;
							break;
						}
					}
				}
				// 不存在的需要增加
				if (!saveIs) {
					logger.info("需要新添的附件:{}", attachFile);
					addList.add(attachFile);
				}
			}
			// 保存新创建的
			for (String attachFile : addList) {
				Attach saveAttach = new Attach();
				saveAttach.setTargetId(_temp.getQuestionId());
				saveAttach.setFile(attachFile);
				logger.info("正在保存图片{}", attachFile);
				if (attachMapper.saveAttachPicture(saveAttach) != 1) {
					throw new UpdateFailedException("保存新创建的图片失败");
				}
			}
			// 取出需要删除的的
			List<Long> delList = new ArrayList<>();
			// 删除存在的
			for (int i = 0; i < attachList.size(); i++) {
				Attach attach = (Attach) attachList.get(i);
				if (attach.getFileType() == 0) {
					boolean deleteIs = false;
					for (String attachFile : attachs) {
						if (attachFile.equals(String.valueOf(attach.getFile()))) {
							deleteIs = true;
							break;
						}
					}
					if (!deleteIs) {
						logger.info("需要删除的图片:{}", attach.getFile());
						delList.add(attach.getAttachId());
					}
				}

			}
			// 保存新创建的
			for (Long tempAttachId : delList) {
				logger.info("正在删除图片:{}", tempAttachId);
				if (attachMapper.deleteById(tempAttachId) != 1) {
					throw new UpdateFailedException("删除旧的图片失败");
				}
			}
		}

		// 更新是hasAttach,注意有除图片外的类型
		if (attachList.size() == 0) {
			if (attachs == null || attachs.length == 0) {
				// 保持原状态
			} else {
				// 有附件
				question.setHasAttach(1);
				questionMapper.updateHasAttach(question);
			}
		} else {
			if (attachs == null || attachs.length == 0) {
				// 无附件
				question.setHasAttach(0);
				questionMapper.updateHasAttach(question);
			} else {
				// 保持原状态
			}
		}

		QuestionCar questionCar = new QuestionCar();
		questionCar.setQuestionId(question.getQuestionId());
		List<QuestionCar> questionCarList = questionCarMapper
				.select(questionCar);
		logger.info("============前台车系==============");
		logger.info(Arrays.toString(seriesIds));
		logger.info("==========================");
		if (seriesIds == null) {
			logger.info("没有收到车系ID，后台删除擅长的车系");
			if (questionCarList.size() > 0) {
				for (int i = 0; i < questionCarList.size(); i++) {
					QuestionCar tempQuestionCar = questionCarList.get(i);
					logger.info("清空删除擅长的车系：{}",
							tempQuestionCar.getQuestionCarId());
					questionCarMapper.delete(tempQuestionCar);
				}
			}
		} else {
			logger.info("开始比对更新车系");
			// 取出需要增加的
			List<String> addList = new ArrayList<>();
			// 取新的擅长车系
			for (String tempSeriesId : seriesIds) {
				logger.info("前台的车系{}", tempSeriesId);
				boolean saveIs = false;
				for (int i = 0; i < questionCarList.size(); i++) {
					QuestionCar tempQuestionCar = questionCarList.get(i);
					logger.info("现有的车系{}", tempQuestionCar.getSeriesId());
					if (tempSeriesId.equals(String.valueOf(tempQuestionCar
							.getSeriesId()))) {
						logger.info("比对拥有的车系:{}", tempSeriesId);
						saveIs = true;
						break;
					}
				}
				// 不存在的需要增加
				if (!saveIs) {
					logger.info("需要新添的车系:{}", tempSeriesId);
					addList.add(tempSeriesId);
				}
			}
			// 保存新创建的
			for (String seriesId : addList) {
				Series tempSeries = seriesMapper.selectByPrimaryKey(Integer
						.valueOf(seriesId));
				QuestionCar saveQuestionCar = new QuestionCar();
				saveQuestionCar.setQuestionId(question.getQuestionId());
				saveQuestionCar.setSeriesId(tempSeries.getSeriesId());
				saveQuestionCar.setBrandId(tempSeries.getBrandId());
				saveQuestionCar.setSubbrandId(tempSeries.getSubbrandId());
				logger.info("正在保存车系:{}", seriesId);
				if (questionCarMapper.saveQuestionCar(saveQuestionCar) != 1) {
					throw new UpdateFailedException("保存新创建的车系失败");
				}
			}
			// 取出需要删除的的
			List<Long> delList = new ArrayList<>();
			// 删除存在的
			for (int i = 0; i < questionCarList.size(); i++) {
				QuestionCar tempQuestionCar = questionCarList.get(i);
				boolean deleteIs = false;
				for (String tempSeriesId : seriesIds) {
					if (tempSeriesId.equals(String.valueOf(tempQuestionCar
							.getSeriesId()))) {
						deleteIs = true;
						break;
					}
				}
				if (!deleteIs) {
					logger.info("需要删除的车系:{}",
							tempQuestionCar.getQuestionCarId());
					delList.add(tempQuestionCar.getQuestionCarId());
				}
			}
			// 保存新创建的
			for (Long questionCarId : delList) {
				logger.info("正在删除车系:{}", questionCarId);
				if (questionCarMapper.deleteByPrimaryKey(questionCarId) != 1) {
					throw new UpdateFailedException("删除旧的车系失败");
				}
			}
		}

	}

	public void transAttachList(List<Attach> attachList) {
		if (attachList == null) {
			return;
		}
		for (Attach attach : attachList) {
			if (attach.getFileType() == 0) {
				String pathUrl = attach.getFile();
				if (pathUrl.toLowerCase().startsWith("http://")
						|| pathUrl.toLowerCase().startsWith("https://")) {
				}
				if (pathUrl.startsWith("group1")) {
					pathUrl = constants.getAvatarGroup1Root() + pathUrl;
				}
				if (pathUrl.startsWith("group2")) {
					pathUrl = constants.getAvatarGroup2Root() + pathUrl;
				}
				attach.setFile(pathUrl);
			}
		}
	}

	@Override
	public void exportQuestionListByMapParam(HttpServletResponse response,
			Map<String, Object> map) {
		List<Question> list = questionMapper.exportQuestionListByMapParam(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName = "问题相关数据.xls";
		String[] headers = new String[] { "时间", "昵称", "用户id", "问题编号", "回复数",
				"类型", "问题详情" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		StringBuffer buffer = new StringBuffer();
		for (Question question : list) {
			buffer.append(question.getUid()).append(",");
		}
		String ids = buffer.toString().substring(0, buffer.length() - 1);
		Map<Long, String> userMap = userService.selectNickName(ids);
		for (Question bi : list) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(bi.getCreatedAt());
			if (StringUtils.isBlank(userMap.get(bi.getUid()))) {
				objs[1] = "未知";
			} else {
				objs[1] = userMap.get(bi.getUid());
			}
			objs[2] = bi.getUid();
			objs[3] = bi.getQuestionId();
			objs[4] = bi.getAnswerCount();
			objs[5] = bi.getCategory().getName();
			objs[6] = bi.getContent();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("问题相关数据");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public void exportRubbishQuestion(HttpServletResponse response,
			Map<String, Object> map) {
		List<Question> list = questionMapper.exportRubbishQuestion(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName = "回收站问题相关数据.xls";
		String[] headers = new String[] { "时间", "问题编号", "问题详情" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (Question bi : list) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(bi.getCreatedAt());
			objs[1] = bi.getQuestionId();
			objs[2] = bi.getContent();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("回收站问题相关数据");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public List<Long> selectQuestionId(Long uid) {
		return questionMapper.selectQuestionId(uid);

	}

	@Override
	public PageInfo<Question> selectHighEnergyQuestion(String startTime,
			String endTime, Question question) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("uid", question.getUid());
		PageInfo pageInfo = PageHelper.startPage(question.getPageNum(),
				question.getPageSize()).doSelectPageInfo(
				() -> questionMapper.selectHighEnergyQuestion(map));
		return pageInfo;
	}

	@Override
	public int updateAnswer(Long quetionId) {
		// TODO Auto-generated method stub
		return questionMapper.updateAnswer(quetionId);
	}

	@Override
	public List<Long> selectGood(int isGood) {
		// TODO Auto-generated method stub
		return questionMapper.selectGood(isGood);
	}	

}
