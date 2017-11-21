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
	<title>编辑大型警示牌</title>


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


				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="pac">行政代码</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="pac" id="pac" placeholder="行政代码">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="name" id="name" placeholder="名称">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="manager">管理员</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="manager" id="manager" placeholder="age" manager>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="phone">管理员电话</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="phone" id="phone" placeholder="管理员电话">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="address">地址</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="address" id="address" placeholder="地址">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="latitude">经度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="latitude" id="latitude" placeholder="经度">
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">纬度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="longitude" id="longitude" placeholder="longitude">
					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="deviceid">设备编号</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="deviceid" id="deviceid" placeholder="设备编号">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="ip">视频服务器IP地址</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="ip" id="ip" placeholder="视频服务器IP地址">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="area">地域</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="area" id="area" placeholder="地域">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="username">登录用户</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="username" id="username" placeholder="登录用户">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="user_">登录用户</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="user_" id="user_" placeholder="登录用户">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="password">登陆密码</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="password" id="password" placeholder="登陆密码">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="port">登陆端口</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="port" id="port" placeholder="登陆端口">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="height">塔高</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="height" id="height" placeholder="塔高">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="elevation">海拔</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="elevation" id="elevation" placeholder="海拔">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="h_offset">水平角</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="h_offset" id="h_offset" placeholder="水平角">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="note">说明</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="note" id="note" placeholder="说明">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="v_offset">俯仰角</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="v_offset" id="v_offset" placeholder="俯仰角">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="shape">几何</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="shape" id="shape" placeholder="几何">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="channel">通道</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="channel" id="channel" placeholder="通道">

					</div>
				</div>

				<div id="imgs_box"></div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">媒体文件</label>
					<div class="col-xs-8 col-md-4 col-md-4">
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

			initPac( this_data.pac)
		})


	});
	
	function setVlue(callback) {

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);

		console.log(data_tr)

		$('#id').val( data_tr.id );

		this_data.pac = data_tr.pac;
		$('#name').val( data_tr.name );
		$('#manager').val( data_tr.manager );

		$('#phone').val( data_tr.phone );
		$('#address').val( data_tr.address );
		$('#latitude').val( data_tr.latitude );
		$('#longitude').val( data_tr.longitude );
		$('#deviceid').val( data_tr.deviceid );
		$('#ip').val( data_tr.ip );
		$('#user_').val( data_tr.user_ );
		$('#username').val( data_tr.username );
		$('#password').val( data_tr.password );
		$('#channel').val( data_tr.channel );
		$('#port').val( data_tr.port );
		$('#height').val( data_tr.height );
		$('#elevation').val( data_tr.elevation );
		$('#h_offset').val( data_tr.h_offset );
		$('#v_offset').val( data_tr.v_offset );
		$('#note').val( data_tr.note );
		$('#shape').val( data_tr.shape );




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


			},

			messages: {


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



		formData.append("f", 4122001 );

		formData.append("pac", this_data.pac );
		formData.append("name", $form.find('input[name="name"]').val() );
		formData.append("manager", $form.find('input[name="manager"]').val() );
		formData.append("phone", $form.find('input[name="phone"]').val() );
		formData.append("address", $form.find('input[name="address"]').val() );
		formData.append("latitude", $form.find('input[name="latitude"]').val() );
		formData.append("longitude", $form.find('input[name="longitude"]').val() );
		formData.append("deviceid", $form.find('input[name="deviceid"]').val() );
		formData.append("ip", $form.find('input[name="ip"]').val() );
		formData.append("user_", $form.find('input[name="user_"]').val() );
		formData.append("username", $form.find('input[name="username"]').val() );
		formData.append("password", $form.find('input[name="password"]').val() );
		formData.append("channel", $form.find('input[name="channel"]').val() );
		formData.append("port", $form.find('input[name="port"]').val() );
		formData.append("height", $form.find('input[name="height"]').val() );
		formData.append("elevation", $form.find('input[name="elevation"]').val() );
		formData.append("h_offset", $form.find('input[name="h_offset"]').val() );
		formData.append("v_offset", $form.find('input[name="v_offset"]').val() );
		formData.append("note", $form.find('input[name="note"]').val() );
		formData.append("shape", $form.find('input[name="shape"]').val() );




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