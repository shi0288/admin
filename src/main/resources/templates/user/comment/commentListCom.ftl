<#if (answer) ??>
<div class="panel panel-info">
    <div class="panel-heading">
        <div class="container-fluid content-block">
            <div class="row">
                <div class="col-xs-6 col-md-2">
                    <strong>${((answer.createdAt?number*1000)?number_to_datetime)!''}</strong></div>
                <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                  <#if status??&&status==1>
                    <a href="javascript:void(0);" class="btn btn-xs btn-danger del_answer"
                       tagVal="${(answer.answerId)!''}">删除</a>
                    <a waitingLoad href="/user/comment/commentAdd?answer_id=${(answer.answerId)!''}"
                       class="btn btn-xs btn-primary">评论</a>
                  </#if>     
                </div>
                <div class="col-xs-6 col-md-2">评论数：<strong>${(answer.comment_count)!''}</strong></div>
                <div class="col-xs-6 col-md-2">
                    <#if e.user.status==-3>
                        <button type="button" tagVal="${(answer.uid)!''}" taskName="false"
                                class="btn btn-xs btn-warning set_frozen">
                            解禁用户
                        </button>
                    <#else>
                        <button type="button" tagVal="${(answer.uid)!''}" taskName="true"
                                class="btn btn-xs btn-primary set_frozen">
                            封禁用户
                        </button>
                    </#if>
                </div>
                <div class="col-xs-6 col-md-2">提问类型:<strong></strong></div>
                <div class="col-xs-6 col-md-2">回答来源:<strong>${conver('source',answer.source)}</strong></div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-md-2">用户名:<strong><#if answer.user.type==2 || answer.user.type==3>${(answer.user.expert_name)!''}<#else>${(answer.user.userInfo.data.nick_name)!''}</#if></strong></div>
                <div class="col-xs-6 col-md-2">用户ID:<strong>${(answer.uid)!''}</strong></div>
                <div class="col-xs-6 col-md-2">
                    位置:<strong>${(answer.city.name)!''}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                    关联车型:
                </div>

                <div class="col-xs-6 col-md-2">身份:<strong><#if (answer.user.isOfficial) &gt; 0>官方<#else>${conver('userType',answer.user.type)}</#if></strong></div>
                
            </div>
        </div>
    </div>
    <div class="panel-body">
        <p>【回复编号】${(answer.answerId)!''}</p>
        <p>【回复内容】${(answer.content)!''}</p>
        <#if (answer.attaches)?? && (answer.attaches?size>0)>
            <p>
            <div class="row">
                <#list answer.attaches as att>
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
                    <div class="col-xs-6 col-md-2"><strong>${((e.created_at?number*1000)?number_to_datetime)!''}</strong>
                    </div>
                    <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                        <#if status??&&status==0>
                            <a href="javascript:void(0);" tagVal="${(e.comment_id)!''}" class="btn btn-xs btn-warning ">恢复</a>
                        <#elseif status??&&status==1>
                            <a href="javascript:void(0);" class="btn btn-xs btn-danger del_comment"
                               tagVal="${(e.comment_id)!''}">删除</a>
                            <a href="/user/comment/commentAdd?comment_id=${(e.comment_id)!''}"
                               class="btn btn-xs btn-primary" >评论</a>
                        </#if>
                        <a href="/user/answer/answerList?answerId=${(e.answer_id)!''}" target="_blank"
                           class="btn btn-xs btn-primary" >看回复</a>
                    </div>
                    <div class="col-xs-6 col-md-2">点赞数：<strong>${(e.agree_count)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">回复ID:<strong>${(e.answer_id)!''}</strong></div>

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
       
                    </div>
                    <div class="col-xs-6 col-md-2">评论来源:<strong>${conver('source',e.source)}</strong></div>
                </div>
                <div class="row">

                    <div class="col-xs-6 col-md-2">用户名:<strong><#if e.user.type==2 || e.user.type==3>${(e.user.expert_name)!''}<#else>${(e.user.userInfo.data.nick_name)!''}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">用户ID:<strong>${(e.uid)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">身份:<strong><#if (e.user.isOfficial) &gt; 0>官方<#else>${conver('userType',e.user.type)}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">
                        位置:<strong></strong>
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
            【评论ID】${(e.comment_id)!''}</p>
            <p>【评论内容】${(e.content)!''}</p>
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

