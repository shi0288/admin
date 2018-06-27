<#if (questionBi)??>
<div class="panel panel-info">
    <div class="panel-heading">
        <div class="container-fluid content-block">
        基础数据
             <div class="row">
                <div class="col-xs-6 col-md-2">
                 有效问题:<strong>${questionBi.questionCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                带图问题:<strong>${questionBi.hasAttachCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                精华问题:<strong>${questionBi.goodCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                有回复问题:<strong>${questionBi.remark1}</strong>
                </div>
             </div>
             <div class="row">
                <div class="col-xs-6 col-md-2">
                 回复数:<strong>${questionBi.answerCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                买车问题:<strong>${questionBi.buyCarCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                用车问题:<strong>${questionBi.sellCarCount}</strong>
                </div>
                <div class="col-xs-6 col-md-2">
                平均时间:<strong>${avgTime}</strong>
                </div>             
             </div> 
             
            <p></p>
            身份数据信息
             <#list typeList as item>    
                <#if item.type=="userType">
                     <#if item.typeValue==1>
                      <div class="row">
                          <div class="col-xs-6 col-md-2">
                          向标兵提问数：<strong>${item.questionSpecifyCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          标兵提问数：<strong>${item.questionCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          标兵回复数：<strong>${item.answerCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          标兵活跃度：<strong>${pacesetterActive}</strong>
                          </div>
                      </div> 
                     </#if>
                     <#if item.typeValue==2>                     
                      <div class="row">
                          <div class="col-xs-6 col-md-2">
                          向专家提问数：<strong>${item.questionSpecifyCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          专家提问数：<strong>${item.questionCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          专家回复数：<strong>${item.answerCount}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          专家活跃度：<strong>${expertActive}</strong>
                          </div>
                      </div>
                     </#if>
                </#if>
             </#list> 
             <div class="row">
                          <div class="col-xs-6 col-md-2">
                          车顾问回复数：<strong>${(adviser_answer_count)!''}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          车顾问答题数：<strong>${(adviser_question_count)!''}</strong>
                          </div>
                          <div class="col-xs-6 col-md-2">
                          车顾问人数：<strong>${(adviser_user_count)!''}</strong>
                          </div>
                      </div>
            <p></p>
           【 渠道数据信息】：            
                    <div class="row"> 
                        <div class="col-xs-6 col-md-1">
                                                               渠道
                        </div>
                        <div class="col-xs-6 col-md-1">
                                                               问题
                        </div>
                        <div class="col-xs-6 col-md-1">
                                                               回复
                        </div>
                        <div class="col-xs-6 col-md-1">
                                                               删除问题
                        </div>
                        <div class="col-xs-6 col-md-1">
                                                               有效问题
                        </div>
                        <div class="col-xs-6 col-md-1">
                                                               解答数
                        </div>
                        <div class="col-xs-6 col-md-2">
                                                               回复回传数
                        </div>
                        <div class="col-xs-6 col-md-2">
                                                               成功回传数
                        </div>
                        
                    </div>
             <#list typeList as item>    
                <#if item.type=="source">
                    <#if item.typeValue==101>
                       <#assign   sourceName="惠商机"/>
                    <#elseif item.typeValue==102>
                       <#assign   sourceName="百度"/>
                    <#elseif item.typeValue==103>
                       <#assign   sourceName="360"/>
                    <#elseif item.typeValue==104>
                       <#assign  sourceName="平台自增长"/>
                    <#elseif item.typeValue==106>
                       <#assign  sourceName="平安好车主"/>   
                    <#elseif item.typeValue==1>
                       <#assign   sourceName="PC"/>
                    <#elseif item.typeValue==2>
                       <#assign   sourceName="IOS"/>
                    <#elseif item.typeValue==3>
                       <#assign   sourceName="Android"/>
                    <#elseif item.typeValue==4>
                       <#assign  sourceName="M站"/>
                    <#elseif item.typeValue==5>
                       <#assign  sourceName="H5"/>
                    <#elseif item.typeValue==6>
                       <#assign  sourceName="H5"/>
                    <#elseif item.typeValue==7>
                       <#assign  sourceName="小程序"/>   
                   <#elseif item.typeValue==8>
                       <#assign  sourceName="活动"/>        
                    <#elseif item.typeValue==998>
                       <#assign  sourceName="后台创建"/>
                    <#elseif item.typeValue==999>
                       <#assign  sourceName="冷启动"/> 
                    </#if>
                    <div class="row"> 
                        <div class="col-xs-6 col-md-1">
                        ${sourceName}
                        </div>
                        <div class="col-xs-6 col-md-1">
                            <strong>${item.questionCount}</strong>
                        </div>
                        <div class="col-xs-6 col-md-1">
                            <strong>${item.answerCount}</strong>
                        </div>
                        <div class="col-xs-6 col-md-1">
                            <strong>${item.remark1}</strong>
                        </div>
                        <div class="col-xs-6 col-md-1">
                            <strong>${item.validQuestionCount}</strong>
                        </div>
                        <div class="col-xs-6 col-md-2">
                            <strong>${item.remark4}</strong>
                        </div>
                        <#if item.typeValue==101 ||item.typeValue==102 ||item.typeValue==103>
                        <div class="col-xs-6 col-md-2">
                            <strong>${item.remark2}</strong>
                        </div>
                        <div class="col-xs-6 col-md-2">
                            <strong>${item.remark3}</strong>
                        </div>
                        </#if>
                        
                    </div>        
                </#if>
             </#list>   
        </div>
    </div>
</div>

</#if>