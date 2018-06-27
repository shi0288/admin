<!doctype html>
<html>
<head>
    <title>问答管理后台</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<#include "user/boot_css.ftl"/>
</head>
<body class="main_body">
<#include "user/boot_sub.ftl"/>
<div class="container-fluid main">

    <div class="panel panel-primary">
        <div class="panel-body">
            <ol class="breadcrumb">
                <li class="active">操作日志</li>
            </ol>
            <div class="well">
                <form class="table-filter" action="${base}/user/adminLog" method="post">
                    <div class="row">
                        <div class="col-md-3">
                            <input type="text" class="form-control" value="${(adminLogs.username)!''}" name="username"  placeholder="用户名" />
                        </div>
                        <div class="col-md-3">
                            <select name="module" class="form-control  model_sel">
                                <option value="">模块（全部）</option>
                            <#if (moduleMap)??>
                                <#list moduleMap.keySet() as key>
                                    <option <#if (adminLogs.module)?? && adminLogs.module==key > selected="selected"</#if> value="${key}">${moduleMap.get(key)}</option>
                                </#list>
                            </#if>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select name="action" class="form-control  model_sel">
                                <option value="">操作类型（全部）</option>                              
                                <option <#if (adminLogs.action)?? && adminLogs.action==1 > selected="selected"</#if> value="1">封禁用户</option>
                            </select>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10px;">
                        <div class="col-md-1">
                            <button waitingLoad type="submit"  class="btn btn-primary">筛选</button>
                        </div>
                    </div>
                </form>
            </div>

        <#if  (pager.list)??>
            <#import "/user/boot_pager.ftl" as p>
            <#assign parameterMap = {"username":(adminLogs.username)!'',"module":(adminLogs.module)!'', "action":(adminLogs.action)!''} />
            <@p.pager pager = pager baseUrl = "${base}/user/adminLog" parameterMap = parameterMap/>
        </#if>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>用户</th>
                        <th>模块</th>
                        <th>类型</th>
                        <th>描述</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if  (pager)??>
                        <#list pager.list as e>
                        <tr>
                            <td>${((e.created_at?number*1000)?number_to_datetime)!''}</td>
                            <td>${e.username}</td>
                            <td>${conver('logModule',e.module)}</td>
                            <td>${conver('logAction',e.action)}</td>
                            <td>${e.content}</td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        <#if  (pager.list)??>
            <#assign parameterMap = {"username":(adminLogs.username)!'',"module":(adminLogs.module)!'', "action":(adminLogs.action)!''} />
            <@p.pager pager = pager baseUrl = "${base}/user/adminLog" parameterMap = parameterMap/>
        </#if>
        </div>
    </div>
</div>
<#include "user/boot_js.ftl"/>
<div class="mask_area">
    <div class="mask"></div>
</div>
<div id="loading">
    <div id="loading-center">
        <div id="loading-center-absolute">
            <div class="object" id="object_one"></div>
            <div class="object" id="object_two"></div>
            <div class="object" id="object_three"></div>
            <div class="object" id="object_four"></div>
            <div class="object" id="object_five"></div>
            <div class="object" id="object_six"></div>
            <div class="object" id="object_seven"></div>
            <div class="object" id="object_eight"></div>
        </div>
    </div>
</div>

</body>
</html>
