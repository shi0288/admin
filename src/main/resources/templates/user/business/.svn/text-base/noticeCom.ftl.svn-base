<#if  (pager)??>
    <#list pager as e>
    <div class="col-md-6 pic_body">
        <div class="panel panel-info">
            <div class="panel-heading">
                <span class="label label-success">序号：<span class="pic_number">${e_index+1}</span></span>
            </div>
            <div class="panel-body">
                <p>
                <div class="form-group">
                    <label for="title_${e_index}">标题(仅限PC端展示)</label>
                    <input type="text" class="form-control pic_title" id="title_${e_index}" value="${(e.text)!''}" placeholder="标题" />
                </div>
                <div class="form-group">
                    <label for="url_${e_index}">访问地址</label>
                    <input type="text" class="form-control pic_url" id="url_${e_index}" value="${e.url}" placeholder="访问地址" />
                </div>
                </p>
                <p>
                    <a href="javascript:void(0);" name="moveTop" class="btn btn-primary pull-left" role="button">前移</a>
                    <a href="javascript:void(0);" name="moveBottom" class="btn btn-primary pull-right" role="button">后移</a>
                </p>
            </div>
        </div>
    </div>
    </#list>
</#if>

