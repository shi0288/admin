<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">修改问题</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/question/updateQuestion" method="post" id="filter">
           
            <input type="hidden" 
            <#if (question.is_html)?? && question.is_html==1>
               value="${(question.cover)!''}"
            <#else>
               value="${attachs!''}"
            </#if>
             name="attachs" id="attachs"/>
             
            <input type="hidden" value="${question.questionId}" name="questionId" id="questionId"/>
            <input type="hidden" value="${question.is_html}" name="contentType" />
            <div class="form-group">
                <label class="col-md-2 control-label">问题ID:</label>
                <div class="col-md-3">
                    <input type="text" value="${question.questionId}" class="form-control" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">发布人:</label>
                <div class="col-md-3">
                    <input type="text" value="${(question.user.userInfo.data.nick_name)!''}" class="form-control"
                           disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">发布省份:</label>
                <div class="col-md-3">
                    <select id="province_select" class="form-control province_select">
                        <option>选择</option>
                    <#if (provinceList)??>
                        <#list  provinceList as province>
                            <option value="${(province.id)!''}"
                                    <#if (question.city)?? && province.id==question.city.parent>selected</#if>>${(province.name)!''}</option>
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
                                    <#if (question.city)?? && city.id==question.city.id>selected</#if>>${city.name}</option>
                        </#list>
                    <#else>
                        <option value="">请先选择省份</option>
                    </#if>
                    </select>
                </div>
            </div>
            <#if question.status==-98>
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">发布时间:</label>
                <div class="col-md-3">
                       <input value="${releaseTime!''}" name="releaseTime" id="releaseTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           />
                </div>
            </div> 
            </#if>
                                  
            <div class="form-group">
                <label class="col-xs-12 col-md-2 control-label">问题分类:</label>
            <#if (categoryList)??>
                <#list  categoryList as cate>
                    <div class="col-xs-6 col-md-1">
                        <label class="radio-inline">
                            <input type="radio" name="categoryId"
                                   <#if (question.categoryId)?? && question.categoryId==cate.categoryId >checked</#if>
                                   value="${(cate.categoryId)!''}"> ${(cate.name)!''}
                        </label>
                    </div>
                </#list>
            </#if>
            </div>
            
            <div class="form-group">
                <label class="col-xs-12 col-md-2 control-label">是否压缩:</label>               
                    <div class="col-xs-6 col-md-1">
                        <label class="radio-inline">
                            <input type="radio"  name="compression" value="1"> 是
                        </label>
                        </div> 
                   <div class="col-xs-6 col-md-1">     
                        <label class="radio-inline">
                            <input type="radio" name="compression" value="0" checked> 否
                        </label>
                    </div>      
            </div>
            
             <div class="form-group content_select" >
                <label class="col-xs-12 col-md-2 control-label">内容类型:</label>               
                    <div class="col-xs-6 col-md-1 ">
                        <label class="radio-inline" >                            
                            <input type="radio"  value="1" disabled
                            <#if (question.is_html)?? && question.is_html==1>
                             checked
                            </#if>
                            > 图文混排
                        </label>
                    </div> 
                    <div class="col-xs-6 col-md-1 ">     
                        <label class="radio-inline" >
                            <input type="radio"  value="0" disabled
                            <#if (question.is_html)?? && question.is_html==1>
                            <#else>
                            checked
                            </#if>
                            >一般类型
                        </label>
                    </div>      
            </div>
            
            <#if question.user.isOfficial==1>
                <div class="form-group title_select" >
             <label for="title" class="col-md-2 control-label">内容标题:</label>
             <div class="col-md-3 ">
                    <input class="form-control" id="title" name="title"  type="text" value="${(question.title)!''}"/>
                </div>
            </div>
            </#if>
            
            
            <div class="form-group">
                <label for="content" class="col-md-2 control-label">问题描述:</label>               
                <#if (question.is_html)?? && question.is_html==1>
                 <div class="col-sm-offset-2 col-sm-10 pic_content" >
                    <script id="container" name="container" type="text/plain">${(question.content)!''}
                    </script>
                </div>
                <#else>
                 <div class="col-md-8 pub_content" >
                    <textarea class="form-control" id="content" name="content" rows="5">${(question.content)!''}</textarea>
                </div>
              </#if>               
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <label for="attachImg" class="btn btn-primary">
                        上传图片
                    </label>
                    <input type="file" name="files" id="attachImg" multiple="true" style="display: none"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10 pic_after">
                <#if (question.is_html)?? && question.is_html==1 && (question.cover)!''>
                  <div class="col-xs-6 col-md-2">
                                <button type="button" class="close img-close"><span>×</span></button>
                                <img src="${(question.cover)!''}" class="img-responsive img-thumbnail"
                                     style="height: 80px;" alt="图片无法访问"></div>
                <#else>
                  <#if (question.attaches)??>
                    <#list  question.attaches as attach>
                        <#if attach.fileType==0>
                            <div class="col-xs-6 col-md-2">
                                <button type="button" class="close img-close"><span>×</span></button>
                                <img src="${(attach.file)!''}" class="img-responsive img-thumbnail"
                                     style="height: 80px;" alt="图片无法访问"></div>
                        </#if>
                    </#list>
                  </#if>
                </#if>
                
                </div>
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
                    <input class="form-control" id="find_series" completeSeries type="text" value=""/>
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
                <#if (question.series)??>
                    <#list  question.series as series>
                        <div class="col-xs-6 col-md-2"><span class="btn btn-warning btn-xs span_series_name"
                                                             tagval="${(series.seriesId)!''}">${(series.name)!''}</span>
                        </div>
                        <input type="hidden" name="seriesIds" value="${(series.seriesId)!''}">
                    </#list>
                </#if>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input name="is_good" type="checkbox"  <#if question.isGood==1>checked value="1"</#if> disabled/>是否加精
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_create_question">发布</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>
<#include  "/user/autocomplete.ftl" />

<script type="text/javascript" charset="utf-8" src="${jsPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${jsPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${jsPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
    var ue = UE.getEditor('container');
</script>
</body>
</html>
