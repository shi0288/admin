<div class="col-md-10 pic_body">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">新闻早知道内容设置</h3>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label for="pic_title">标题</label>
                <input type="text" class="form-control pic_title" id="pic_title" value="${(topic.title)!''}" placeholder="标题" />
            </div>
            <div class="form-group">
                <label for="pic_content">内容</label>
                <input type="text" class="form-control pic_content" id="pic_content" value="${(topic.content)!''}" placeholder="内容" />
            </div>
            <div class="form-group">
                <label for="pic_url">访问地址</label>
                <input type="text" class="form-control pic_url" id="pic_url" value="${(topic.url)!''}" placeholder="访问地址" />
            </div>
        </div>
    </div>
</div>

