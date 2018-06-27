<#if  (pager.list)??>
    <#list pager.list as e>

    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="container-fluid content-block">
                <div class="row">
                    <div class="col-xs-6 col-md-2"><strong>${((e.createdAt?number*1000)?number_to_datetime)!''}</strong></div>                    
                    <div class="col-xs-6 col-md-2">用户ID:<strong>${(e.uid)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">
                    <a waitingLoad href="/user/answer/hingEnergyAnswer?questionId=${(e.questionId)!''}"
                           class="btn btn-xs btn-primary">看回答</a>
                    </div>
               <!-- </div>
                <div class="row">-->
                    <div class="col-xs-6 col-md-2">提问类型:<strong>${(e.category.name)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">问题来源:<strong>${conver('source',e.source)}</strong></div>
                    <div class="col-xs-6 col-md-2">
                        关联车型:<strong><#if (e.series)??>
                        <#list e.series as series >${(series.name)!''}</#list></#if></strong>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-body">
            <p>【话题编号】${(e.questionId)!''}</p>
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

