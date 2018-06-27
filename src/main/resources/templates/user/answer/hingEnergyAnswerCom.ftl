<#if (question) ??>
<div class="panel panel-info">
    <div class="panel-heading">
        <div class="container-fluid content-block">
            <div class="row">
                <div class="col-xs-6 col-md-2">
                    <strong>${((question.createdAt?number*1000)?number_to_datetime)!''}</strong></div>
                                
                <div class="col-xs-6 col-md-2">提问类型:<strong>${(question.category.name)!''}</strong></div>
                <div class="col-xs-6 col-md-2">问题来源:<strong>${conver('source',question.source)}</strong></div>
            
                <div class="col-xs-6 col-md-2">用户ID:<strong>${(question.uid)!''}</strong></div>
                <div class="col-xs-6 col-md-2">
                    位置:<strong>${(question.city.name)!''}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                    关联车型:<strong><#if (question.series)??>
                        <#list question.series as series >${(series.name)!''}</#list></#if></strong>
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


<#if  (answerList)??>
    <#list answerList as e>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="container-fluid content-block">
                <div class="row">
                    <div class="col-xs-6 col-md-2"><strong>${((e.createdAt?number*1000)?number_to_datetime)!''}</strong>
                    </div>
                    <div class="col-xs-6 col-md-2">用户ID:<strong>${(e.uid)!''}</strong></div>                    
                    <div class="col-xs-6 col-md-3" style="padding-right: 0px;"> 
                    <#if (e.goodAt) &lt;= 0 >
                        <button type="button" tagVal="${(e.answerId)!''}" id="set_hingEnerg_${(e.answerId)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_hingEnerg">
                               设置高能
                        </button>
                    <#else>
                        <button type="button" tagVal="${(e.answerId)!''}" id="set_hingEnerg_${(e.answerId)!''}" taskName="false"
                                    class="btn btn-xs btn-warning set_hingEnerg">
                                取消高能
                        </button>                       
                    </#if> 
                    <button type="button" tagVal="${(e.answerId)!''}" id="set_hingEnerg_${(e.answerId)!''}" 
                                    class="btn btn-xs btn-warning write_comment">
                                写评论
                     </button>
                     <button type="button" tagVal="${(e.answerId)!''}" id="set_hingEnerg_${(e.answerId)!''}" 
                                    class="btn btn-xs btn-warning see_comment">
                                看评论
                     </button>              
                    </div>
                    <div class="col-xs-6 col-md-2">点赞数：<strong>${(e.agreeCount)!''}</strong></div>                    
                    <div class="col-xs-6 col-md-2">回答来源:<strong>${conver('source',e.source)}</strong></div>            
                </div>
            </div>
        </div>
        <div id="popupcontent" class="popupcontent">    
                 <input type="hidden" value="${e.answerId}" name="answerId" id="answerId"/>
                <textarea class="col-md-12 ;padding-bottom:100%" style="margin:0; padding:0;border:none;width:100%;" id="comment" name="comment">${(e.goodDesc)!''}</textarea>            
        </div>
        <div class="panel-body">
            <p>
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

