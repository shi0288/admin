package com.xyauto.bi.service.basic.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.bi.service.basic.IQuestionBIBasicService;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.mapper.AnswerMapper;
import com.xyauto.qa.mapper.QuestionMapper;

/**
 * 
 * @author zhangbh
 *
 */
@Service
public class QuestionBiBasicServiceImpl implements IQuestionBIBasicService {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper answerMapper;

	List<Integer> selectCountByMapGroupHours(Question question,Integer beforeDay) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if (question != null) {
			if (question.getIsGood() != null) {
				map.put("isGood", question.getIsGood());
			}
			if (question.getHasAttach() != null) {
				map.put("hasAttach", question.getHasAttach());
			}
		}
		if (beforeDay!=null&&beforeDay>0) {
			map.put("beforeDay", beforeDay);
		}
		return questionMapper.selectCountByMapGroupHours(map);
	}

	@Override
	public List<Integer> selectQuestionCountGroupHours(Integer beforeDay) {
		List<Integer> list = this.selectCountByMapGroupHours(null,beforeDay);
		return list;
	}

	@Override
	public List<Integer> selectQuestionCountByGoodGroupHours(Integer beforeDay) {
		Question question = new Question();
		question.setIsGood(1);
		List<Integer> list = this.selectCountByMapGroupHours(question,beforeDay);
		return list;
	}

	@Override
	public List<Integer> selectQuestionCountByAttachGroupHours(Integer beforeDay) {
		Question question = new Question();
		question.setHasAttach(1);
		List<Integer> list = this.selectCountByMapGroupHours(question,beforeDay);
		return list;
	}

	@Override
	public List<Question> selectBuyCarQuestionCountGroupHours(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectBuyCarQuestionCountGroupHours(map);
	}

	@Override
	public List<Integer> selectAnswerCountByMapGroupHours(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerCountByMapGroupHours(map);
	}

	@Override
	public List<Question> selectQuestionCountGroupHoursUserType(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);		
		return questionMapper.selectQuestionCountGroupHoursUserType(map);
	}

	@Override
	public List<Question> selectQuestionSpecifyCountGroupHoursUserType(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectQuestionSpecifyCountGroupHoursUserType(map);
	}

	@Override
	public List<Answer> selectAnswerCountGroupHoursUserType(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerCountGroupHoursUserType(map);
	}

	@Override
	public List<Question> selectQuestionSourceGroupHoursUserType(Integer beforeDay,Integer status) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		map.put("status", status);
		return questionMapper.selectQuestionSourceGroupHoursUserType(map);
	}

	@Override
	public List<Answer> selectAnswerSourceGroupHoursUserType(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerSourceGroupHoursUserType(map);
	}

	@Override
	public List<Question> selectQuestionSpecifyCountGroupDayUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectQuestionSpecifyCountByTimeGroupDayUid(map);
	}

	@Override
	public List<Answer> selectAnswerQueCountGroupDayUid(Integer startHour,
			Integer endHour,Integer content,Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		map.put("startHour", startHour);
		map.put("endHour", endHour);
		map.put("content", content);
		return answerMapper.selectAnswerQueCountGroupByTimeDayUid(map);
	}

	@Override
	public List<Answer> selectAnswersCountGroupDayUid(Integer startHour,Integer endHour,Integer content,Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		map.put("startHour", startHour);
		map.put("endHour", endHour);
		map.put("content", content);
		return answerMapper.selectAnswersCountGroupByTimeDayUid(map);
	}

	@Override
	public List<Question> selectQuestionCountGroupUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectQuestionSpecifyCountByTimeGroupDayUid(map);
	}

	@Override
	public List<Answer> selectAnswerQueCountGroupUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerQueCountGroupByTimeDayUid(map);
	}

	@Override
	public List<Answer> selectAnswersCountGroupUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswersCountGroupByTimeDayUid(map);
	}

	@Override
	public List<Answer> selectAnswersCountByContentGroupUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswersCountByContentGroupUid(map);
	}

	@Override
	public List<Answer> selectAnswersQueCountByContentGroupUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswersQueCountByContentGroupUid(map);
	}

	@Override
	public List<Question> selectQuestionByAnswerGroupHours(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectQuestionByAnswerGroupHours(map);
	}

	@Override
	public List<Question> selectDelQueGroupHoursSource(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectDelQueGroupHoursSource(map);
	}

	@Override
	public List<Answer> selectCallAnswerGroupHoursSource(Integer type,Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		map.put("type", type);
		return answerMapper.selectCallAnswerGroupHoursSource(map);
	}

	@Override
	public List<Question> selectAnswerQueGroupHoursSource(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return questionMapper.selectAnswerQueGroupHoursSource(map);
	}

	@Override
	public List<Answer> selectFirstAnswerQueCountGroupDayUid(Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beforeDay", beforeDay);
		return answerMapper.selectFirstAnswerQueCountGroupDayUid(map);
	}

	@Override
	public List<Answer> selectAnswerQueCountByAnswerQueTime(Integer userType,
			Integer length, Integer minute, Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userType", userType);
		map.put("length", length);
		map.put("second", minute*60);
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerQueCountByAnswerQueTime(map);
	}

	@Override
	public List<Answer> selectAnswerQueCount(Integer userType, Integer length,
			 Integer beforeDay) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userType", userType);
		map.put("length", length);
		map.put("beforeDay", beforeDay);
		return answerMapper.selectAnswerQueCount(map);
	}


}
