<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%--

		在JSP中，请尽量使用绝对路径，以保证路径的完整性。

	--%>

	<title>广西森林防火系统</title>

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=5.1.0&312">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dddsdss0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/hplus/smartMenu.css">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo_ico.ico" >

	<style>
		.roll-right.J_tabRight{
			right: 80px;
		}
		.roll-right.btn-group{
			right: 0;
		}

		/******************************************* 请求进度条 Start ********************************************/
		#bar-progress {
			display: block;
			height: 2px;
			background-color: #1ab394;
			width: 0%;
		}
		/******************************************* 请求进度条 End  *********************************************/


	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>')</script>
	<script src="${pageContext.request.contextPath}/config/cg.js?"></script>

	<style>
		#content-main {
			height: calc(100% - 100px);
			overflow: hidden;
		}
	</style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg  pace-done">


<div id="wrapper">

	<!--左侧导航开始-->
	<jsp:include page="main/left.jsp"></jsp:include>
	<!--左侧导航结束-->
	<!--右侧部分开始-->
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="navbar-header">

					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i></a>

				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown">
						<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
							<i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
						</a>
						<ul class="dropdown-menu dropdown-messages">
							<li class="m-t-xs">
								<div class="dropdown-messages-box">
									<a class="pull-left">
										<%--<img alt="image" class="img-circle" src="http://orheaidcn.bkt.clouddn.com/a7.jpg">--%>
									</a>
									<div class="media-body">
										<small class="pull-right">46小时前</small>
										<strong>小四</strong> 这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？ <br>
										<small class="text-muted">3天前 2014.11.8</small>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="dropdown-messages-box">
									<a class="pull-left">
										<%--<img alt="image" class="img-circle" src="http://orheaidcn.bkt.clouddn.com/a4.jpg">--%>
									</a>
									<div class="media-body ">
										<small class="pull-right text-navy">25小时前</small>
										<strong>国民岳父</strong> 如何看待“男子不满自己爱犬被称为狗，刺伤路人”？——这人比犬还凶 <br>
										<small class="text-muted">昨天</small>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="text-center link-block">
									<a class="J_menuItem" href="mailbox.html" data-index="88">
										<i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
									</a>
								</div>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
							<i class="fa fa-bell"></i> <span class="label label-primary">8</span>
						</a>
						<ul class="dropdown-menu dropdown-alerts">
							<li>
								<a>
									<div>
										<i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
										<span class="pull-right text-muted small">4分钟前</span>
									</div>
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<a>
									<div>
										<i class="fa fa-qq fa-fw"></i> 3条新回复 <span class="pull-right text-muted small">12分钟钱</span>
									</div>
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<div class="text-center link-block">
									<a class="J_menuItem" href="notifications.html" data-index="89">
										<strong>查看所有 </strong> <i class="fa fa-angle-right"></i>
									</a>
								</div>
							</li>
						</ul>
					</li>

				</ul>
			</nav>
		</div>
		<div class="row content-tabs">
			<button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
			</button>
			<nav class="page-tabs J_menuTabs">
				<div class="page-tabs-content">
					<a class="active J_menuTab" data-id="index_v1.html">首页</a>
				</div>
			</nav>
			<button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
			</button>
			<div class="btn-group roll-nav roll-right">
				<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

				</button>
				<ul role="menu" class="dropdown-menu dropdown-menu-right">
					<li class="J_tabShowActive">
						<a>定位当前选项卡</a>
					</li>
					<li class="divider"></li>
					<li class="J_tabCloseAll">
						<a>关闭全部选项卡</a>
					</li>
					<li class="J_tabCloseOther">
						<a>关闭其他选项卡</a>
					</li>
				</ul>
			</div>
			<div id="bar-progress"></div>
		</div>
		<div class="row J_mainContent" id="content-main">

			<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${pageContext.request.contextPath}/if/serviceHrefController/action?f=1&href=system/main/main" frameborder="0" data-id="index_v1.html" seamless=""></iframe>

		</div>
		<%--<div class="footer">
			<div class="pull-right">© 2017-2022
				<a>java4u</a>
			</div>
		</div>--%>
	</div>
	<!--右侧部分结束-->
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/hplus.js?v=4.1.0"></script>

<!--右键菜单-->
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/hplus/jquery-smartMenu.js"></script>

<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js?' + new Date().getTime() + '"><\/script>')</script>
<!-- 弹出TAB -->
<script src="${pageContext.request.contextPath}/plug-in/hplus/hplus-tab.js"></script>

<script>
	$(document).ready( function(){

		//================================ 展开/关闭 面板 ===================================//
		//基于 Jquery,Bootstrap
		//这是模板代码，放于此处，在需要的时候可拷贝使用
		if ($(".modal").appendTo("body"), $("[data-toggle=popover]").popover(), $(".collapse-link").click(function () {
				var o = $(this).closest("div.ibox"), e = $(this).find("i"), i = o.find("div.ibox-content");
				i.slideToggle(200), e.toggleClass("fa-chevron-up").toggleClass("fa-chevron-down"), o.toggleClass("").toggleClass("border-bottom"), setTimeout(function () {
					o.resize(), o.find("[id^=map-]").resize()
				}, 50)
			}), $(".close-link").click(function () {
				var o = $(this).closest("div.ibox");
				o.remove()
			}), top == this) {
		}
		//================================= 展开/关闭 面板 ==================================//

	})

	function callbackAddTab() {
		$('#dropdown-toggle').click();
	}

//	console.log("一张网页，要经历怎样的过程，才能抵达用户面前？\n一位新人，要经历怎样的成长，才能站在技术之巅？\n探寻这里的秘密；\n体验这里的挑战；\n成为这里的主人；\n加入华宇，你，可以影响世界。");


	peggingPac('${LOCAL_CLINET_USER.pac}');
	function peggingPac(pac,callback) {

		$.ajax({
			url: '/if/serviceController/action',
			type: 'post',
			dataType: 'text',
			data: {
				f: 210006,
				code: pac
			},
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					var obj = data.obj;

					var sub = "";
					for(var i in obj){
						if (obj.hasOwnProperty(i)){
							if (i < 1){
								sub = obj[i].name;
								continue;
							}
							sub += "-" + obj[i].name;
						}
					}

					$('#pac').html(sub)
				}
				try{
					callback()
				} catch (e){

				}

			}
		})

	}
</script>
</body>
</html>

<%--   媒体预览；匆删 Start   --%>
<style>
	.none{
		display: none;
	}
	.bj_media{
		position: fixed;
		z-index: 9999;
		background-color: rgba(0, 0, 0, 0.70);
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		vertical-align:middle;
		text-align:center;
	}
	.bj_media.bj_media_img img{
		vertical-align: middle;
		max-width: 100%;
		max-height: 100%;
		width: auto;
		height: auto;
		/*top: 50%;*/
		/*left: 50%;*/
		/*-webkit-transform: translateX(-50%) translateY(-50%);*/
		/*transform: translateX(-50%) translateY(-50%);*/
		/*position: relative;*/
	}
	.bj_media.bj_media_video video{
		width: 100%!important;
		height: 100%!important;
	}
	.bj_media.bj_media_video.none, .bj_media.bj_media_img.none, .bj_media.bj_media_audio.none{
		display: none;
	}
	.bj_media.bj_media_audio audio{
		vertical-align: middle;
		max-width: 100%;
		width: 98%;
		padding-left: 1%;
		padding-right: 1%;
		top: 50%;
		transform: translateX(0%) translateY(-50%);
		position: relative;
	}
	.bj_media .bj_media_close{
		position: fixed;
		z-index: 10999;
		top: 10px;
		right: 10px;
		display: block;
		background-color: #FF9233;
		height: 40px;
		width: 40px;
		line-height: 40px;
		border-radius: 50%;
		color: #fff;
		text-align: center;
	}
</style>
<script>
	function bj_media_close(dom) {
		$(dom).parents(".bj_media").remove()
	}
</script>
<%--   媒体预览；匆删 End   --%>