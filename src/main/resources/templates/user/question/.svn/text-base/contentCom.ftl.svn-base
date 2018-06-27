<#if  (pager.list)??>
    <#list pager.list as e>
      <div class="panel panel-default" >      
        <div class="panel-heading">
            <div class="container-fluid content-block">
                <div class="row">
                    <div class="col-xs-6 col-md-2"><strong>${((e.createdAt?number*1000)?number_to_datetime)!''}</strong></div>
                    <div class="col-xs-6 col-md-2" style="padding-right: 0px;">
                        <a href="javascript:void(0);" class="btn btn-xs btn-danger del_question"
                           tagVal="${(e.questionId)!''}">删除</a>
                        <#if e.isGood==1>
                            <button type="button" tagVal="${(e.questionId)!''}" id="set_good_${(e.questionId)!''}" taskName="false"
                                    class="btn btn-xs btn-warning set_good">
                                取消精华
                            </button>
                        <#else>
                            <button type="button" tagVal="${(e.questionId)!''}" id="set_good_${(e.questionId)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_good">
                                设为精华
                            </button>
                        </#if>
                        <a waitingLoad href="/user/question/toUpdateQuestion?questionId=${(e.questionId)!''}"
                           class="btn btn-xs btn-primary">修改</a>
                        <a waitingLoad href="http://ask.qichedaquan.com/detail/${e.key}"
                           class="btn btn-xs btn-primary">详情</a>    
                    </div>                    
                    <div class="col-xs-6 col-md-2">用户名:<strong><#if e.user.type==2 || e.user.type==3>${(e.user.expert_name)!''}<#else>${(e.user.userInfo.data.nick_name)!''}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">提问类型:<strong>${(e.category.name)!''}</strong></div>
                    <div class="col-xs-6 col-md-2">
                        关联车型:<strong><#if (e.series)??>
                        <#list e.series as series >${(series.name)!''}、</#list></#if></strong>
                    </div>
                    <div class="col-xs-6 col-md-1">类型：<strong>
                    <#if e.type==1>分享<#else>
                    <#if (e.attaches)?? && (e.attaches?size>0)>                   
                       <#if questionType==3>投票
                       <#elseif questionType==4>PK
                       <#elseif questionType==5>分享
                       <#elseif questionType==6>活动                      
                       <#else>提问</#if>
                    <#else>提问</#if>
                    </#if>
                    </strong></div>
                    <div class="col-xs-6 col-md-1">
                        位置:<strong>${(e.city.name)!''}</strong>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-md-2">用户ID:<strong class="btn" data-clipboard-text="${(e.uid)!''}" >${(e.uid)!''}</strong></div>                   
                    <div class="col-xs-6 col-md-2">来源:<strong><#if e.source==6>H5<#else>${conver('source',e.source)}</#if></strong></div>
                    <div class="col-xs-6 col-md-2">
                        <#if e.isTop==1>
                            <button type="button" tagVal="${(e.questionId)!''}" taskName="false"
                                    class="btn btn-xs btn-warning set_top">
                                取消置顶 ${e.topSort}</button>
                        <#else>
                            <button type="button" tagVal="${(e.questionId)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_top">
                                设为置顶
                            </button>
                        </#if>
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
                        <a waitingLoad href="/user/answer/answerAdd?questionId=${(e.questionId)!''}" class="btn btn-xs btn-primary">回答</a>
                        <a waitingLoad href="/user/answer/answerList?questionId=${(e.questionId)!''}"
                           class="btn btn-xs btn-primary">看回答</a>
                         <a class="btn btn-xs btn-primary push_question" tagVal="${(e.questionId)!''}">推送</a>   
                    </div>
                    <div class="col-xs-6 col-md-2">回答数：<strong>${(e.answerCount)!''}<#if (e.is_html)==1> 图文帖</#if></strong></div>
                    <div class="col-xs-6 col-md-2">身份:<strong><#if (e.user.isOfficial) &gt; 0>官方<#else>${conver('userType',e.user.type)}</#if></strong></div>
                              
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
            【话题编号】<span class="btn" data-clipboard-text="${(e.questionId)!''}">${(e.questionId)!''}</span></p>
            <#if (e.title)!''><p>【标题】${(e.title)!''}</p></#if>
            <p>【内容】${(e.content)!''}</p>
            <p> 
            <#if (e.attaches)?? && (e.attaches?size>0)>  
               <div class="row">                                           
                 <#list e.attaches as att>                  
                    
                        <#if att.fileType==0>
                            <div class="col-xs-4 col-md-2">
                                <img src="${(att.file)!''}"
                                     class="img-responsive img-thumbnail" alt="图片无法访问">
                            </div>
                        </#if>
                     
                    <#if att.fileType==3 || att.fileType==4>
                        <div class="row"  >                            
                          <div class="col-xs-6 col-md-1 btn-group"  data-toggle="buttons">
                              <label class="radio-inline set_tp" tagVal="${(att.attachId)}">
                                  <input style="padding-left:10px;" type="radio"   name="${att.attachId}"
                                   <#if att.fileType==3> checked </#if>
                                   value="1">投票
                              </label>
                           </div>
                          <div class="col-xs-6 col-md-1 btn-group"  data-toggle="buttons">
                              <label class="radio-inline set_tp" tagVal="${(att.attachId)}">
                                  <input type="radio"  name="${att.attachId}"
                                     <#if att.fileType==4> checked </#if>
                                   value="2">PK
                              </label>
                          </div>                                                  
                        </div>
                          <#assign json=att.file?eval />
                        <#list json as itemfile>
                            <div class="row" ">
                                <div  class="col-xs-12 col-md-6">投票内容:<strong>${(itemfile.text)!''}</strong></div>
                                <div  class="col-xs-6 col-md-2">支持人数:<strong>${itemfile.count}</strong></div>
                                <div  class="col-xs-6 col-md-2">比例:<strong>${itemfile.rate}</strong></div>
                            </div>
                        </#list>                           
                     </#if>
                    
                       
                 </#list>     
                 </div>                          
            </#if>
            </p>
        </div>
    </div>
    </#list>
</#if>

