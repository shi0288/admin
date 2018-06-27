<#if jobList?? >
    <#list  jobList as e>
    <tr>
        <td>${(e.id)!''}</td>
        <td> ${(e.jobDetailName)!''}</td>      
        <td>${(e.jobGroupName)!''}</td>
        <!--<td> ${(e.targetObject)!''}</td>      
        <td>${(e.methodName)!''}</td> -->  
        <td> ${(e.cron)!''}</td>      
        <!--<td>${(e.isOpen)!''}</td>  --> 
        <td> ${(e.status)!''}</td>      
        <td>${(e.desc)!''}</td>             
        <td> 
        <#if e.isOpen==1>
           <button type="button" tagVal="${(e.id)!''}" taskName="false"
                                    class="btn btn-xs btn-warning set_open">关闭
           </button>
        <#else>
          <button type="button" tagVal="${(e.id)!''}" taskName="true"
                                    class="btn btn-xs btn-primary set_open">打开
          </button>
        </#if>
          <button type="button" tagVal="${(e.id)!''}" 
                                    class="btn btn-xs btn-warning delete_job">删除
          </button>
          <button type="button" tagVal="${(e.id)!''}" 
                                    class="btn btn-xs btn-warning start_job">立刻执行
          </button>        
           <a  class="btn btn-xs btn-primary"  href="./toUpdateJob?id=${(e.id)!''}" " >修改</a>
        </td> 
    </tr>
    </#list>
</#if>