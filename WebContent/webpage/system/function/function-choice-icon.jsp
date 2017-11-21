<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  TSUser: YanWei
  Date: 2017/6/28
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Title</title>

	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">

	<style>
		.fontawesome-icon-list .active a {
			background-color: #1d9d74;
			color: #fff;
			text-decoration: none;
		}
		.fontawesome-icon-list .fa-hover a:hover .fa {
			font-size: 14px;
			vertical-align: 0;
		}
		.fontawesome-icon-list .fa-hover a .fa{
			width: inherit;
		}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp">
	<div class="row">
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						图标列表
					</h5>
					<div class="ibox-tools">
						<a onclick="location.reload()">
							<i class="fa fa-refresh"></i>
						</a>
						<a class="collapse-link" onclick="window.parent.choiceIconClose()">
							<i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content icons-box">
					<section id="icons">

						<div class="row fontawesome-icon-list">


							<c:forEach items="${icons}" var="_icon">
								<div class="fa-hover col-xs-4 <c:if test="${icon.id == _icon.id}">active</c:if>">
									<a><i class="${_icon.iconClass}" id="${_icon.id}"></i> ${_icon.iconName}</a>
								</div>
							</c:forEach>



							<%--<div class="fa-hover col-xs-4">
								<a><i class="fa fa-amazon"></i> amazon</a>
							</div>--%>



						</div>

					</section>

				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	var $dom = {}
	$(document).ready(function () {
		$dom.icons = $('#icons .fa-hover');


		choiceIcon($dom.icons);

	});

	function choiceIcon(dom) {

		dom.unbind('click').bind('click',function (event) {

			var $i = $(this).find('i');

			window.parent.choiceIconCallback($i.attr('class'), $i.attr('id'));

		})

	}
</script>
</body>
</html>
