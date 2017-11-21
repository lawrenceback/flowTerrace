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
	<title>添加远程监控</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		#city,#county{
			width: 45%;
			margin-right: 5px;
		}

		video{
			width: 400px ! important
		}

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

<body>

<div id="bar-progress"><label id="bar-progress-text"></label></div>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInUp">
			<form method="post" class="form-horizontal" name="form1" id="form1">

				<input type="hidden" name="id" id="id">
				<input type="hidden" name="pac" id="pac" value="450000">

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="shape">形状</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="shape" id="shape" placeholder="形状">
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 该参数必须遵守OGC的OpenGIS Geometry Model</span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="start_addr">起始地点</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="start_addr" id="start_addr" placeholder="起始地点">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="stop_addr">终止地点</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="stop_addr" id="stop_addr" placeholder="终止地点">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称 *</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="name" id="name" placeholder="名称" required>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="build_addr">营造位置</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="build_addr" id="build_addr" placeholder="营造位置">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="build_year">营造年度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="date" class="form-control" name="build_year" id="build_year" placeholder="营造年度">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="build_unit">营造单位</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="build_unit" id="build_unit" placeholder="营造单位">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="tree_type">树种</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="tree_type" id="tree_type" placeholder="树种">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="type">类型</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="type" id="type" placeholder="类型">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="belt_len">长度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="number" class="form-control" name="belt_len" id="belt_len" placeholder="长度">
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 只允许输入数字</span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="belt_width">宽度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="number" class="form-control" name="belt_width" id="belt_width" placeholder="宽度">
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 只允许输入数字</span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="status">状态</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<select name="status" id="status" class="form-control">
							<option value="1">优秀</option>
							<option value="2">良好</option>
							<option value="3" selected>一般</option>
							<option value="4">差</option>
							<option value="5">废弃</option>
						</select>
					</div>

				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="city">行政区 *</label>

					<div class="col-xs-8 col-md-4 col-md-4" style="display: flex">
						<select class="form-control" id="city" onchange="changeCity(this)"></select>
						<select class="form-control" id="county" onchange="changeCounty(this)"></select>
					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div id="imgs_box"></div>

				<div class="hr-line-dashed"></div>


				<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
					<button class="btn btn-primary" type="submit">提交内容</button>
				</div>


			</form>

		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>

<script>

	var $dom = {};

	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};

	$(document).ready(function() {


		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.imgs_box = $('#imgs_box');

		setVlue(function () {
			var pac = $('#pac').val();
			initPac( pac)
		})


	});
	
	function setVlue(callback) {

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);

		console.log(data_tr)

		$('#id').val( data_tr.id );
		$('#belt_len').val( data_tr.belt_len );
		$('#belt_width').val( data_tr.belt_width );
		$('#build_addr').val( data_tr.build_addr );
		$('#build_unit').val( data_tr.build_unit );
		$('#build_year').val( data_tr.build_year.substring(0,10) );
		$('#latitude').val( data_tr.latitude );
		$('#longitude').val( data_tr.longitude );
		$('#name').val( data_tr.name );
		$('#note').val( data_tr.note );
		$('#pac').val( data_tr.pac );
		$('#row_spacing').val( data_tr.row_spacing );
		$('#shape').val( data_tr.shape );
		$('#start_addr').val( data_tr.start_addr );
		$('#status').val( data_tr.status );
		$('#stop_addr').val( data_tr.stop_addr );
		$('#tree_type').val( data_tr.tree_type );
		$('#type').val( data_tr.type );

		var mediaFiles = data_tr.mediaFiles;
		for(var i in mediaFiles){
			if (mediaFiles.hasOwnProperty(i)){
				switch (mediaFiles[i].fileType){
					case 1:
						$dom.imgs_box.append('<img src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'"><hr>')
						break;
					case 2:
						$dom.imgs_box.append('<video width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="video/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</video><hr>');
						break;
					case 3:
						$dom.imgs_box.append('<audio width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="audio/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</audio><hr>');
						break;
				}

			}
		}

		try{
			callback()
		} catch(e){

		}


	}


	function validate_form() {
		$("form#form1").validate({
			rules: {
				pac: {
					required: true
				},

			},

			messages: {
				pac: {
					required: '行政区号代码不得为空.'
				},

			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});
	}


	function _submit(form){

		var $form = $(form);

		var formData = new FormData();

		formData.append("f", 4112001 );
		formData.append("pac", this_data.pac );
		formData.append("id", $form.find('input[name="id"]').val() );
		formData.append("shape", $form.find('input[name="shape"]').val() );
		formData.append("start_addr", $form.find('input[name="start_addr"]').val() );
		formData.append("stop_addr", $form.find('input[name="stop_addr"]').val() );
		formData.append("name", $form.find('input[name="name"]').val() );
		formData.append("build_addr", $form.find('input[name="build_addr"]').val() );
		formData.append("build_year", $form.find('input[name="build_year"]').val() );
		formData.append("build_unit", $form.find('input[name="build_unit"]').val() );
		formData.append("tree_type", $form.find('input[name="tree_type"]').val() );
		formData.append("type", $form.find('input[name="type"]').val() );
		formData.append("belt_len", $form.find('input[name="belt_len"]').val() );
		formData.append("belt_width", $form.find('input[name="belt_width"]').val() );
		formData.append("status", $form.find('input[name="status"]').val() );


		$.ajax({
			url: cg_vte.service,
			type: 'post',
			processData: false,  // 不处理数据
			contentType: false,   // 不设置内容类型
			xhr: function () {
				//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
				var xhr = $.ajaxSettings.xhr();
				if (onprogress && xhr.upload) {
					xhr.upload.addEventListener("progress", onprogress, false);
					return xhr;
				}
			},
			dataType: 'text',
			data: formData,
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.addOrUpdCallback();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}

	//初始化行政区
	function initPac(pac, callback){

		//判断用户是什么级别的用户
		switch (formatPacLevel(pac)){
			case 1:
				//省级用户
				getPac($dom.city,pac,2)
				break;
			case 2:
				//市级用户
				var pac_province = pac.substring(0,2);
				getPac($dom.city, formatPacUnderline(pac_province), 2,function(){

					getPac($dom.county,pac,3,function(){
						$dom.city.val(formatPacUnderline( pac) );
						$dom.county.val(formatPacUnderline(pac) );
					})
				})

				break;
			case 3:
				//县级用户
				var pac_province = pac.substring(0,2);
				getPac($dom.city, formatPacUnderline(pac_province), 2,function(){

					var pac_city = pac.substring(0,4);
					getPac($dom.county, formatPacUnderline(pac_city) ,3,function(){

						var pac_county = pac.substring(0,6);
						getPac($dom.county, formatPacUnderline(pac_city) ,3,function(){
							$dom.city.val(formatPacUnderline(pac_city));
							$dom.county.val(formatPacUnderline(pac_county))
						})

					})
				})
				break;
		}

		validate_form();

	}

	//工具-拉取pac数据
	function getPac(item,id,level,callback) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210002,//接口
				pac: id,
				fetchType: 1
			},
			success: function(data){
				data = JSON.parse(data);

				$dom.county.empty();

				var sub = '';
				var rows = data.rows;
				for (var i in rows){
					if(rows.hasOwnProperty(i)){

						sub += temp.selected_option_count.format({
							code: rows[i].code,
							text: rows[i].name
						})
					}
				}
				if (level < 2 ){
					item.append(sub);
				}
				item.html(sub);

				try {
					callback(rows)
				} catch ( e ){

				}
			}
		})
	}

	//当市高等框发生改变
	function changeCity(item) {
		getPac($dom.county,item.value,3);
		this_data.pac = item.value;
	}
	//当区/县发生改变
	function changeCounty(item) {
		this_data.pac = item.value;
	}

	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt) {
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100 * loaded / tot);     //已经上传的百分比

		$("#bar-progress").css("width", per + "%");
		$('#bar-progress-text').text(per + "%")
		console.log(per)
		if (per == 100) {

			console.log("上传成功")
			$("#bar-progress").css("width", '0');
			$('#bar-progress-text').text('')
		}

	}

</script>
</body>
</html>