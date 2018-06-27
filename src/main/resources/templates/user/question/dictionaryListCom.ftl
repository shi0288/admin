<#if (pager.list)?? >
    <#list  pager.list as e>
    <tr class="tr_body">
        <td>${(e.id)!''}</td>
        <td> ${(e.text)!''}</td>
        <td>
           <a href="javascript:void(0);" class="btn btn-xs btn-danger del_keyWord"
                           tagVal="${(e.id)!''}">删除</a>            
        </td>
    </tr>
    </#list>
</#if>