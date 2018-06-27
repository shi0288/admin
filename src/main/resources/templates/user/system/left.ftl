<div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
    <div class="list-group">


        <a waitingLoad href="${base}/user/system/userList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='userList'>active</#if>">用户列表</a>
        <a waitingLoad href="${base}/user/system/menuList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='menuList'>active</#if>">菜单列表</a>
        <a waitingLoad href="${base}/user/system/operateList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='operateList'>active</#if>">功能列表</a>
        <!--<a waitingLoad href="${base}/user/manage/specialList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='specialList'>active</#if>">特约专家</a>
        <a waitingLoad href="${base}/user/manage/specialExpertList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='specialExpertList'>active</#if>">专家侧边栏</a>
        <a waitingLoad href="${base}/user/manage/specialModelList" class="list-group-item <#if (urlObj.levelTwo)?? && urlObj.levelTwo=='specialModelList'>active</#if>">标兵侧边栏</a>
       -->   
    </div>
</div>