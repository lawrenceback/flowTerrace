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

	<title>首页</title>

	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/plug-in/demo.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/plug-in/iconfont.css?35553" rel="stylesheet">



</head>

<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">

				<div class="middle-box text-center animated fadeInRightBig">
					<h3 class="font-bold">这里是页面内容</h3>

					<div class="error-desc">
						您可以在这里添加栅格，参考首页及其他页面完成不同的布局 <br>
					</div>
				</div>

			</div>

		</div>



		<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>


		<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
		<script>document.write('<script src="'+cg_vte.iconfont_js+'?' + new Date().getTime() + '"><\/script>')</script>


		<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	</div>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>





	<script>
   $(function(){



   })
 function togglebox(item){

   	$(item).siblings(".ibox-bd").slideToggle();


 }
   function amplification(item) {

	   $(item).parents('.content-box').find('.down').hide();

   	    var content = $(item).parents('.content-box').html();

	    layer.open({
		   type: 1,
		   title: '',
		   closeBtn: 0,
		   maxmin: false, //开启最大化最小化按钮
		   area: ['100%', '100%'],
		   content: content,
		   btn: ['关闭'],
		   fix: false,
		   scrollbar: false,
		   yes: function (index, layero) {

			   $(item).parents('.content-box').find('.down').show();

		   	layer.close( index );

		   }
	   });


   }

	</script>
</body>
</html>