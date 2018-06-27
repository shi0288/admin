<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">回复问题</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/comment/add" method="post" id="filter">
            <input type="hidden" value="" name="attachs" id="attachs" />

            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">回复内容:</label>
                <div class="col-md-8">
                    <p>
                        【<strong><#if answer.user.type==2 || answer.user.type==3>${(answer.user.expert_name)!''}<#else>${(answer.user.userInfo.data.nick_name)!''}</#if></strong>的回复】${(answer.content)!''}
                        <input type="hidden" name="answer_id" value="${(answer.answerId)}"/>
                    </p>
                </div>
                
            </div>
         <#if (comment) ??>
            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">评论内容:</label>
                <div class="col-md-8">
                    <p>
                        【<strong><#if comment.user.type==2 || comment.user.type==3>${(comment.user.expert_name)!''}<#else>${(comment.user.userInfo.data.nick_name)!''}</#if></strong>的评论】${(comment.content)!''}
                        <input type="hidden" name="reply_uid" value="${(comment.uid)!''}"/>
                        <input type="hidden" name="reply_comment_id" value="${(comment.comment_id)!''}"/>
                    </p>
                </div>
            </div>
        </#if>

            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">发布运营号:</label>
                <div class="col-md-3">
                    <select id="uid" name="uid" class="form-control">
                        <option value="">选择</option>
                    <#if (businessUserList)??>
                        <#list  businessUserList as businessUser>
                            <option value="${(businessUser.user_id)!''}">${(businessUser.nick_name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>
            
            <!--
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">发布省份:</label>
                <div class="col-md-3">
                    <select id="province_select" class="form-control province_select">
                        <option>选择</option>
                    <#if (provinceList)??>
                        <#list  provinceList as province>
                            <option value="${(province.id)!''}">${(province.name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="city_id" class="col-md-2 control-label">城市:</label>
                <div class="col-md-3">
                    <select id="city_id" name="cityId" class="form-control  city_select">
                        <option value="">请先选择省份</option>
                    </select>
                </div>
            </div>
            -->
            
            <div class="form-group">
                <label for="content" class="col-md-2 control-label">回复描述:</label>
                <div class="col-md-8">
                    <textarea class="form-control" id="content" name="content" rows="5"></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <label for="attachImg" class="btn btn-primary">
                        上传图片
                    </label>
                    <input type="file" name="files" id="attachImg" multiple="true" style="display: none"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10 pic_after">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_create_comment">发布</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/answer.js"></script>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/comment.js"></script>

<#include  "/user/autocomplete.ftl" />
</body>
</html>
