<#if (question) ??>
<div class="panel panel-info">
    <div class="panel-heading">
        <div class="container-fluid content-block">
            <div class="row">
                <div class="col-xs-6 col-md-2">
                    <strong>${((question.createdAt?number*1000)?number_to_datetime)!''}</strong></div>
                <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                    <a href="javascript:void(0);" class="btn btn-xs btn-danger del_question"
                       tagVal="${(question.questionId)!''}">删除</a>
                    <a waitingLoad href="/user/answer/answerAdd?questionId=${(question.questionId)!''}"
                       class="btn btn-xs btn-primary">回答</a>
                       
                </div>
                <div class="col-xs-6 col-md-2">回答数：<strong>${(question.answerCount)!''}</strong></div>
                <div class="col-xs-6 col-md-2">
                    <#if e.user.status==-3>
                        <button type="button" tagVal="${(question.uid)!''}" taskName="false"
                                class="btn btn-xs btn-warning set_frozen">
                            解禁用户
                        </button>
                    <#else>
                        <button type="button" tagVal="${(question.uid)!''}" taskName="true"
                                class="btn btn-xs btn-primary set_frozen">
                            封禁用户
                        </button>
                    </#if>
                </div>
                <div class="col-xs-6 col-md-2">提问类型:<strong>${(question.category.name)!''}</strong></div>
                <div class="col-xs-6 col-md-2">问题来源:<strong>${conver('source',question.source)}</strong></div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-md-2">用户名:<strong><#if question.user.type==2 || question.user.type==3>${(question.user.expert_name)!''}<#else>${(question.user.userInfo.data.nick_name)!''}</#if></strong></div>
                <div class="col-xs-6 col-md-2">用户ID:<strong>${(question.uid)!''}</strong></div>
                <div class="col-xs-6 col-md-2">
                    位置:<strong>${(question.city.name)!''}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                    关联车型:<strong><#if (question.series)??>
                        <#list question.series as series >${(series.name)!''}</#list></#if></strong>
                </div>

                <div class="col-xs-6 col-md-2">身份:<strong><#if (question.user.isOfficial) &gt; 0>官方<#else>${conver('userType',question.user.type)}</#if></strong></div>
                <div class="col-xs-6 col-md-2">
                    <#if question.isGood==1>
                        <span class="label label-danger">精华</span></#if>
                    <#if question.isTop==1>
                        <span class="label label-danger">置顶</span></#if>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-body">
        <p>【话题编号】${(question.questionId)!''}</p>
        <p>【内容】${(question.content)!''}</p>
        <#if (question.attaches)?? && (question.attaches?size>0)>
            <p>
            <div class="row">
                <#list question.attaches as att>
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
</#if>


<#if  (pager.list)??>
    <#list pager.list as e>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="container-fluid content-block">
                <div class="row">
                    <div class="col-xs-6 col-md-2"><strong>${((e.createdAt?number*1000)?number_to_datetime)!''}</strong>
                    </div>
                    <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                        <#if (curName)?? && curName=="rubbishList">
                            <a href="javascript:void(0);" tagVal="${(e.answerId)!''}" class="btn btn-xs btn-warning recovery_answer">恢复</a>
                        <#elseif (curName)?? && curName=="verifyList">
                            <a href="javascript:void(0);" class="btn btn-xs btn-danger del_answer"
                               tagVal="${(e.answerId)!''}">删除</a>
                            <a href="javascript:void(0);" tagVal="${(e.answerId)!''}" class="btn btn-xs btn-warning verify_answer">审核</a>
                        <#else>
                            <a href="javascript:void(0);" class="btn btn-xs btn-danger del_answer"
                               tagVal="${(e.answerId)!''}">删除</a>
                            <!--<a href="/user/answer/answerAdd?questionId=${(e.questionId)!''}&answerId=${(e.answerId)!''}"
                               class="btn btn-xs btn-primary" >回答</a>-->
                            <a waitingLoad href="/user/comment/commentAdd?answer_id=${(e.answerId)!''}"
                       class="btn btn-xs btn-primary">评论</a>    
                        </#if>
                        <a href="/user/question/contentList?questionId=${(e.questionId)!''}" target="_blank"
                           class="btn btn-xs btn-primary" >看问题</a>
                    </div>
                    <div class="col-xs-6 col-md-2">点赞数：<strong>${(e.agreeCount)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">问题编号:<strong>${(e.questionId)!''}</strong></div>

                    <div class="col-xs-6 col-md-2">
                        <#if e.user.status==-3>
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
                        <button type="button" tagVal="${(e.answerId)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_first_answer">
                                设置首回
                        </button>
                    </div>
                    <div class="col-xs-6 col-md-2">回答来源:<strong>${conver('source',e.source)}</strong></div>
                </div>
                <div class="row">

                    <div class="col-xs-6 col-md-2">用户名:<strong><#if e.user.type==2 || e.user.type==3>${(e.user.expert_name)!''}<#else>${(e.user.userInfo.data.nick_name)!''}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">用户ID:<strong>${(e.uid)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">身份:<strong><#if (e.user.isOfficial) &gt; 0>官方<#else>${conver('userType',e.user.type)}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">
                        位置:<strong>${(e.city.name)!''}</strong>
                    </div>
                    <div class="col-xs-6 col-md-2">
                        评论数:<strong>${(e.comment_count)!''}</strong>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <p>
            <div class="col-xs-3 col-md-1">
                 <label class="checkbox-inline">
                        <input type="checkbox"  id="${(e.answerId)!''}" name="check_answerId"  value="${(e.answerId)!''}"> 
                 </label>
            </div>
            【回复编号】${(e.answerId)!''}</p>
            <p>【回复】${(e.content)!''}</p>
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

