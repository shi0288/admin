<#if  (pager)??>
    <#list pager as e>
    <div class="col-md-4 pic_body">
        <div class="thumbnail thumb-gag"> <span class="label label-success lobel-tag">序号：<span class="form-control pic_number">${e_index+number}</span></span>
            <img id="pic_${e_index+number}" src="${e.img}"  class="pic_img"  />
            <div class="caption">
                <p>
                <div class="form-group">
                    <input type="file"  name="file"  class="createPic" id="createPic_${e_index+number}" >
                </div>
                <div class="form-group">
                    <label for="id_${e_index+number}">id</label>
                    <input type="text" class="form-control pic_id" id="id_${e_index+number}" value="${e.id}" placeholder="id" />
                </div>
                <div class="form-group">
                    <label for="title_${e_index+number}">标题</label>
                    <input type="text" class="form-control pic_title" id="title_${e_index+number}" value="${e.title}" placeholder="标题" />
                </div>
                <div class="form-group">
                    <label for="url_${e_index+number}">APP访问地址</label>
                    <input type="text" class="form-control pic_url" id="url_${e_index+number}" value="${e.url}" placeholder="访问地址" />
                </div>
                <div class="form-group">
                    <label for="H5_url_${e_index+number}">H5访问地址</label>
                    <input type="text" class="form-control pic_h5_url" id="H5_url_${e_index+number}" value="${(e.hurl)!''}" placeholder="H5访问地址" />
                </div>
                
                <div class="form-group">
                    <label for="m_url_${e_index+number}">M站访问地址</label>
                    <input type="text" class="form-control pic_m_url" id="m_url_${e_index+number}" value="${(e.murl)!''}" placeholder="M站访问地址" />
                </div>
                <div class="form-group">
                    <label for="pc_url_${e_index+number}">PC访问地址</label>
                    <input type="text" class="form-control pic_pc_url" id="pc_url_${e_index+number}" value="${(e.purl)!''}" placeholder="PC访问地址" />
                </div>
                <div class="form-group">
                    <label for="wx_url_${e_index+number}">小程序访问地址</label>
                    <input type="text" class="form-control pic_wx_url" id="wx_url_${e_index+number}" value="${(e.wxurl)!''}" placeholder="小程序访问地址" />
                </div>
                </p>
                <p>
                    <a href="javascript:void(0);" name="moveTop" class="btn btn-primary pull-left" role="button">前移</a>
                    <a href="javascript:void(0);" name="delete" class="btn btn-primary pull-left" role="button">删除</a>
                    <a href="javascript:void(0);" name="moveBottom" class="btn btn-primary pull-right" role="button">后移</a>
                </p>
                <div style="clear:both;"></div>
            </div>
        </div>
    </div>
    </#list>
</#if>