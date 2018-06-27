<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">高能问答</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/question/hingEnergyList" method="post">
            <div class="row">
                
                <div class="col-md-3">
                    <input type="text" class="form-control" value="${(question.uid)!''}" name="uid" placeholder="用户ID" />
                </div>
                <!--<div class="col-md-3">
                    <input type="text" class="form-control" value="${nickName!''}" name="nickName" placeholder="用户名" />
                </div>-->
            </div>
            <div class="row">
                <!--<div class="col-md-3">
                    <select name="categoryId" class="form-control">
                        <option value="">问题分类</option>
                    <#if categoryList??>
                        <#list categoryList as category>
                            <option value="${category.categoryId}"
                                <#if answeQuestion.categoryId==category.categoryId>
                                    selected = "selected"
                                </#if>
                            >${category.name}</option>
                        </#list>
                    </#if>
                    </select>
                </div>
                -->
                
                <div class="col-md-3">
                    <input value="${startTime!''}" id="startTime" name="startTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd ',realDateFmt:'yyyyMMdd '})"
                           placeholder="开始时间>="/>
                </div>
                <div class="col-md-3">
                    <input value="${endTime!''}" id="endTime" name="endTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd ',realDateFmt:'yyyyMMdd '})"
                           placeholder="结束时间<="/>
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button type="submit" class="btn btn-primary"> 筛选</button>
                </div>
                
            </div>
        </form>
    </div>
<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {
    "uid":(question.uid)!, "startTime":(startTime)!,"endTime":(endTime)!
    }
    />
    <@p.pager pager = pager baseUrl = "${base}/user/question/hingEnergyList" parameterMap = parameterMap/>
</#if>
    <#include  "/user/question/hingEnergyCom.ftl" />

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/hingEnergyList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script src="${jsPath}/date/WdatePicker.js"></script>
</body>
</html>
