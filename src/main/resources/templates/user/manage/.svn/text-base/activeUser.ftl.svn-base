<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">用户管理</a></li>
        <li class="active">参加活动用户信息</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/manage/updateActiveUser" method="post" id="filter">
            <input type="hidden" name="id" value="${(userApply.id)!''}"/>

            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">姓名:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled  name="userName"
                           value="${(userApply.userName)!''}"/>
                </div>
            </div>            
            
            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">性别:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled  name="gender"
                           value="<#if userApply.gender==0>男<#else>女</#if>"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">城市:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled  name="city"
                           value="${(userApply.city)!''}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-2 control-label">手机号码:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.phone)!''}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-2 control-label">微信号:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.weixin)!''}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-2 control-label">电子邮箱:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.email)!''}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="expert_name" class="col-md-2 control-label">是否有车:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled  
                           value="<#if userApply.hascar==0>否<#else>是</#if>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">您的爱车:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.car)!''}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-2 control-label">熟悉车型:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.begoodCar)!''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">职业:</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" disabled value="${(userApply.occupation)!''}"/>
                </div>
            </div>
            
             <div class="form-group">
                <label class="col-md-2 control-label">身份证照片:</label>
                <#list images as image>
                   <div class="col-xs-4 col-md-2">
                     <img src="${(image)!''}" 
                          class="img-responsive img-thumbnail" alt="图片无法访问">                          
                   </div>
                </#list>                
            </div>
            
            <#if userApply.user_type==2>
              <div class="form-group">
                  <label class="col-md-2 control-label">自我介绍:</label>
                  <div class="col-md-8 pub_content" >
                    <textarea class="form-control" disabled  rows="5">${(userApply.intro)!''}</textarea>
                  </div>
              </div>
              
              <div class="form-group">
                  <label class="col-md-2 control-label">获奖情况:</label>
                  <div class="col-md-3">
                      <input type="text" class="form-control" disabled value="${(userApply.prize)!''}"/>
                  </div>
              </div>
            </#if>
            <div class="form-group">
                  <label class="col-md-2 control-label">状态:</label>
                  <div class="col-md-3">
                      <select name="status" class="form-control">
                            <#if (statusMap)??>
                               <#list statusMap.keySet() as key>
                                <option <#if (userApply.status)?? && (userApply.status)==key > selected="selected"</#if> value="${key}">${statusMap.get(key)}</option>
                               </#list>
                            </#if>
                         </select>
                  </div>
              </div>
              
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_update_active_user">保存</button>
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
