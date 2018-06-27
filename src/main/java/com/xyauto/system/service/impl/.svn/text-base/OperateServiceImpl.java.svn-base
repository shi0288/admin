package com.xyauto.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.system.entiy.Operate;
import com.xyauto.system.mapper.OperateMapper;
import com.xyauto.system.service.IOperateService;
import com.xyauto.system.vo.MenuOperateVo;

@Service
public class OperateServiceImpl  implements IOperateService {

	@Autowired
	private OperateMapper operateMapper;
	@Override
	public int insert(Operate operate) {
		// TODO Auto-generated method stub
		return operateMapper.insertOperate(operate);
	}

	@Override
	public List<Operate> selectList(Operate operate) {
		// TODO Auto-generated method stub
		return operateMapper.selectList(operate);
	}

	@Override
	public List<MenuOperateVo> selectMenuOperate(Operate operate) {
		
		return operateMapper.selectMenuOperate(operate);
	}

	@Override
	public Operate selectOperate(Integer operateId) {
		
		return operateMapper.selectOperate(operateId);
	}

	@Override
	public int updateOperate(Operate operate) {
		// TODO Auto-generated method stub
		return operateMapper.updateOperate(operate);
	}

}
