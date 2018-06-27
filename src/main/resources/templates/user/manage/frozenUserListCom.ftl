<#if (pager.list)?? >
    <#list  pager.list as e>
    <tr class="tr_body">
        <td>${(e.uid)!''}</td>
        
        <td> <#if e.type==2 || e.type==3>${(e.expert_name)!''}
        <#else>${(e.userInfo.data.nick_name)!''}</#if></td>
        <td> ${conver('userType',(e.type)!'')}</td>
        
        <td> ${(e.forbbiden_user_name)!''}</td>
        <td> <#if (e.forbbiden_time)!''>${((e.forbbiden_time?number*1000)?number_to_datetime)!''}</#if></td>
        <td> <#if (e.released_at)!''>
               <#if e.released_at==0>永久<#else>${((e.released_at?number*1000)?number_to_datetime)!''}</#if>
             </#if>
        </td>
        
        <td>${(e.userInfo.data.mobile)!''}</td>
        <td>${(e.forbbiden_reason)!''}</td>
        <td>
             <button type="button" tagVal="${(e.uid)!''}"  taskName="false"
                                    class="btn btn-xs btn-primary set_frozen">
                                解禁
            </button>
        </td>
    </tr>
    </#list>
</#if>