<#if  (block)??>
<div class="col-md-8 pic_body">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">专家列表中的横图</h3>
        </div>
        <div class="panel-body">
            <div class="thumbnail thumb-gag">
                <img src="${block.img}"  class="pic_expert"  id="pic_expert"  />
                <div class="caption">
                    <p>
                    <div class="form-group">
                        <input type="file"  name="file"  class="createPic" id="createPic_expert" >
                    </div>
                    <div class="form-group">
                        <label for="pic_expert">http地址</label>
                        <input type="text" class="form-control pic_url" id="pic_expert" value="${(block.url)!''}" placeholder="http地址" />
                    </div>
                    </p>
                    <div style="clear:both;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</#if>

