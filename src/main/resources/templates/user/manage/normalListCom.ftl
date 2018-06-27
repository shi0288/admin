<#if (pager.list)?? >
    <#list  pager.list as e>
    <tr class="tr_body">
        <td>${(e.uid)!''}</td>
        <td> <#if e.type==2 || e.type==3>${(e.expert_name)!''}
        <#else>${(e.userInfo.data.nick_name)!''}</#if></td>
        <td> ${conver('userType',(e.type)!'')}</td>
        <td>${(e.userInfo.data.mobile)!''}</td>
        <td>${(e.code)!''}</td>
        <td>
            <a  class="btn btn-xs btn-primary"  href="./expertEdit?uid=${(e.uid)!''}" >编辑</a>
            <a  class="btn btn-xs btn-primary"  href="http://ask.qichedaquan.com/user/${e.uid}"  target="_blank" >查看他的主页</a>
            <#if e.type lt 1 >
                <a  class="btn btn-xs btn-primary set_model"  href="javascript:void(0);"  tagVal="${(e.uid)!''}"  >设置标兵</a>
                <a  class="btn btn-xs btn-primary set_expert"  href="javascript:void(0);"   tagVal="${(e.uid)!''}" >设置专家</a>
                <a  class="btn btn-xs btn-primary set_adviser"  href="javascript:void(0);"  tagVal="${(e.uid)!''}"  >设置车顾问</a>
            <#elseif e.type==3>
                <a  class="btn btn-xs btn-primary update_adviser"  href="javascript:void(0);"  tagVal="${(e.uid)!''}"  >更新车顾问</a>
            </#if>
            
        </td>
    </tr>
    </#list>
</#if>