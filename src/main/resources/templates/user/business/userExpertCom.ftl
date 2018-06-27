<#if  (userExpertList)??>
    <#list userExpertList as e>
    <div class="col-md-3 pic_body">
        <div class="thumbnail thumb-gag"> <span class="label label-success lobel-tag">序号：<span class="pic_number">${e_index+1}</span></span>
            <img id="pic_${e_index}" src="${(e.picture)!''}"  class="pic_img"  />
            <div class="caption">
                <p>
                <div class="form-group">
                    <input type="file"  name="file"  class="createPic" id="createPic_${e_index}" >
                </div>

                <div class="form-group">
                    <label for="pic_id_${e_index}">用户uid</label>
                    <input type="text" class="form-control pic_id" id="pic_id_${e_index}" value="${e.id}" placeholder="用户uid" />
                </div>

                <div class="form-group">
                    <label for="pic_name_${e_index}">姓名</label>
                    <input type="text" class="form-control pic_name" id="pic_name_${e_index}" value="${e.name}" placeholder="姓名" />
                </div>

                <div class="form-group">
                    <label for="pic_title_${e_index}">标题</label>
                    <input type="text" class="form-control pic_title" id="pic_title_${e_index}" value="${e.title}" placeholder="标题" />
                </div>

                </p>
                <p>
                    <a href="javascript:void(0);" name="moveTop" class="btn btn-primary pull-left" role="button">前移</a>
                    <a href="javascript:void(0);" name="moveBottom" class="btn btn-primary pull-right" role="button">后移</a>
                </p>
                <div style="clear:both;"></div>
            </div>
        </div>
    </div>
    </#list>
</#if>