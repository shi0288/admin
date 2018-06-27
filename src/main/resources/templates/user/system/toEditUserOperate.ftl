<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">系统管理</a></li>
        <li class="active">用户列表</li>
    </ol>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>
                 <label class="checkbox-inline" >
                        <input type="checkbox"  id="checked_all" name="checked_all" >全选
                 </label>
                </th>
                <th>菜单ID</th>
                <th>菜单名称</th>                
            </tr>
            </thead>
            <tbody>
               <input type="hidden"  id="uid" value="${uid}" >全选
               <#if userPowerList??>
                   <#list userPowerList as e>
                   <tr>
                      <td>
                           <label class="checkbox-inline">
                              <input type="checkbox" data="select_power_${(e.powerId)!''}" id="${(e.powerId)!''}" 
                              <#if (e.uid)!''>
                                 checked
                              </#if>
                              name="check_powerId"  value="${(e.powerId)!''}"> 
                           </label>
                      </td>
                      <td>${(e.powerId)!''}</td>
                      <td> ${(e.powerName)!''}</td>
                   </tr>   
                   </#list>
               </#if>
            </tbody>
        </table>
        <div class="col-xs-2 col-md-1">
              <button type="button"  onclick="saveUserPower()"  class="btn btn-primary">保存</button>
        </div>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
