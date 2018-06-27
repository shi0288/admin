<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">用户管理</a></li>
        <li class="active">报名活动用户列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/manage/activeUserList" method="post">
            <div class="row">                
                <div class="col-md-3">
                  <div class="row"> 
                    <div class="col-xs-3  col-md-4 group-label">  <label>手机号</label>  </div>
                    <div class="col-xs-9  col-md-8 group-control"><input type="text" class="form-control" name="phone" value="${(userApply.phone)!''}"/>
                    </div>
                  </div>
                </div>
                <div class="col-md-3">
                   <div class="row"> 
                     <div class="col-xs-3  col-md-4 group-label"><label>姓名</label></div>
                     <div class="col-xs-9  col-md-8 group-control"><input type="text" class="form-control" name="userName" value="${(userApply.userName)!''}"  /></div>
                   </div>
                </div>
                <div class="col-md-3">
                   <div class="row"> 
                    <div class="col-xs-3  col-md-4 group-label"><label >微信号</label></div>
                    <div class="col-xs-9  col-md-8 group-control"><input type="text" class="form-control" name="weixin" value="${(userApply.weixin)!''}"  />
                    </div>
                   </div>
                </div>
                <div class="col-md-3">
                    <div class="row"> 
                     <div class="col-xs-4  col-md-4 group-label"><label>状态</label></div>
                     <div class="col-xs-8  col-md-8 group-control">
                        <select name="status" class="form-control">
                            <#if (statusMap)??>
                               <#list statusMap.keySet() as key>
                                <option <#if (userApply.status)?? && (userApply.status)==key > selected="selected"</#if> value="${key}">${statusMap.get(key)}</option>
                               </#list>
                            </#if>
                         </select>
                     </div>
                    </div>
                </div>
                
            </div>
               
           
            <div class="row" style="padding-top: 10px;">
               <div class="col-md-3">
                    <div class="row"> 
                     <div class="col-xs-4  col-md-4 group-label"><label>活动类型</label></div>
                     <div class="col-xs-8  col-md-8 group-control">
                         <select name="user_type" class="form-control">
                            <option value="">全部</option>
                            <option value="1"
                                <#if (userApply.user_type)??&& userApply.user_type==1>
                                selected="selected"
                               </#if>>标兵
                            </option>
                            <option value="2"
                                <#if (userApply.user_type)??&& userApply.user_type==2>
                                selected="selected"
                               </#if>>专家
                            </option>
                         </select>
                     </div>
                    </div>
                </div>
            
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
            </div>
        </form>
    </div>

<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"phone":(userApply.phone)!,"userName":(userApply.userName)!,"status":(userApply.status)!,"weixin":(userApply.weixin)!} />
    <@p.pager pager = pager baseUrl = "${base}/user/manage/activeUserList" parameterMap = parameterMap/>
</#if>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>微信号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/manage/activeUserListCom.ftl" />
            </tbody>
        </table>
    </div>

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/manage/normalList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/manage.js"></script>
</body>
</html>
