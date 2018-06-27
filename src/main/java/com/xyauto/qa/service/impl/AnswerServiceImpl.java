package com.xyauto.qa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Attach;
import com.xyauto.qa.entity.Message;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.entity.vo.HingEntryAnswerVo;
import com.xyauto.qa.mapper.AnswerMapper;
import com.xyauto.qa.service.IAnswerService;
import com.xyauto.qa.service.IMessageService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;
import com.xyauto.utils.enumutil.EnumUtils.MessageType;
import com.xyauto.utils.excel.ExcelUtils;
import com.xyauto.utils.excel.ExcelVo;

@Service
public class AnswerServiceImpl extends ABaseServiceImpl<Answer, Long> implements
		IAnswerService {

	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private IMessageService messageService;
	@Autowired
    private Constants constants;
	@Override
	public PageInfo<Answer> getAnswerList(Answer answer) {
		PageInfo<Answer> pageInfo = PageHelper.startPage(answer.getPageNum(),
				answer.getPageSize()).doSelectPageInfo(
				() -> answerMapper.getAnswerList(answer));
		List<Answer> list = pageInfo.getList();
		for (Answer temp : list) {
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
	public PageInfo<Answer> getAnswerList(Answer answer, Integer userType,
			String sort, String keyWord, Integer status, String nickName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("answerId", answer.getAnswerId());
		map.put("uid", answer.getUid());
		map.put("questionId", answer.getQuestionId());
		if (userType != null && userType == 9) {
			map.put("isOfficial", userType);
		} else {
			map.put("userType", userType);
		}
		map.put("source", answer.getSource());
		map.put("hasAttach", answer.getHasAttach());
		map.put("cityId", answer.getCityId());
		map.put("startTime", answer.getCreatedAt());
		map.put("endTime", answer.getUpdatedAt());
		map.put("keyWord", keyWord);
		map.put("sort", sort);
		map.put("expert_name", nickName);
		if (userType != null && userType == 9) {
			map.put("isOfficial", userType);
		} else {
			map.put("userType", userType);
		}

		if (status == CommonCons.Quesiotn_Flag.DELETE_QUESTION) {
			return this.getDelAnswerListByMapParam(answer, map);
		}
		if (status == CommonCons.Quesiotn_Flag.REVIEW_QUESTION) {
			map.put("status", -1);
			return this.getAnswerListByMapParam(answer, map);
		}
		if (status == CommonCons.Quesiotn_Flag.RUBBISH_QUESTION) {
			return null;
		}
		return this.getAnswerListByMapParam(answer, map);

	}

	private PageInfo<Answer> getDelAnswerListByMapParam(Answer answer,
			Map<String, Object> map) {
		PageInfo<Answer> pageInfo = PageHelper.startPage(answer.getPageNum(),
				answer.getPageSize()).doSelectPageInfo(
				() -> answerMapper.getDelAnswerListByMapParam(map));
		return this.getAnswerListInfo(pageInfo);
	}

	private PageInfo<Answer> getAnswerListByMapParam(Answer answer,
			Map<String, Object> map) {
		PageInfo<Answer> pageInfo = PageHelper.startPage(answer.getPageNum(),
				answer.getPageSize()).doSelectPageInfo(
				() -> answerMapper.getAnswerListByMapParam(map));
		return this.getAnswerListInfo(pageInfo);
	}

	/**
	 * 封装用户信息和图片信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	private PageInfo<Answer> getAnswerListInfo(PageInfo<Answer> pageInfo) {
		List<Answer> list = (List<Answer>) pageInfo.getList();
		for (Answer temp : list) {
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
	public int selectAnswerUserCount(String startTime, String endTime,
			Integer userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("userType", userType);
		return answerMapper.selectAnswerUserCount(map);

	}

	@Override
	public List<Answer> selectAnswerByAgreencount(String startTime,
			String endTime) {
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return answerMapper.selectAnswerByAgreencount(map);
	}

	@Override
	public void exportAnswer(HttpServletResponse response,
			Map<String, Object> map) {
		List<Answer> list = answerMapper.exportAnswer(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName = "回复相关数据.xls";
		String[] headers = new String[] { "时间", "昵称", "用户id", "问题编号", "点赞数",
				"回复详情" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (Answer bi : list) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(bi.getCreatedAt());
			objs[1] = "未知";
			objs[2] = bi.getUid();
			objs[3] = bi.getQuestionId();
			objs[4] = bi.getAgreeCount();
			objs[5] = bi.getContent();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("回复相关数据");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public List<Long> selectAnswerId(Long uid) {

		return answerMapper.selectAnswerId(uid);
	}

	@Override
	public List<Answer> selectHingEnergyAnswer(Long questionId,
			Boolean isHingEnergy) {
		Answer answer = new Answer();
		answer.setQuestionId(questionId);
		if (isHingEnergy != null) {
			if (isHingEnergy == true) {
				answer.setGoodAt(1);
			} else {
				answer.setGoodAt(0);
			}
		}
		List<Answer> list = answerMapper.selectHingEnergyAnswer(answer);

		for (Answer temp : list) {
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
		return list;
	}

	@Override
	public String setHingEnergy(Long answerId, boolean isHingEnergy,
			String comment) {
		Answer answer = answerMapper.selectByPrimaryKey(answerId);
		if (!isHingEnergy) {
			answer.setGoodAt(0);
		} else {
			// 查询该问题下是否已经有高能回答
			List<Answer> list = this.selectHingEnergyAnswer(
					answer.getQuestionId(), true);
			if (list != null && list.size() > 0) {
				return "该问题下已经有高能回复，请取消高能后在设置";
			}
			answer.setGoodAt((int) (new Date().getTime() / 1000));
		}
		if (StringUtils.isNotBlank(comment)) {
			answer.setGoodDesc(comment);
		}
		answerMapper.updateByPrimaryKeySelective(answer);
		return "success";
	}

	@Override
	public List<HingEntryAnswerVo> selectPushHingEnergy(Long uid, String time) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("time", time);
		if (StringUtils.isBlank(time)) {
			return null;
		}
		// 查询高能回答
		List<Answer> answerList = answerMapper.selectPushHingEnergy(map);
		/**
		 * 查询已经推送的高能数据 转换time，根据业务规则，下月推送上月的信息，因此月份要加1
		 */
		Date date = DateUtils.beforMonth(time, 1, "yyyyMM");
		time = DateUtils.dateToStr(date, "yyyyMM");
		List<Message> messageList = messageService.selectGroupUid(uid, time,
				MessageType.Good_Answer.getCode());

		if (answerList != null && answerList.size() > 0) {
			List<HingEntryAnswerVo> list = new ArrayList<HingEntryAnswerVo>();
			if (messageList == null || messageList.size() == 0) {
				for (Answer answer : answerList) {
					HingEntryAnswerVo vo = new HingEntryAnswerVo();
					vo.setUid(answer.getUid());
					vo.setCount(answer.getAgreeCount());
					list.add(vo);
				}
			} else {
				int k = 0;
				for (int i = 0; i < answerList.size(); i++) {
					Answer answer = answerList.get(i);
					HingEntryAnswerVo vo = new HingEntryAnswerVo();
					vo.setUid(answer.getUid());
					vo.setCount(answer.getAgreeCount());
					list.add(vo);
					if (k < messageList.size()) {
						if (answer.getUid() == messageList.get(k).getUid()) {
							vo.setPush(true);
						} else if (answer.getUid() > messageList.get(k)
								.getUid()) {
							k++;
							for (int j = k; j < messageList.size(); j++) {
								if (answer.getUid() == messageList.get(j)
										.getUid()) {
									vo.setPush(true);
									k = ++i;
									break;
								} else if (answer.getUid() < messageList.get(j)
										.getUid()) {
									k = j;
									break;
								}
							}
						}
					}
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public QaResult setFirstAnswer(Long answerId) {
		// TODO Auto-generated method stub
		//查询当前回答是否是第一条回复
		Answer answer=answerMapper.selectFirstAnswer(answerId);
		//不是第一条回复，则修改回答时间
		if (answer==null) {
			return new QaResult(ErrorCode.OVER,"回复id不正确，请重新设置");
		}else if (answer.getAnswerId().equals(answerId)) {
			return new QaResult(ErrorCode.OVER,"该回复已经是第一条，请不要重复设置");
		}else {
			answer.setAnswerId(answerId);
			answerMapper.setFirstAnswer(answer);
		}
		return new QaResult("设置成功");
	}

	@Override
	public Answer selectOne(Long answerId) {
		// TODO Auto-generated method stub
		Answer answer=answerMapper.selectByPrimaryKey(answerId);
		User user = userService.getUserAndInfo(answer.getUid());
		answer.setUser(user);
		return answer;
	}
}
