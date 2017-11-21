<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>基础数据公共API返回状态代码</title>

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content  animated fadeInUp">
	<div class="row">
		<div class="col-md-12">
			<div class="ibox ">
				<%--<div class="ibox-title">
					<h5>基础数据公共API返回状态代码</h5>
				</div>--%>
				<div class="ibox-content">
					<h5>基础数据公共API返回状态代码	</h5>
					<hr>

					<div class="jqGrid_wrapper">

						<table class="table table-bordered">
							<tr>
								<th>状态码</th>
								<th>解释</th>
							</tr>
							<tr>
								<td>10000</td>
								<td>数据访问/操作成功</td>
							</tr>
							<tr>
								<td>10001</td>
								<td>重复操作</td>
							</tr>
							<tr>
								<td>10002</td>
								<td>密码错误</td>
							</tr>
							<tr>
								<td>10003</td>
								<td>参数错误</td>
							</tr>
							<tr>
								<td>10004</td>
								<td>参数被占用</td>
							</tr>
							<tr>
								<td>10005</td>
								<td>参数是空</td>
							</tr>
							<tr>
								<td>10006</td>
								<td>数据不存在</td>
							</tr>
							<tr>
								<td>10007</td>
								<td>数据已经存在</td>
							</tr>
							<tr>
								<td>70000</td>
								<td>数据访问/操作失败</td>
							</tr>
							<tr>
								<td>77777</td>
								<td>数据操作异常</td>
							</tr>
							<tr>
								<td>20001</td>
								<td>用户不存在</td>
							</tr>
							<tr>
								<td>20000</td>
								<td>用户已存在</td>
							</tr>
							<tr>
								<td>20002</td>
								<td>用户权限不够</td>
							</tr>
							<tr>
								<td>99999</td>
								<td>接口功能不明确</td>
							</tr>
							<tr>
								<td>310003</td>
								<td>用户密码不得为空</td>
							</tr>
							<tr>
								<td>320001</td>
								<td>该编号当天已经存在</td>
							</tr>
							<tr>
								<td>330001</td>
								<td>非正确的几何图形</td>
							</tr>
							<tr>
								<td>60001</td>
								<td>IMEI不存在</td>
							</tr>
							<tr>
								<td>60002</td>
								<td>IMEI已经过期</td>
							</tr>
							<tr>
								<td>60003</td>
								<td>IMEI已注销</td>
							</tr>
							<tr>
								<td>60004</td>
								<td>IMEI已经存在</td>
							</tr>
							<tr>
								<td>61001</td>
								<td>IMEI授权码(key)不存在</td>
							</tr>
							<tr>
								<td>61001</td>
								<td>IMEI授权码(key)被占用</td>
							</tr>
							<tr>
								<td>62001</td>
								<td>IMEI授权码(pac)不存在</td>
							</tr>
							<tr>
								<td>51001</td>
								<td>设备类型不存在</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>




<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>