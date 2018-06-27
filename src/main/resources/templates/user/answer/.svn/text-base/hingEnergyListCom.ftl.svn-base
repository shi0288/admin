<#if list?? >
    <#list  list as e>
    <tr class="tr_body">
        <td>${(e.uid)!''}</td>        
        <td> ${(e.count)!''}</td>
        <td> <input style="z-index:0" id="money_${(e.uid)!''}"/></td>
        <#if e.push>
          <td>已推送  </td>
        <td >
            <a class="btn btn-xs btn-primary push_message" disable>推送</a>
        </td>
        <#else>
          <td>未推送  </td>
        <td>
            <a class="btn btn-xs btn-primary  push_hing_energy" tagVal="${(e.uid)!''}" tagCount="${(e.count)!''}">推送</a>
        </td>
        </#if>
        
    </tr>
    </#list>
</#if>