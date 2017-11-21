<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/29
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>角色排序</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?555">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<style>
		.dd{
			height: calc(100% - 100px);
			overflow: auto;
		}
		hr{
			margin-top: 10px;
			margin-bottom: 10px;
		}
	</style>
</head>
<body class="gray-bg">


	<div class="ibox-content height_per_100">


		<div class="dd" id="nestable">
			<ol class="dd-list" id="dd-list"></ol>
		</div>
		<p class="m-b-lg fr" style="color:red;">
			  这些角色支持拖动排序。
		</p>
		<div class="cl" style="clear: both"></div>
		<div class="text-right" style="overflow-y: hidden">
			<a onclick="editRoles();" class="btn btn-warning">确定</a>

		</div>

	</div>

	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/config/cg.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/nestable/jquery.nestable.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var temp = {
			li: '<li class="dd-item" data-id="{id}"><div class="dd-handle">{roleName}</div></li>'
		};

		var this_data = {};
		var $dom = {};
		$(document).ready(function() {

			$dom.dd_list = $('#dd-list');

			initRole( function () {

				sorting()

			});

		});
		function sorting() {


			var updateOutput = function (e, callback) {

				var $lis = $dom.dd_list.find('li');
				this_data.departSize = $lis.length;
				this_data.departThisIndex = 1;
				this_data.sorting = {};
				$lis.each(function (index) {
					var data = $(this).data('data');
					//console.log( data )

					this_data.sorting['roles["' + Number(index) + '"].id'] = data.id;
					this_data.sorting['roles["' + Number(index) + '"].sorting'] = Number(index) +1;

				});

			};


			$("#nestable").nestable({group: 2}).on("change", updateOutput);

		}

		function editRoles() {

            //遍历角色排序数组
            var j=0;
            for(var i in this_data.sorting){
				if (this_data.sorting.hasOwnProperty(i)){
					j++;
				}
			}


			//是否排序过
			if(j > 0){

                layer.load(2, { shade: 0.6 });

                $.ajax({
                    url: cg_vte.path + "if/roleController/editRoles",
                    type: 'post',
                    dataType: 'text',
                    data: this_data.sorting,
                    success: function (data) {
                        data = JSON.parse(data);

                        if (data.status == 10000){
                            window.parent.initRoles();
                            window.parent.layer.closeAll()
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                })

			}


		}



		function initRole(callback) {

			layer.load(1, {	shade: 0.6});

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 930001,
					rows: 1000,
					sort: 'sorting',
					order: 'desc',
				},
				success: function (data) {
					data = JSON.parse(data);

					var rows = data.rows;

					layer.closeAll();

					var $ol = $('<ol>');
					for (var i in rows) {
						if (rows.hasOwnProperty(i)) {
							var sub = temp.li.format({
								id: rows[i].id,
								roleName: rows[i].roleName,
								roleCode: rows[i].roleCode
							})
							$ol.append( $(sub).data('data', rows[i] ));
						}
					}

					$('#dd-list').html($ol)

					try{
						callback()
					}catch (e){

					}


				}
			})


		}
	</script>
</body>
</html>
