<#if menuOperateList?? >
    <#list  menuOperateList as e>
    <tr>
        <td>${(e.mid)!''}</td>
        <td> ${(e.menuName)!''}</td>      
        <td>${(e.operateName)!''}</td>          
        <td> <a  class="btn btn-xs btn-primary"  href="./toEditMenu?mid=${(e.mid)!''}" "  >编辑</a></td> 
    </tr>
    </#list>
</#if>