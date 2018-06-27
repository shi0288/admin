<#if list??>
    <#list list as item>
        <tr>
            <td>${item_index+1}</td>
            <td>${item.answerCount}</td>
            <td>${item.questionId}</td>
            <td>${item.user.userInfo.data.nick_name}</td>           
            <td>${item.uid}</td>
            <td>${item.content}</td>
            <td><a href="http://ask.qichedaquan.com/detail/${item.key}">跳转</a></td>
        </tr>
    </#list>
</#if>