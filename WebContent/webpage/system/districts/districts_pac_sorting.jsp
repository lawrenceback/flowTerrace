<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/7/10
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>排序 行政区</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		/******************************************* 上传进度条 Start ********************************************/
		#bar-progress {
			display: block;
			height: 5px;
			width: 0%;
			position: fixed;
			top: 0;
			left: 0;
			background-color: #1AB394;
			z-index: 99999;
		}

		#bar-progress-text {padding-top: 5px;}
		/******************************************* 上传进度条 End  *********************************************/

	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac:  formatPacUnderline(cg_vte.initPac),
		};
	</script>
</head>

<body class="gray-bg">

	<div id="bar-progress"><label id="bar-progress-text"></label></div>

	<div class="ibox-content">
		<p class="m-b-lg">
			<strong>Nestable</strong>
			支持拖动排序和触摸屏。
		</p>

		<div class="dd" id="nestable">
			<ol class="dd-list" id="dd-list"></ol>
		</div>
		<div class="m-t-md">
			<h5>数据：</h5>
		</div>
		<textarea id="nestable-output" class="form-control"></textarea>


	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/nestable/jquery.nestable.js"></script>

<script>

	var temp = {
		li: '<li class="dd-item" data-id="{id}"><div class="dd-handle">{text}</div></li>'
	}
	$(document).ready(function() {


		this_data.pac = window.sessionStorage.getItem( 'pac' );
		this_data.pac_text = window.sessionStorage.getItem( 'pac_text' );



		initPac( function () {

			sorting()

		});

	});
	function sorting() {

		var updateOutput = function (e) {
			var list = e.length ? e : $(e.target), output = list.data("output");
			if (window.JSON) {
				output.val(window.JSON.stringify(list.nestable("serialize")))
			} else {
				output.val("浏览器不支持")
			}
		};
		$("#nestable").nestable({group: 1}).on("change", updateOutput);
		$("#nestable2").nestable({group: 1}).on("change", updateOutput);

		updateOutput($("#nestable").data("output", $("#nestable-output")));
		updateOutput($("#nestable2").data("output", $("#nestable2-output")));

		$("#nestable-menu").on("click", function (e) {
			var target = $(e.target), action = target.data("action");
			if (action === "expand-all") {
				$(".dd").nestable("expandAll")
			}
			if (action === "collapse-all") {
				$(".dd").nestable("collapseAll")
			}
		})

	}
	
	function initPac(callback) {

		var pac = this_data.pac;

		switch (formatPacLevel(pac)){
			case 1:
				//省
				break;
			case 2:
				//市
				pac = pac.substring(0, 2);
				break;
			case 3:
				//区/县
				pac = pac.substring(0, 4);
				break;
			case 4:
				pac = pac.substring(0, 6);
				break;
			case 5:
				pac = pac.substring(0, 9);
				break;
			case 6:
				pac = pac.substring(0, 12);
				break;
			case 7:
				pac = pac.substring(0, 15);
				break;
		}
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210004,
				id: this_data.pac
			},
			success: function (data) {
				data = JSON.parse(data)
				console.log(data);
				var children = data.children;

				var $ol = $('<ol>');
				for (var i in children){
					if (children.hasOwnProperty(i)){
						var sub = temp.li.format({
							id: children[i].id,
							text: children[i].text
						})

						$ol.append( $(sub).data('data', children[i] ));
					}
				}
				$('#dd-list').html($ol)


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