<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">系统管理</a></li>
        <li class="active">菜单列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/system/menuList" method="post">
            <div class="row">
               <div class="col-md-3">
                    <input type="text" class="form-control" value="${(menu.menuName)!''}" name="menuName"
                           placeholder="菜单名称"/>
                </div> 
                <!--<div class="col-md-3">
                     <select name="operateType" class="form-control">
                          <option value="">所属菜单</option>
                    <#if (menuList)??>
                        <#list  menuList as menu>
                            <option value="${(menu.mid)!''}">${(menu.menuName)!''}</option>
                        </#list>
                    </#if>
                     </select>
                </div>-->
            </div>
            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
                <div class="col-md-1">
                 <button waitingLoad type="button"  onclick="addMenu()"  class="btn btn-primary">添加菜单</button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>菜单ID</th>
                <th>菜单名</th>
                          
                <th>关联功能</th>
                <!--<th>联系方式</th>-->
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/system/menuListCom.ftl" />
            </tbody>
        </table>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
