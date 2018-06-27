<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">用户管理</a></li>
        <li class="active">用户列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/manage/normalList" method="post">
            <div class="row">
                <div class="col-md-3">
                    <input type="text" class="form-control" name="uid" value="${(user.uid)!''}" placeholder="用户ID" />
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="nickName" value="${(nickName)!''}" placeholder="昵称" />
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
                    </select>
                   </div>
                 </div> 
                </div>
                
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>

                <div class="col-md-1">
                    <button type="button" id="reset_user_info"  class="btn btn-primary">同步</button>
                </div>
            </div>
        </form>
    </div>

<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"uid":(user.uid)!,"userType":(userType)!,"nickName":(nickName)!} />
    <@p.pager pager = pager baseUrl = "${base}/user/manage/normalList" parameterMap = parameterMap/>
</#if>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>昵称</th>
                <th>身份</th>
                <th>联系方式</th>
                <th>邀请码</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/manage/normalListCom.ftl" />
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
