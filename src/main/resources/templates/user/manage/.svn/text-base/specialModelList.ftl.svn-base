<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">用户管理</a></li>
        <li class="active">标兵侧边栏</li>
    </ol>
    <div class="well">
        <button type="button" tagVal="specialModel" id="but_save_special" class="btn btn-primary" >
            保存
        </button>
    </div>

    <div class="row">
        <div class=" col-md-6 ">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text" class="form-control" id="findModelById" placeholder="用户ID" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text" class="form-control" id="findModelByName" placeholder="标兵昵称" />
                    </div>
                </div>
            </div>
            <div class="well table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>标兵昵称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="user_pool">
                    <#include  "/user/manage/specialModelListCom.ftl" />
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-md-6">
            <div class="well table-responsive">
                <table class="table right_list">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>标兵昵称</th>
                        <th>排序</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="list_pool">
                    <#if (specialList)?? >
                        <#list  specialList as e>
                        <tr class="user_body">
                            <td class="user_index">${(e_index+1)!''}</td>
                            <td class="user_id" tagVal="${(e.uid)!''}">${(e.userInfo.data.nick_name)!''}</td>
                            <td>
                                <button class="btn btn-primary btn-xs" name="topMove">
                                    <span class="glyphicon glyphicon-arrow-up"></span>
                                </button>
                                <button class="btn btn-primary btn-xs" name="bottomMove">
                                    <span class="glyphicon glyphicon-arrow-down"></span>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-xs user_delete">
                                    <span class="glyphicon glyphicon-minus"></span>
                                </button>
                            </td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/manage.js"></script>
</body>
</html>
