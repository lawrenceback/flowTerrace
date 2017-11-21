<%@ page import="com.jxgis.ffireserver.web.bean.TSRole" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jxgis.ffireserver.web.bean.TSDepart" %>
<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/30
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户 - 个人资料</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/style.min.css?v=4.1.0">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=2273d8796523a6f959aa3e757d372a60"></script>
	<style>
		.ovel{overflow: hidden;}
		.mb20{margin-bottom: 20px;}
		.map{width:700px;height:400px;}
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family: "Microsoft YaHei";}

		.headPortrait {
			width: 300px;
			margin: auto;
		}
	</style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
	<div class="row animated fadeInRight">

		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>个人资料</h5>
					<div class="ibox-tools">
						<a href="${pageContext.request.contextPath}/userController.do?goHref&id=${user.id}&href=system/user/personal/info-edit">
							修改
						</a>
					</div>
				</div>
				<div>
					<div class="ibox-content no-padding border-left-right text-center">
						<img alt="image" class="img-responsive headPortrait" src="${pageContext.request.contextPath}/${user.headPortrait}">
					</div>
					<div class="ibox-content profile-content">
						<p>名称: ${user.name}</p>
						<p>电话: ${user.phone}</p>
						<p>帐号: ${user.account}</p>
						<p>角色: <%
								List<TSRole> roles = (List<TSRole>) request.getSession().getAttribute("roles");
								for (TSRole role: roles){
									out.write(role.getRoleName() + ",");
								}

							%></p>
						<p>部门: <%
							List<TSDepart> departs = (List<TSDepart>) request.getSession().getAttribute("This_Depart_Content");
							for (TSDepart depart: departs){
								out.write(depart.getDepartName() + "/");
							}

						%></p>


					</div>
				</div>
			</div>
		</div>


	</div>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function () {

		$('.dropdown.profile-element', window.parent.document).removeClass('open').find('#dropdown-toggle').attr('aria-expanded', false);

	});

</script>
</body>
</html>
