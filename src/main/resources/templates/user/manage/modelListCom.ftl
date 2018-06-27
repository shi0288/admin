<#if (pager.list)?? >
    <#list  pager.list as e>
    <tr class="tr_body">
        <td>${(e.uid)!''}</td>
        <td> ${(e.userInfo.data.nick_name)!''}</td>
        <td>
            <#list e.categories as cate  >
                <#if cate_has_next>
                ${(cate.name)!''},
                <#else>
                ${(cate.name)!''}
                </#if>
            </#list>
        </td>
        <td>${(e.userInfo.data.mobile)!''}</td>
        <td>${(e.code)!''}</td>
        <td>
            <a  class="btn btn-xs btn-primary"  href="./expertEdit?uid=${(e.uid)!''}" >编辑</a>
            <a  class="btn btn-xs btn-primary"  href="http://ask.qichedaquan.com/user/${e.uid}"  target="_blank" >查看他的主页</a>
            <a  class="btn btn-xs btn-primary set_expert"  href="javascript:void(0);"   tagVal="${(e.uid)!''}" >设置专家</a>
            <#if e.status==0 || e.status==1>
                <a  href="javascript:void(0);"  tagVal="${(e.uid)!''}" frozenType="frozen" class="btn btn-xs btn-primary frozen_user"  >隐藏身份</a>
            <#elseif e.status==-1>
                <a  href="javascript:void(0);"  tagVal="${(e.uid)!''}" frozenType="thaw" class="btn btn-xs btn-warning frozen_user"  >恢复身份</a>
            </#if>
            <#if e.enableDistribute==0>
                <a class="btn btn-xs btn-primary enable_distribute" type="join" tagVal="${(e.uid)!''}">加入自动推送</a>   
            <#else>
               <a class="btn btn-xs btn-warning enable_distribute"  type="quit" tagVal="${(e.uid)!''}">退出自动推送</a>   
            </#if>
        </td>
    </tr>
    </#list>
</#if>