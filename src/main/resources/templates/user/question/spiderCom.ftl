<#if (pager.list)?? >
    <#list  pager.list as e>
    

      <tr class="tr_body panel">
          <form class="form-filter " action="${base}/user/question/saveSpider" method="post" id="${e.id}">
        <td class="col-xs-1  col-md-1 group-control" >
           <input type="text" class="form-control col-xs-1  col-md-1"  name="id" value="${(e.id)!''}" readonly  >
        </td>
        
        <td class="col-xs-2  col-md-2 group-control">       
          <select name="series_id" class="form-control"> 
          <option value="">请选择</option>
          <#list series as serie>           
            <option value="${serie.seriesId}" <#if serie.seriesId==e.series_id>selected="selected" </#if>>${(serie.name)!''} </option>
          </#list> 
          </select>
        </td>  
              
        <td class="col-xs-1  col-md-1 group-control">       
          <select name="category_id" class="form-control category_select"> 
          <option value="" >请选择</option>
          <option value="1" <#if e.category_id==1>selected="selected"</#if>>买车 </option>
          <option value="3" <#if e.category_id==3>selected="selected"</#if>>用车</option>         
          </select>
        </td>
        
        <td class="col-xs-2  col-md-2 group-control">  
        <select name="sub_category_id" class="form-control sub_category_select"> 
          <option value="" >请选择</option>
          <#list cateConver(e.category_id,e.sub_category_id)  as sub_category> 
             <option value="${sub_category.categoryId}" <#if sub_category.categoryId==e.sub_category_id>selected="selected"</#if>>${sub_category.name} </option>
          </#list>                  
        </select>       
         </td>
         
        <td class="col-xs-4  col-md-4 group-control">
         <textarea class="form-control" id="content" name="content" rows="4">${(e.content)!''}</textarea>        
        </td>
        
        <td class="col-xs-2  col-md-2 group-control">
            <button type="button" class="btn btn-primary save_spider">审核通过</button>
                      
            <a  class="btn btn-xs btn-primary del_spider" tagVal="${(e.id)!''}" >删除</a>
            <a  class="btn btn-xs btn-primary look_spider"  tagVal="${e.url}" >查看问题</a>                        
        </td>
          </form>
    </tr>

  
    </#list>
</#if>