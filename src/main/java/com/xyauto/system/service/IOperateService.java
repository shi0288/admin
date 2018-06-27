package com.xyauto.system.service;

import java.util.List;

import com.xyauto.system.entiy.Operate;
import com.xyauto.system.vo.MenuOperateVo;

public interface IOperateService {
	int insert(Operate operate);
	List<Operate> selectList(Operate operate);
	List<MenuOperateVo> selectMenuOperate(Operate operate);
	Operate selectOperate(Integer operateId);
	int updateOperate(Operate operate);
}
