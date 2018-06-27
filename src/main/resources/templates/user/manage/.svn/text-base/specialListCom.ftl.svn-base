<#if (pager.list)?? >
    <#list  pager.list as e>
    <tr>
        <td>${(e.uid)!''}</td>
        <td>${(e.userInfo.data.nick_name)!''}</td>
        <td>${(e.expert_name)!''}</td>
        <td>
            <button class="btn btn-primary btn-xs add_list" tagVal="${(e.uid)!''}"
                    tagName="${(e.expert_name)!''}">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </td>
    </tr>
    </#list>
</#if>