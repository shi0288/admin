<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">系统管理</a></li>
        <li class="active">菜单列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/system/jobList" method="post">
            <div class="row">
               <div class="col-md-3">
                    <input type="text" class="form-control" value="${(task.jobDetailName)!''}" name="jobDetailName"
                           placeholder="任务名称"/>
                </div> 
                <!--<div class="col-md-3">
                     <select name="jobGroupName" class="form-control">
                          <option value="">任务组名</option>
                    <#if (menuList)??>
                        <#list  menuList as menu>
                            <option value="${(menu.mid)!''}">${(menu.menuName)!''}</option>
                        </#list>
                    </#if>
                     </select>
                </div>-->
                <div class="col-md-3">
                      <select name="isOpen" class="form-control">
                          <option value="">是否开启(全部)</option>
                          <option value="1"
                        <#if task.isOpen??&& task.isOpen==1>
                                selected="selected"
                        </#if>
                        >是
                        </option>
                        <option value="0"
                        <#if task.isOpen??&& task.isOpen==0>
                                selected="selected"
                        </#if>
                        >否
                        </option>
                      </select>                    
                </div>
            </div>
            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
                <div class="col-md-1">
                 <button waitingLoad type="button"  onclick="addJob()"  class="btn btn-primary">添加任务</button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>任务ID</th>
                <th>任务名</th>                         
                <th>任务组名</th>
                <!--<th>任务类名</th>
                <th>任务方法名</th>-->
                <th>任务时间</th>
                <!--<th>是否开启</th>-->
                <th>执行状态</th>
                <th>描述</th>               
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/system/jobListCom.ftl" />
            </tbody>
        </table>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
