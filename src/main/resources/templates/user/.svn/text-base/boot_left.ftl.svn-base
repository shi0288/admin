<div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
    <div class="list-group">

    <#if (menu_children)??>
        <#list  menu_children as menu>
            <#assign   child_menu_url=menu.url/>
            <#if menu.parentId==cur_parent>
                <#list  menu.url ?split("?") as url>
                  <#if url_index==0><#assign   child_menu_url=url/></#if>
                </#list>
                <a waitingLoad href="${base}${menu.url}" class="list-group-item 
                <#if getRequestUrl==child_menu_url>active</#if>">
                ${menu.powerName}</a>
            </#if>
        </#list>
    </#if>
        <#--<a waitingLoad href="${base}/user/question/contentList"-->
           <#--class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='contentList'>active</#if>">问题管理</a>-->
        <#--<a waitingLoad href="${base}/user/question/rubbishList"-->
           <#--class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='rubbishList'>active</#if>">问答回收站</a>-->
        <#--<a waitingLoad href="${base}/user/question/questionAdd"-->
           <#--class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='questionAdd'>active</#if>">创建问题</a>-->
        <#--<a waitingLoad href="${base}/user/answer/answerList"-->
           <#--class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='answerList'>active</#if>">回复管理</a>-->
        <#--<a waitingLoad href="${base}/user/question/verifyList"-->
           <#--class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='verifyList'>active</#if>">待审核问题</a>-->
    </div>
</div>