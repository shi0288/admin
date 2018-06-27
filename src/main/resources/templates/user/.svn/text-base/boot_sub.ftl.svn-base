<nav class="navbar navbar-default  navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse"
                    data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="##" class="navbar-brand">问答管理后台</a></div>
        <div class="collapse navbar-collapse navbar-responsive-collapse">
            <ul class="nav navbar-nav navbar-right">
                <#if (menu_parent)??>
                    <#list  menu_parent as menu>
                        <li <#if menu.powerId==cur_parent>class="active"</#if>><a
                                waitingLoad href="${base}${menu.url}">${menu.powerName}</a></li>
                    </#list>
                </#if>
                <#--<li <#if (urlObj.levelOne)?? && urlObj.levelOne=='biData'>class="active"</#if>><a -->
                        <#--waitingLoad href="${base}/user/biData/basicBi">数据监控</a></li>-->
                <#--<li <#if (urlObj.levelOne)?? && urlObj.levelOne=='business'>class="active"</#if>><a-->
                        <#--waitingLoad href="${base}/user/business/pcBannerList">运营管理</a></li>-->
                <#--<li <#if (urlObj.levelOne)?? && urlObj.levelOne=='question'>class="active"</#if>><a-->
                        <#--waitingLoad href="${base}/user/question/contentList">内容管理</a></li>-->
                <#--<li <#if (urlObj.levelOne)?? && urlObj.levelOne=='manage'>class="active"</#if>><a-->
                        <#--waitingLoad href="${base}/user/manage/expertList">用户管理</a></li>-->
                <li class="dropdown"><a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <span
                        class="glyphicon glyphicon-user"></span> ${(account)!''} <i class="caret"></i> </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/user/adminLog">个人操作记录</a></li>
                        <li class="divider"></li>
                        <li><a waitingLoad href="${base}/user/loginout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>