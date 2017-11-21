<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/19
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>国漫大作 - 大鱼海棠</title>

	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/plyr/plyr.css">
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">

		<div class="col-sm-8 col-sm-offset-2">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						视频演示
						<small class="m-l-sm">点击进行播放</small>
					</h5>
					<div class="ibox-tools">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
						<a class="dropdown-toggle" data-toggle="dropdown" href="tabs_panels.html#">
							<i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li>
								<a href="tabs_panels.html#">选项1</a>
							</li>
							<li>
								<a href="tabs_panels.html#">选项2</a>
							</li>
						</ul>
						<a class="close-link">
							<i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="player">
						<video poster="${pageContext.request.contextPath}/test.png" controls crossorigin>
							<!-- Video files -->
							<source src="${pageContext.request.contextPath}/test.mp4" type="video/mp4">
							<%--<source src="https://cdn.selz.com/plyr/1.0/movie.webm" type="video/webm">--%>

							<!-- Text track file -->
							<track kind="captions" label="English" srclang="en" src="https://cdn.selz.com/plyr/1.0/example_captions_en.vtt" default>

							<!-- Fallback for browsers that don't support the <video> element -->
							<a href="${pageContext.request.contextPath}/test.mp4">Download</a>
						</video>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>
<script>
	(function (d, u) {
		var a = new XMLHttpRequest(), b = d.body;
		if ("withCredentials" in a) {
			a.open("GET", u, true);
			a.send();
			a.onload = function () {
				var c = d.createElement("div");
				c.setAttribute("hidden", "");
				c.innerHTML = a.responseText;
				b.insertBefore(c, b.childNodes[0])
			}
		}
	})(document, "${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/plyr/sprite.svg");
</script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/plyr/plyr.js"></script>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>


	(function() {

		// This is the bare minimum JavaScript. You can opt to pass no arguments to setup.
		// e.g. just plyr.setup(); and leave it at that if you have no need for events
		var players = plyr.setup('.js-player');


		//================================ 展开/关闭 面板 ===================================//
		//基于 Jquery,Bootstrap
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


	})();
</script>

</body></html>