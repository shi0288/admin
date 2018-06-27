<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">系统管理</a></li>
        <li class="active">修改功能</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/system/saveOperate" method="post" id="filter">
            <input type="hidden" value="${operate.operateId}" name="operateId" id="operateId"/>

            <div class="form-group">
                <label for="operateName" class="col-md-2 control-label">功能名称:</label>
                <div class="col-md-3">
                    <input type="text" name="operateName"  value="${(operate.operateName)!''}" class="form-control"
                           />
                </div>
            </div>
            <div class="form-group">
                <label for="url" class="col-md-2 control-label">关联地址:</label>
                <div class="col-md-3">
                    <input type="text" name="url"  value="${(operate.url)!''}" class="form-control"
                           />
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-md-2 control-label">功能类型:</label>
                <div class="col-md-3">                       
                     <select name="operateType" class="form-control">
                        <option value="">请选择</option>
                        <option
                        <#if operate??&& operate.operateType==1>
                                selected="selected"
                        </#if>
                                value="1">查询
                        </option>
                        <option
                        <#if operate??&& operate.operateType==2>
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
            </div>

            <div class="form-group">
                <label for="mid_select" class="col-md-2 control-label">所属菜单:</label>
                <div class="col-md-3">
                    <select id="mid" name="mid" class="form-control mid_select">
                        <option value="0">选择</option>
                    <#if (menuList)??>
                        <#list  menuList as e>
                            <option value="${(e.mid)!''}"
                                    <#if  operate.mid==e.mid>selected</#if>>${(e.menuName)!''}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_update_operate">保存</button>
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
