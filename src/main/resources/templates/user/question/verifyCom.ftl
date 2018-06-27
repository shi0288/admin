<#if  (pager.list)??>
    <#list pager.list as e>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="container-fluid content-block">
                <div class="row">
                    <div class="col-xs-6 col-md-2"><strong>${((e.createdAt?number*1000)?number_to_datetime)!''}</strong></div>
                    <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                        <a href="javascript:void(0);" class="btn btn-xs btn-danger del_question"
                           tagVal="${(e.questionId)!''}">删除</a>
                        <#if  answeQuestion.status==-1 ||answeQuestion.status==-2>
                            <a href="javascript:void(0);" tagVal="${(e.questionId)!''}" class="btn btn-xs btn-primary verify_question">审核</a>
                        </#if>
                        <#if  answeQuestion.status==-98>
                            <a waitingLoad href="/user/question/toUpdateQuestion?questionId=${(e.questionId)!''}"
                           class="btn btn-xs btn-primary">修改</a>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-2">回答数：<strong>${(e.answerCount)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">用户名:<strong><#if e.user.type==2 || e.user.type==3>${(e.user.expert_name)!''}<#else>${(e.user.userInfo.data.nick_name)!''}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">用户ID:<strong>${(e.uid)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">身份:<strong><#if (e.user.isOfficial) &gt; 0>官方<#else>${conver('userType',e.user.type)}</#if></strong></div>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-md-2">提问类型:<strong>${(e.category.name)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">问题来源:<strong>${conver('source',e.source)}</strong></div>
                    <div class="col-xs-6 col-md-2">
                        关联车型:<strong><#if (e.series)??>
                        <#list e.series as series >${(series.name)!''}、</#list></#if></strong>
                    </div>
                    <div class="col-xs-6 col-md-2">
                        <#if e.user.status==-2>
                            <button type="button" tagVal="${(e.uid)!''}" taskName="false"
                                    class="btn btn-xs btn-warning set_frozen">
                                解禁用户
                            </button>
                        <#else>
                            <button type="button" tagVal="${(e.uid)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_frozen">
                                封禁用户
                            </button>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-2">
                        位置:<strong>${(e.city.name)!''}</strong>
                    </div>
                    <#if releaseMap ?? && (releaseMap.get(e.questionId?string))??>                    
                       <div class="col-xs-6 col-md-2">
                        发布时间:<strong>  ${((releaseMap.get(e.questionId?string))?string("yyyy-MM-dd HH:mm:ss"))!''}  </strong>
                    </div>
                    </#if>
                    
                </div>
            </div>
        </div>

        <div class="panel-body">
            <p>
            <div class="col-xs-3 col-md-1">
                 <label class="checkbox-inline">
                        <input type="checkbox" data="set_good_${(e.questionId)!''}" id="${(e.questionId)!''}" name="check_questionId"  value="${(e.questionId)!''}"> 
                 </label>
            </div>
            【话题编号】${(e.questionId)!''}</p>
            <p>【拦截原因】${(e.distrustReason)!''}</p>
            <p>【内容】${(e.content)!''}</p>
            <#if (e.attaches)?? && (e.attaches?size>0)>
                <p>
                <div class="row">
                    <#list e.attaches as att>
                        <#if att.fileType==0>
                            <div class="col-xs-6 col-md-2">
                                <img src="${(att.file)!''}"
                                     class="img-responsive img-thumbnail" alt="图片无法访问">
                            </div>
                        </#if>
                    </#list>
                </div>
                </p>
            </#if>
        </div>
    </div>
    </#list>
</#if>

