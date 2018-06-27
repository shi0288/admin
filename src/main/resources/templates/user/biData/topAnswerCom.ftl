<#if list??>
    <#list list as item>
        <tr>
            <td>${item_index+1}</td>
            <td>${item.agreeCount}</td>
            <td>${item.answerId}</td>
            <td>${item.uid}</td>
            <td>${item.user.userInfo.data.nick_name}</td>            
           
            <td>${item.questionId}</td>            
            <td><a href="javascript:void(0);" class="to_question"
                           tagVal="${(item.questionId)!''}">跳转</a></td>
        </tr>
    </#list>
</#if>