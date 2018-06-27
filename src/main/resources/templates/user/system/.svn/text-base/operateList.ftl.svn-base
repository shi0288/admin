<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">系统管理</a></li>
        <li class="active">功能列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/system/operateList" method="post">
        
            <div class="row" >
                <div class="col-md-3">
                    <input type="text" class="form-control" value="${(operate.operateName)!''}" name="operateName"
                           placeholder="功能名称"/>
                </div> 
                <div class="col-md-3">
                    <select name="userType" class="form-control">
                        <option value="">用户身份</option>
                    </select>
                    
                </div>
                <div class="col-md-3">
                    <select name="operateType" class="form-control">
                        <option value="">功能类型</option>
                        <option
                        <#if operate??&& operate.operateType==1>
                                selected="selected"
                        </#if>
                                value="1">查询
                        </option>
                        <option
                        <#if perate??&& operate.operateType==2>
                                selected="selected"
                        </#if>
                                value="2">新增
                        </option>
                        <option
                        <#if operate??&& operate.operateType==3>
                                selected="selected"
                        </#if>
                                value="3">修改
                        </option>
                        <option
                        <#if perate??&& operate.operateType==4>
                                selected="selected"
                        </#if>
                                value="4">删除
                        </option>
                    </select>                   
                </div> 
                <div class="col-md-3">
                     <select name="operateType" class="form-control">
                          <option value="">所属菜单</option>
                    <#if (menuList)??>
                        <#list  menuList as menu>
                            <option value="${(menu.mid)!''}">${(menu.menuName)!''}</option>
                        </#list>
                    </#if>
                     </select>
                </div>                            
            </div>
            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
                <div class="col-md-1">
                 <button waitingLoad type="button"  onclick="addOperate()"  class="btn btn-primary">添加功能</button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>功能ID</th>
                <th>功能名称</th>
                <th>所属菜单</th>
                <!--<th>擅长问题</th>
                <th>联系方式</th>-->
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/system/operateListCom.ftl" />
            </tbody>
        </table>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
