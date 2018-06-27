<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">系统管理</a></li>
        <li class="active">菜单管理</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/system/createMenu" method="post" id="filter">
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">菜单名称:</label>
                <div class="col-md-3">
                       <input  name="menuName" id="menuName" type="text"
                            style="height: 34px;"/>
                </div>
            </div> 
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">父级菜单:</label>
                <div class="col-md-3">
                     <select name="parentId" class="form-control menu_select">
                          <option value="0">请选择</option>
                    <#if (menuList)??>
                        <#list  menuList as menu>
                            <option value="${(menu.mid)!''}">${(menu.menuName)!''}</option>
                        </#list>
                    </#if>
                     </select>            
                </div>
            </div>
             <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">关联功能:</label>
                <div class="col-md-3">
                    <select name="operateId" class="form-control operate_select">
                        <option value="0">关联功能</option>
                    </select>
                </div>
            </div>           
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_add_menu">保存</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
