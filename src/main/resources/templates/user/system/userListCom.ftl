<#if userlist?? >
    <#list  userlist as e>
    <tr>
        <td>${(e.uid)!''}</td>
        <td> ${(e.userName)!''}</td>
        <td> <#if e.password==''>域账户<#else>后台添加</#if></td>
        <td> <a  class="btn btn-xs btn-primary"  href="./toEditUserOperate?uid=${(e.uid)!''}"  >编辑</a></td>
       <!-- <td>
            <a  class="btn btn-xs btn-primary"  href="./expertEdit?uid=${(e.uid)!''}"  >编辑</a>
            <a  class="btn btn-xs btn-primary"  href="http://ask.qichedaquan.com/user/${e.uid}"  target="_blank"   >查看他的主页</a>
            <#if e.status==0 || e.status==1>
                <a  href="javascript:void(0);"  tagVal="${(e.uid)!''}" frozenType="frozen" class="btn btn-xs btn-primary frozen_user"  >隐藏身份</a>
            <#elseif e.status==-1>
                <a  href="javascript:void(0);"  tagVal="${(e.uid)!''}" frozenType="thaw" class="btn btn-xs btn-warning frozen_user"  >恢复身份</a>
            </#if>
        </td>-->
    </tr>
    </#list>
</#if>