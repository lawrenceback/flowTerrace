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
	<title>添加热点</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		#city,#county_{
			width: 45%;
			margin-right: 5px;
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
				<div class="row">
					<div class="col-sm-12">


						<form method="post" class="form-horizontal" name="form1" id="form1">

							<div class="form-group">
								<label class="col-xs-4 control-label" for="no">no</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="no" id="no" placeholder="no" >
								</div>
							</div>

							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="satellite">satellite</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="satellite" id="satellite" placeholder="satellite">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="county">county</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="county" id="county" placeholder="county">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="note">note</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="note" id="note" placeholder="note">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="reporter">reporter</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="reporter" id="reporter" placeholder="reporter">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="opinion">opinion</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="opinion" id="opinion" placeholder="opinion">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="duty">duty</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="duty" id="duty" placeholder="duty">
								</div>
							</div>

							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="longitude">longitude</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="longitude" id="longitude" placeholder="longitude">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="latitude">latitude</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="latitude" id="latitude" placeholder="latitude">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="type">type</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="type" id="type" placeholder="type">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="pixels">pixels</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="pixels" id="pixels" placeholder="pixels">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="smoke">smoke</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="smoke" id="smoke" placeholder="smoke">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-4 control-label" for="continuous">continuous</label>

								<div class="col-xs-6">
									<input type="text" class="form-control" name="continuous" id="continuous" placeholder="continuous">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label" for="city">行政区 *</label>

								<div class="col-xs-6" style="display: flex">
									<select class="form-control" id="city" onchange="changeCity(this)"></select>
									<select class="form-control" id="county_" onchange="changeCounty(this)"></select>
								</div>
							</div>

							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label">媒体文件</label>
								<div class="col-xs-6">
									<style>	#mediaFile{opacity: 0;position: absolute;cursor: pointer}#medit_file_input{overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}</style>
									<input type="file" class="form-control" id="mediaFile" accept="audio/*,video/*,image/*,*.mkv" onchange="readyFile(this)" multiple>
									<div class="input-append input-group" style="z-index: -1;">
								<span class="input-group-btn">
									<button class="btn btn-white" type="button">选择文件</button>
								</span>
										<input class="input-large form-control" type="text" id="medit_file_input">
									</div>
									<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 支持的类型有: <code>image/*,video/*,audio/*</code></span>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
								<button class="btn btn-primary" type="submit">提交内容</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>


	var $dom = {};

	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};

	$(document).ready(function() {


		$dom.city = $('#city');
		$dom.county = $('#county_');

		getPac($dom.city, this_data.pac ,2,function () {
			$('#pac').val(this_data.pac);

			//表单校验
			validate_form();



		});




	});

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

	function readyFile(files) {
		files = files.files;
		$('#medit_file_input').val('');
		var fileNames = '';
		for (var i in files){
			if (files.hasOwnProperty(i)){
				if (i == 0){
					fileNames =  files[i].name;
					continue;
				}
				fileNames += "," + files[i].name;
			}
		}
		$('#medit_file_input').val(fileNames)
	}


	function _submit(form){

		var $form = $(form);

		var formData = new FormData();

		formData.append("f", 4111001 );
		formData.append("pac", this_data.pac );
		formData.append("no", $form.find('input[name="no"]').val() );
		formData.append("satellite", $form.find('input[name="satellite"]').val() );
		formData.append("longitude", $form.find('input[name="longitude"]').val() );
		formData.append("latitude", $form.find('input[name="latitude"]').val() );
		formData.append("pixels", $form.find('input[name="pixels"]').val() );
		formData.append("smoke", $form.find('input[name="smoke"]').val() );
		formData.append("continuous", $form.find('input[name="continuous"]').val() );
		formData.append("landtype", $form.find('input[name="landtype"]').val() );
		formData.append("receiptt", $form.find('input[name="receiptt"]').val() );
		formData.append("type", $form.find('input[name="type"]').val() );
		formData.append("reporter", $form.find('input[name="reporter"]').val() );
		formData.append("reporttime", $form.find('input[name="reporttime"]').val() );
		formData.append("opinion", $form.find('input[name="opinion"]').val() );
		formData.append("duty", $form.find('input[name="duty"]').val() );
		formData.append("note", $form.find('input[name="note"]').val() );
		formData.append("source", $form.find('input[name="source"]').val() );
		formData.append("shape", $form.find('input[name="shape"]').val() );
		formData.append("county", $form.find('input[name="county"]').val() );
		formData.append("status", $form.find('input[name="status"]').val() );

		//媒体文件
		var files = document.getElementById('mediaFile');
		files = files.files;
		for(var i in files){
			if (files.hasOwnProperty(i)){
				formData.append("file" + i, files[i] );
			}
		}

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