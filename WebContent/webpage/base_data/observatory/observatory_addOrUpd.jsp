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
	<title>添加瞭望台</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<input type="hidden" class="form-control" name="id" id="id" value="" placeholder="id">

						<div class="form-group">
							<label class="col-sm-2 control-label" for="pac">行政代码</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="pac" id="pac" value="" placeholder="pac">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="name" value="" placeholder="名称">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="shape">形状</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="shape" id="shape" value="" placeholder="形状">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="status" id="status" value="" placeholder="状态">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="type">类型</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="type" id="type" value="" placeholder="类型">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="telescope">望远镜</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="telescope" id="telescope" value="" placeholder="望远镜">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="interphone">对讲机</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="interphone" id="interphone" value="" placeholder="对讲机">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="compass_instrument">罗盘仪</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="compass_instrument" id="compass_instrument" value="" placeholder="罗盘仪">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="telephone">有线电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="telephone" id="telephone" value="" placeholder="有线电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="radio">无线电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="radio" id="radio" value="" placeholder="无线电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="look_area">瞭望面积</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="look_area" id="look_area" value="" placeholder="瞭望面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="look_forest_area">瞭望森林面积</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="look_forest_area" id="look_forest_area" value="" placeholder="瞭望森林面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="look_coverage">瞭望覆盖率</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="look_coverage" id="look_coverage" value="" placeholder="瞭望覆盖率">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="c_area">建筑面积</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="c_area" id="c_area" value="" placeholder="建筑面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="elevation">海拔</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="elevation" id="elevation" value="" placeholder="海拔">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="build_year">建设时间</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="build_year" id="build_year" value="" placeholder="建设时间">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="manager">管理员</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="manager" id="manager" value="" placeholder="管理员">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="phone">电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="phone" id="phone" value="" placeholder="电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="structure">结构</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="structure" id="structure" value="" placeholder="结构">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="video_surveillance">视频监测</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="video_surveillance" id="video_surveillance" value="" placeholder="视频监测">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="build_unit">修建单位</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="build_unit" id="build_unit" value="" placeholder="修建单位">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="longitude">径度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="longitude" id="longitude" value="" placeholder="径度">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="latitude">纬度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="latitude" id="latitude" value="" placeholder="纬度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="note">说明</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="note" id="note" value="" placeholder="说明">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div id="imgs_box">

						</div>


						<div class="form-group">

							<div class="col-sm-10 col-xs-offset-2">
								<button class="btn btn-primary" type="submit" id="submit">提交</button>
							</div>
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
	$(document).ready(function() {

		$dom.imgs_box = $('#imgs_box');

		setFormValue(validation_());

	});

 function validation_(){

	 $("form#form1").validate({
		 rules: {
			 pac: {
				 required: true
			 },
			 name: {
				 required: true
			 },

		 },

		 messages: {
			 pac: {
				 required: '行政区号代码不得为空.'
			 },
			 name: {
				 required: 'name不得为空.'
			 },

		 },

		 // Form Processing via AJAX
		 submitHandler: function(form)
		 {
			 _submit(form);

		 }
	 });


 }
	function setFormValue(callback) {

		var remotemonitoring = window.sessionStorage.getItem('data_tr');
		remotemonitoring = JSON.parse(remotemonitoring);

		console.log(remotemonitoring)


		$('#id').val(remotemonitoring.id);
		$('#name').val(remotemonitoring.name);
		$('#pac').val(remotemonitoring.pac);
		$('#shape').val(remotemonitoring.shape);
		$('#status').val(remotemonitoring.status);
		$('#type').val(remotemonitoring.type);
		$('#telescope').val(remotemonitoring.telescope);
		$('#interphone').val(remotemonitoring.interphone);
		$('#compass_instrument').val(remotemonitoring.compass_instrument);
		$('#telephone').val(remotemonitoring.telephone);
		$('#radio').val(remotemonitoring.radio);
		$('#look_area').val(remotemonitoring.look_area);
		$('#look_forest_area').val(remotemonitoring.look_forest_area);
		$('#look_coverage').val(remotemonitoring.look_coverage);
		$('#c_area').val(remotemonitoring.c_area);
		$('#elevation').val(remotemonitoring.elevation);
		$('#build_year').val(remotemonitoring.build_year);
		$('#manager').val(remotemonitoring.manager);
		$('#phone').val(remotemonitoring.phone);
		$('#structure').val(remotemonitoring.structure);
		$('#video_surveillance').val(remotemonitoring.video_surveillance);
		$('#build_unit').val(remotemonitoring.build_unit);
		$('#longitude').val(remotemonitoring.longitude);
		$('#latitude').val(remotemonitoring.latitude);
		$('#note').val(remotemonitoring.note);

		var mediaFiles = remotemonitoring.mediaFiles;
		for(var i in mediaFiles){
			if (mediaFiles.hasOwnProperty(i)){
				$dom.imgs_box.append('<img src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'">')
			}
		}

		try{
			callback()
		} catch (e){

		}
	}
	function _submit(form){

		var formData = new FormData(form);

		formData.append("f", 4131001);

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			processData: false,  // 不处理数据
			contentType: false,   // 不设置内容类型
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
						window.parent.editHotCallback();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
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