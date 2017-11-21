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
			<strong></strong>
			支持拖动排序和触摸屏。
		</p>

		<div class="dd" id="nestable">
			<ol class="dd-list" id="dd-list"></ol>
		</div>


	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/nestable/jquery.nestable.js"></script>

<script>

	var this_data = {
		departSize: 0,
		departThisIndex: 0,
		layer_shade: undefined
	}
	var $dom = {}
	var temp = {
		li: '<li class="dd-item" data-id="{id}"><div class="dd-handle">{text}</div></li>'
	}
	$(document).ready(function() {

		$dom.dd_list = $('#dd-list');

		this_data.pac = window.sessionStorage.getItem( 'pac' );
		this_data.pac_text = window.sessionStorage.getItem( 'pac_text' );

		initPac( function () {

			sorting()

		});

	});
	function sorting() {



		var updateOutput = function (e) {

			this_data.shade = layer.load(2, { shade: 0.6	});

			var $lis = $dom.dd_list.find('li');
			this_data.departSize = $lis.length;
			this_data.departThisIndex = 1;
			$lis.each(function (index) {
				var data = $(this).data('data');
				//console.log( data )

				update_depart({
					id: data.id,
					sorting: Number(index) +1
				})
			})


		};

		$("#nestable").nestable({group: 1}).on("change", updateOutput);

	}

	function update_depart(data) {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 942001,
				id: data.id,
				sorting: data.sorting
			},
			success: function (data) {
				data = JSON.parse(data);

				console.log(this_data.departSize)
				console.log(this_data.departThisIndex)
				console.log('\n\n')

				if (data.status == 10000){
					if (this_data.departSize == this_data.departThisIndex){
						layer.msg('success');
						layer.close(this_data.shade)
					}
					this_data.departThisIndex++;
				}
			}
		})
	}
	
	function initPac(callback) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 230001,
				page: 1,
				rows: 1000,
				pac: this_data.pac,
				fetchType: 0,
			},
			success: function (data) {
				data = JSON.parse(data)
				console.log(data);
				var rows = data.rows;

				var $ol = $('<ol>');
				for (var i in rows){
					if (rows.hasOwnProperty(i)){
						var sub = temp.li.format({
							id: rows[i].id,
							text: rows[i].depart_name,
							sorting: rows[i].sorting
						})

						$ol.append( $(sub).data('data', rows[i] ));
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