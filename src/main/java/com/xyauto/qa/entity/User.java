package com.xyauto.qa.entity;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Integer type;
    private String word;
    private String intro;
    private String expert_name;
    private String expert_avatar;
    private String expert_images;
    private Integer question_count;
    private Integer answer_count;
    private String category_ids;
    private String company_name;
    private String company_intro;
    private Float lng;
    private Float lat;
    private Integer status;
    private Integer released_at;
    private String forbbiden_reason;
    private Integer created_at;
    private Integer updated_at;
    private Integer deleted_at;
    private Integer isOfficial;
    private Integer enableDistribute;
    private String title;
    private Integer dealer_id;
    private String website;
    private String code;
    @Transient
    private JSONObject userInfo;

    @Transient
    private List<Category> categories;

    @Transient
    private List<Series> series;


    @Transient
    private  List<Brand> brands =new ArrayList<>();
    @Transient
    private Integer forbbiden_time;
    @Transient
    private String forbbiden_user_name;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getExpert_name() {
        return expert_name;
    }

    public void setExpert_name(String expert_name) {
        this.expert_name = expert_name;
    }

    public String getExpert_avatar() {
        return expert_avatar;
    }

    public void setExpert_avatar(String expert_avatar) {
        this.expert_avatar = expert_avatar;
    }

    public String getExpert_images() {
        return expert_images;
    }

    public void setExpert_images(String expert_images) {
        this.expert_images = expert_images;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }

    public Integer getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Integer answer_count) {
        this.answer_count = answer_count;
    }

    public String getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(String category_ids) {
        this.category_ids = category_ids;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_intro() {
        return company_intro;
    }

    public void setCompany_intro(String company_intro) {
        this.company_intro = company_intro;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReleased_at() {
        return released_at;
    }

    public void setReleased_at(Integer released_at) {
        this.released_at = released_at;
    }

    public String getForbbiden_reason() {
        return forbbiden_reason;
    }

    public void setForbbiden_reason(String forbbiden_reason) {
        this.forbbiden_reason = forbbiden_reason;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public Integer getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Integer updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Integer deleted_at) {
        this.deleted_at = deleted_at;
    }

    public JSONObject getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JSONObject userInfo) {
        this.userInfo = userInfo;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

	public Integer getIsOfficial() {
		return isOfficial;
	}

	public void setIsOfficial(Integer isOfficial) {
		this.isOfficial = isOfficial;
	}

	public Integer getEnableDistribute() {
		return enableDistribute;
	}

	public void setEnableDistribute(Integer enableDistribute) {
		this.enableDistribute = enableDistribute;
	}

	public Integer getForbbiden_time() {
		return forbbiden_time;
	}

	public void setForbbiden_time(Integer forbbiden_time) {
		this.forbbiden_time = forbbiden_time;
	}

	public String getForbbiden_user_name() {
		return forbbiden_user_name;
	}

	public void setForbbiden_user_name(String forbbiden_user_name) {
		this.forbbiden_user_name = forbbiden_user_name;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getDealer_id() {
		return dealer_id;
	}

	public void setDealer_id(Integer dealer_id) {
		this.dealer_id = dealer_id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String toString(){
		StringBuffer buffer=new StringBuffer();
		if (this.uid!=null) {
			buffer.append("uid:").append(this.uid);
		} 
		if (this.type!=null) {
			buffer.append("type:").append(this.type);
		}
		return buffer.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	
	
    
}