<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">问题管理</li>
    </ol>
    <div class="well">
        <form class="table-filter" id="form1" action="${base}/user/question/contentList" method="post">
            <div class="row">                
                <div class="col-md-3">
                  <div class="row"> 
                    <div class="col-xs-3  col-md-4 group-label">  <label>问答ID</label>  </div>
                    <div class="col-xs-9  col-md-8 group-control"><input  type="text" class="form-control" id="question_id" value="${(question.questionId)!''}" name="questionId"
                           placeholder="问题ID"/>
                    </div>
                  </div>
                </div>
                <div class="col-md-3">
                   <div class="row"> 
                     <div class="col-xs-3  col-md-4 group-label"><label>用户名</label></div>
                     <div class="col-xs-9  col-md-8 group-control"><input type="text" class="form-control" value="${nickName!''}" name="nickName" placeholder="用户名"/></div>
                   </div>
                </div>
                <div class="col-md-3">
                   <div class="row"> 
                    <div class="col-xs-3  col-md-4 group-label"><label >用户ID</label></div>
                    <div class="col-xs-9  col-md-8 group-control"><input type="text" class="form-control" value="${(question.uid)!''}" name="uid" placeholder="用户ID"/></div>
                   </div>
                </div>
                <div class="col-md-3">
                    <div class="row"> 
                     <div class="col-xs-4  col-md-4 group-label"><label>问答状态</label></div>
                     <div class="col-xs-8  col-md-8 group-control"><input type="text" class="form-control" value="${question.answerCount}" name="answerCount"
                           placeholder="问答状态"/>
                     </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                 <div class="row"> 
                  <div class="col-xs-4  col-md-4 group-label"><label>问题分类</label></div>
                  <div class="col-xs-8  col-md-8 group-control">
                      <select name="categoryId" id="categoryId" class="form-control">
                        <option value="">全部</option>
                        <option value="0">其他</option>
                    <#if categoryList??>
                        <#list categoryList as category>
                            <option value="${category.categoryId}"
                                <#if question.categoryId==category.categoryId>
                                    selected="selected"
                                </#if>
                            >${category.name}</option>
                        </#list>
                    </#if>
                    </select>
                  </div>
                 </div>                     
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                  <div class="col-xs-4  col-md-4 group-label"><label>用户身份</label></div>
                  <div class="col-xs-8  col-md-8 group-control">
                    <select name="userType" class="form-control">
                        <option value="">全部</option>
                        <option value="0"
                        <#if userType??&& userType==0>
                                selected="selected"
                        </#if>
                        >普通
                        </option>
                        <option value="1"
                        <#if userType??&& userType==1>
                                selected="selected"
                        </#if>
                        >标兵
                        </option>
                        <option value="2"
                        <#if userType??&& userType==2>
                                selected="selected"
                        </#if>
                        >专家
                        </option>
                        <option value="3"
                        <#if userType??&& userType==3>
                                selected="selected"
                        </#if>
                        >车顾问
                        </option>
                        <option value="9"
                        <#if userType??&& userType==9>
                                selected="selected"
                        </#if>
                        >官方
                        </option>
                    </select>
                   </div>
                 </div> 
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                  <div class="col-xs-4  col-md-4 group-label"><label>置顶状态</label></div>
                  <div class="col-xs-8  col-md-8 group-control">
                    <select id="isTop" name="isTop" class="form-control">
                        <option value="">全部</option>
                        <option value="1"
                        <#if question??&& question.isTop==1>
                                selected="selected"
                        </#if>
                        >是
                        </option>
                        <option value="0"
                        <#if question??&& question.isTop==0>
                                selected="selected"
                        </#if>
                        >否
                        </option>
                    </select>
                   </div>
                 </div>
                </div>

                <div class="col-md-3">
                  <div class="row"> 
                  <div class="col-xs-3  col-md-4 group-label"><label>关键词</label></div>
                  <div class="col-xs-9  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(keyWord)!''}" name="keyWord" id="keyWord" placeholder="关键词"/>
                  </div>
                 </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3">
                  <div class="row"> 
                  <div class="col-xs-4  col-md-4 group-label"><label>开始时间</label></div>
                  <div class="col-xs-8  col-md-8 group-control">
                    <input value="${startDate!''}" name="startDate" id="startDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})"
                           placeholder="开始时间>="/>
                   </div>
                  </div>          
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>结束时间</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input value="${endDate!''}" name="endDate" id="endDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})"
                           placeholder="结束时间<="/>
                   </div>
                  </div>
                </div>

                <div class="col-md-3">
                   <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>发布省份</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select class="form-control province_sel">
                        <option value="">全部</option>
                    <#if (provinceList)??>
                        <#list  provinceList as province>
                            <option value="${(province.id)!''}">${(province.name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                    </div>
                  </div>
                </div>

                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>发布城市</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="cityId" class="form-control city_sel">
                        <option value="">全部</option>
                    </select>
                    </div>
                  </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>品牌选择</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select  name="brandId" class="form-control brand_sel">
                        <option value="">全部</option>
                    <#if (brandList)??>
                        <#list  brandList as brand>
                            <option value="${(brand.brandId)!''}">${(brand.name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                   </div>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>关联车系</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="seriesId" class="form-control car_sel">
                        <option value="">全部</option>
                    </select>
                  </div>
                 </div>  
                </div>

                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-5  col-md-5 group-label"><label>问题加密KEY</label></div>
                   <div class="col-xs-7  col-md-7 group-control">
                    <input class="form-control" type="text" value="${(question.key)!''}" name="key"
                           placeholder="问题加密KEY"/>
                  </div>
                 </div>          
                </div>

                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>精华状态</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="isGood" class="form-control">
                        <option value>全部</option>
                        <option
                        <#if question??&& question.isGood==1>
                                selected="selected"
                        </#if>
                                value="1">是
                        </option>
                        <option
                        <#if question??&& question.isGood==0>
                                selected="selected"
                        </#if>
                                value="0">否
                        </option>
                    </select>
                </div>
                </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>来源渠道</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="source" class="form-control">
                        <option value="">全部</option>
                        <#if (sourceMap)??>
                            <#list sourceMap.keySet() as key>
                                <option <#if source?? && source==key > selected="selected"</#if> value="${key}">${sourceMap.get(key)}</option>
                            </#list>
                        </#if>
                    </select>
                    </div>
                    </div>
                </div>
                <!--<div class="col-md-3">
                    <select name="source" class="form-control">
                        <option value="">提问类型（全部）</option>
                        <#if (questionTypeMap)??>
                            <#list questionTypeMap.keySet() as key>
                                <option <#if source?? && source==key > selected="selected"</#if> value="${key}">${questionTypeMap.get(key)}</option>
                            </#list>
                        </#if>
                    </select>
                </div>-->
            </div>
            <div class="row" style="padding-top: 10px;">
                <!--<div class="col-xs-6 col-md-1">
                 <label class="checkbox-inline" >
                        <input type="checkbox"  id="checked_all" name="checked_all" >全选
                 </label>
                </div> -->
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary">筛选</button>
                </div>
                <div class="col-md-1 hidden-xs">
                    <button type="button"  onclick="exportQuestionExcel()"  class="btn btn-primary"> 导出全部</button>
                </div>
                <div class="col-xs-6 col-md-2">
                    <button type="button"  onclick="setGood()"  class="btn btn-primary"> 批量设置精华</button>
                </div>
                <div class="col-xs-2 col-md-1">
                    <button type="button"  onclick="deleteQue()"  class="btn btn-primary"> 批量删除</button>
                </div>
            </div>
        </form>
    </div>
<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"questionId":(question.questionId)!,
    "nickName":(nickName),"uid":(question.uid)!,"answerCount":(question.answerCount)!,
    "categoryId":(question.categoryId)!,"userType":(userType)!,"isTop":(question.isTop)!,
    "keyWord":(keyWord)!,"startDate":(startDate)!,"endDate":(endDate)!,"isGood":(question.isGood)!,
    "source":(source)!
    }
    />
    <@p.pager pager = pager baseUrl = "${base}/user/question/contentList" parameterMap = parameterMap/>
</#if>

<#include  "/user/question/contentCom.ftl" />

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/contentList" parameterMap = parameterMap/>
</#if>
    <div class="col-xs-6 col-md-2">
         <button type="button"  onclick="setGood()"  class="btn btn-primary"> 批量设置精华</button>
    </div>
    <div class="col-xs-2 col-md-1">
         <button type="button"  onclick="deleteQue()"  class="btn btn-primary"> 批量删除</button>
    </div>
</div>
          
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" src="${jsPath}/question.js"></script>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>
<script type="text/javascript" src="${jsPath}/clipboard.min.js"></script>

</body>
</html>
