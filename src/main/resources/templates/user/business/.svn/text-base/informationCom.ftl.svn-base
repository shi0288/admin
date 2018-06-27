<#if  (informationList)??>
    <#list informationList as e>
            <tr class="tr_body">
                <td class="index_number">${e_index+1}</td>
                <td>
                    <div class="form-group">
                        <input type="text" class="form-control pic_content" id="title_${e_index}"
                               value="${(e.content)!''}"
                               placeholder="内容"/>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" class="form-control pic_id" id="qid_${e_index}" value="${(e.id)!''}"
                               placeholder="问题Key" />
                    </div>
                </td>
                <td>
                    <a href="javascript:void(0);" name="moveTop" class="btn btn-primary pull-left" >上移</a>
                    <a href="javascript:void(0);" name="moveBottom" class="btn btn-primary pull-right" >下移</a>
                </td>
            </tr>
    </#list>
</#if>

