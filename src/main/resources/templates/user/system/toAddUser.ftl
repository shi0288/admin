<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">系统管理</a></li>
        <li class="active">用户管理</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/system/addUser" method="post" id="filter">
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">用户名:</label>
                <div class="col-md-3">
                       <input  name="userName" id="userName" type="text"
                            style="height: 34px;"/>
                </div>
            </div> 
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">密码:</label>
                <div class="col-md-3">
                       <input  name="password" id="password" type="text"
                            style="height: 34px;"/>
                </div>
            </div> 
           <!-- <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">用户身份:</label>
                <div class="col-md-3">
                     <select name="role" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">超级管理员</option>
                        <option value="2">管理员</option>
                        <option value="20">运营</option>
                        <option value="90">实习生</option>
                        <option value="99">房客</option>                        
                    </select>  
                </div>
            </div> -->
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">用户来源:</label>
                <div class="col-md-3">
                     <select name="source" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">域账户</option>                       
                    </select>  
                </div>
            </div>           
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_add_user">保存</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
