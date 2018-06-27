<!doctype html>
<html>
<head>
    <title>问答管理后台</title>     
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
    <META HTTP-EQUIV="expires" CONTENT="0">
<#include "user/boot_css.ftl"/>
</head>
<body class="main_body">
<#include "user/boot_sub.ftl"/>
<div class="container-fluid main">
    <div class="row row-offcanvas row-offcanvas-left">
    <#include "/user/boot_left.ftl"/>
        <div class="col-xs-12 col-sm-10">
            <div class="panel panel-primary">
                <div class="panel-heading  visible-xs">
                    <button type="button" class="btn btn-default" data-toggle="offcanvas"> 选择菜单
                        <span class="glyphicon glyphicon-menu-right"></span>
                    </button>
                </div>