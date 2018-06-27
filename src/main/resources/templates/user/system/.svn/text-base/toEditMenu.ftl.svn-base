<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">系统管理</a></li>
        <li class="active">修改菜单</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/system/saveMenu" method="post" id="filter">
            <input type="hidden" value="${menu.mid}" name="mid" id="mid"/>

            <div class="form-group">
                <label for="menuName" class="col-md-2 control-label">菜单名称:</label>
                <div class="col-md-3">
                    <input type="text" name="menuName"  value="${(menu.menuName)!''}" class="form-control"
                           disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="menu_select" class="col-md-2 control-label">所属菜单:</label>
                <div class="col-md-3">
                    <select id="province_select" name="parentId" class="form-control menu_select">
                        <option value="0">选择</option>
                    <#if (menuList)??>
                        <#list  menuList as e>
                            <option value="${(e.mid)!''}"
                                    <#if  menu.parentId==e.mid>selected</#if>>${(e.menuName)!''}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="operate_select" class="col-md-2 control-label">关联功能:</label>
                <div class="col-md-3">
                    <select id="operate_id" name="operateId" class="form-control  operate_select">
                    <#if (operateList)??>
                        <option value="0">请选择</option>
                        <#list operateList as e >
                            <option value="${e.operateId}"
                                    <#if menu.operateId==e.operateId>selected</#if>>${e.operateName}</option>
                        </#list>
                    <#else>
                        <option value="0">请先选择菜单</option>
                    </#if>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_update_menu">保存</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>

<#include  "/user/autocomplete.ftl" />
</body>
</html>
