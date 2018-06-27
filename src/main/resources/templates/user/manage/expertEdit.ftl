<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">用户管理</a></li>
        <li class="active">个人信息</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/manage/updateUser" method="post" id="filter">
            <input type="hidden" name="uid" value="${(user.uid)!''}"/>


            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">用户ID:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" value="${(user.uid)!''}" disabled />
                </div>
            </div>

            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">专家姓名:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="expert_name" name="expert_name"
                           value="${(user.expert_name)!''}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">用户类型:</label>
                <div class="col-md-3">
                    <select name="type" class="form-control" disable>
                        <option value="">选择</option>
                    <#if (userTypeMap)??>
                        <#list userTypeMap.keySet() as key>
                            <option <#if (user.type)?? && user.type==key > selected="selected"</#if>
                                                                           value="${key}">${userTypeMap.get(key)}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">手机号码:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(user.userInfo.data.mobile)!''}"/>
                </div>
            </div>


            <div class="form-group">
                <label for="word" class="col-md-2 control-label">短简介:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" maxlength="14" name="word" id="word"
                           value="${(user.word)!''}" tooltip="top|14字以内"/>
                </div>
            </div>


            <div class="form-group">
                <label for="intro" class="col-md-2 control-label">详细介绍:</label>
                <div class="col-md-8">
                    <textarea class="form-control" id="intro" name="intro" rows="5">${(user.intro)!''}</textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <img src="${(user.expert_avatar)!''}" id="userAvatarImg" class="img-responsive img-thumbnail"
                         style="height: 80px;" alt="<#if (user.expert_avatar)??>请上传<#else>图片无法访问</#if>"/>
                </div>
            </div>

            <div class="form-group">
                <input type="hidden" name="expert_avatar" value="${(user.expert_avatar)!''}"/>
                <div class="col-sm-offset-2 col-sm-10">
                    <label for="userAvatar" class="btn btn-primary">
                        上传头像
                    </label>
                    <input type="file" name="file" id="userAvatar" style="display: none"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-12 col-md-2 control-label">专家擅长回答的问题:</label>
            <#if  (categoryList)??>
                <#list categoryList as cate>
                    <#assign  showIs=false  />
                    <#list (user.category_ids)?split(",") as userCategoryId >
                        <#if userCategoryId==cate.categoryId>
                            <#assign  showIs=true  />
                            <#break>
                        </#if>
                    </#list>
                    <div class="col-xs-6 col-md-1">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="categoryIds" <#if showIs> checked</#if> value="${(cate.categoryId)!''}"> ${(cate.name)!''}
                        </label>
                    </div>
                </#list>
            </#if>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">品牌:</label>
                <div class="col-md-3">
                    <select class="form-control brand_select">
                        <option>选择</option>
                    <#list  brandList as brand>
                        <option value="${brand.brandId}">${brand.name}</option>
                    </#list>
                    </select>
                </div>
                <label for="find_series" class="col-md-2 control-label">输入查询:</label>
                <div class="col-md-3">
                    <input class="form-control" id="find_series" completeBrand type="text" value="" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">车系:</label>
                <div class="col-md-3">
                    <select class="form-control car_select">
                        <option>请先选择品牌</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8  car_show">
                <#if (user.brands)??>
                    <#list  user.brands as brand>
                        <div class="col-xs-6 col-md-2"><span  class="btn btn-warning btn-xs span_series_name" tagVal="0_${(brand.brandId)!''}" >${(brand.name)!''}</span></div>
                        <input type="hidden" name="seriesIds" value="0_${(brand.brandId)!''}"/>
                        <#list  brand.series as series>
                            <div class="col-xs-6 col-md-2"><span  class="btn btn-warning btn-xs span_series_name" tagVal="${(series.seriesId)!''}_${(brand.brandId)!''}" >${(series.name)!''}</span></div>
                            <input type="hidden" name="seriesIds" value="${(series.seriesId)!''}_${(brand.brandId)!''}"/>
                        </#list>
                    </#list>
                </#if>
                </div>
            </div>
            <!-- 开始-->
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">发布省份:</label>
                <div class="col-md-3">
                    <select id="province_select" class="form-control province_select">
                        <option>选择</option>
                    <#if (provinceList)??>
                        <#list  provinceList as province>
                            <option value="${(province.id)!''}"
                                    <#if (usecity)?? && province.id==usecity.parent>selected</#if>>${(province.name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="city_id" class="col-md-2 control-label">城市:</label>
                <div class="col-md-3">
                    <select id="city_id" name="cityId" class="form-control  city_select">
                    <#if (curCitys)??>
                        <option value="">请选择</option>
                        <#list curCitys as city >
                            <option value="${city.id}"
                                    <#if (usecity)?? && city.id==usecity.id>selected</#if>>${usecity.name}</option>
                        </#list>
                    <#else>
                        <option value="">请先选择省份</option>
                    </#if>
                    </select>
                </div>
            </div>
            <!-- 结束-->
            
            <div class="form-group">
                <label for="company_name" class="col-md-2 control-label">所属公司:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" maxlength="14" name="company_name" id="company_name"
                           value="${(user.company_name)!''}"  />
                </div>
            </div>


            <div class="form-group">
                <label for="company_intro" class="col-md-2 control-label">所属公司简介:</label>
                <div class="col-md-8">
                    <textarea class="form-control" id="company_intro" name="company_intro" rows="5">${(user.company_intro)!''}</textarea>
                </div>
            </div>



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_update_user">发布</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/manage.js"></script>
<#include  "/user/autocomplete.ftl" />


</body>
</html>
