package com.xyauto.qa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.entity.Blocks;
import com.xyauto.qa.service.BlocksService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shiqm on 2017/3/22.
 */
@Service
public class BlocksServiceImpl extends ABaseServiceImpl<Blocks, Integer> implements BlocksService {


    @Override
    public List getPcBannerList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.BANNER_PC);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
    }

    @Override
    public List getMobileBannerList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.BANNER_MOBILE);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
    }

    @Override
    public List getPcNoticeList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.NOTICE_PC);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
    }


    @Override
    public JSONObject getExpertPicList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.EXPERT_PC);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
    }

    @Override
    public JSONArray getBlockByName(String name) {
        Blocks blocks = new Blocks();
        blocks.setName(name);
        List<Blocks> list = this.get(blocks);
        if(list.size()==0){
            return null;
        }
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
    }


    @Override
    public int save(Blocks entity) {
        entity.setId(this.getTotalCount()+1);
        entity.setCreated_at(0);
        return super.save(entity);
    }

	@Override
	public JSONObject getTopic() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.TOPIC_TODAY);
        List<Blocks> list = this.get(blocks);
        String str=null;
        if (list!=null&&list.size()>0) {
        	str = list.get(0).getContent();
		}
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
		
	}

	@Override
	public List getInformationList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.INFORMATION_EXPERT);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
	}

	@Override
	public JSONObject getAdver1() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.ADVER1_EXPERT);
        List<Blocks> list = this.get(blocks);
        String str=null;
        if (list!=null&&list.size()>0) {
        	str = list.get(0).getContent();
		}
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
	}

	@Override
	public JSONObject getAdver2() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.ADVER2_EXPERT);
        List<Blocks> list = this.get(blocks);
        String str=null;
        if (list!=null&&list.size()>0) {
        	str = list.get(0).getContent();
		}
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
	}
	
	@Override
	public List getUserExpertList() {
        Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.USER_EXPERT_HOME);
        List<Blocks> list = this.get(blocks);
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
	}

	@Override
	public List getPopularTopicList() {
		Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.POPULAR_TOPIC);
        List<Blocks> list = this.get(blocks);
        if (list==null||list.size()<=0) {
			return null;
		}
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
	}

	@Override
	public List getOneInformationList() {
		Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.ONE_INFORMATION);
        List<Blocks> list = this.get(blocks);
        if (list==null||list.size()<=0) {
			return null;
		}
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;		
	}
	@Override
	public List recommendTopicList() {
		Blocks blocks = new Blocks();
        blocks.setName(CommonCons.Blocks_Flag.recommend_Topic);
        List<Blocks> list = this.get(blocks);
        if (list==null||list.size()<=0) {
			return null;
		}
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;		
	}

	@Override
	public List getList(String type) {
		Blocks blocks = new Blocks();
        blocks.setName(type);
        List<Blocks> list = this.get(blocks);
        if (list==null||list.size()<=0) {
			return null;
		}
        String str = list.get(0).getContent();
        JSONArray jsonArray = JSON.parseArray(str);
        return jsonArray;
	}

	@Override
	public void saveBlock(String content,String type) {
		Blocks blocks = new Blocks();
        blocks.setName(type);
        List<Blocks> list = get(blocks);
        if (list.size() == 0) {
            blocks.setContent(content);
            save(blocks);
        } else {
            blocks = list.get(0);
            blocks.setContent(content);
            update(blocks);
        }
		
	}
}
