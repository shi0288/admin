<#if list??>
    <#list list as item>
        <tr>
            <td>${item_index+1}</td>
            <td>${item.cityName}</td>
            <td>${item.proviceName}</td>
            <td>${item.questionCount}</td>                        
            
        </tr>
    </#list>
</#if>