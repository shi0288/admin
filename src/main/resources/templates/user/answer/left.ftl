<div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
    <div class="list-group">
        <a waitingLoad href="${base}/user/question/contentList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='contentList'>active</#if>">问题管理</a>
        <a waitingLoad href="${base}/user/question/rubbishList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='rubbishList'>active</#if>">问答回收站</a>
        <a waitingLoad href="${base}/user/question/questionAdd" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='questionAdd'>active</#if>">创建问题</a>
        <a waitingLoad href="${base}/user/answer/answerList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='answerList'>active</#if>">回复管理</a>
        <a waitingLoad href="${base}/user/question/verifyList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='verifyList'>active</#if>">待审核问题</a>
    </div>
</div>