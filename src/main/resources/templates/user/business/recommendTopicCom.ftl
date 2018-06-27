<#if  (pager)??>
    <#list pager as e>
    <div class="col-md-4 pic_body">
        <div class="thumbnail thumb-gag"> <span class="label label-success lobel-tag">序号：<span class="pic_number">${e_index+1}</span></span>
            <img id="pic_${e_index}" src="${e.img}" text="123"  class="pic_img"  />
            <img id="picNew_${e_index}" src="${e.newimg}" name="新图" class="picNew_img"  />
            <img id="background_${e_index}" src="${e.background}"  class="background_img"  />
            <div class="caption">
                <p>
                <div class="form-group">
                    <input type="file"  name="file"  class="createPic" id="createPic_${e_index}" >
                </div>
                <div class="form-group">
                    <input type="file"  name="file"   class="createNewPic" id="createNewPic_${e_index}" >
                </div>
                <div class="form-group">
                    <input type="file"  name="file" class="createbackground" id="createbackground_${e_index}" >
                </div>
                <div class="form-group">
                    <label for="title_${e_index}">标题</label>
                    <input type="text" class="form-control pic_title" id="title_${e_index}" value="${e.title}" placeholder="标题" />
                </div>
                <div class="form-group">
                    <label for="subject_${e_index}">话题</label>
                    <input type="text" class="form-control pic_subject" id="subject_${e_index}" value="${e.subject}" placeholder="内容" />
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
                <div style="clear:both;"></div>
            </div>
        </div>
    </div>
    </#list>
</#if>