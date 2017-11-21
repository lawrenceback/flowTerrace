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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.3.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<style>
		#province, #city, #county{
			width: auto;
			display: initial;
		}
		#medit_file_box img{
			width: inherit;
			max-width: 300px;
		}
		#medit_file_box audio{
			width: 100%;
		}

		#medit_file_box .medit_file_value_box{
			padding: 10px;
		}
		#medit_file_box .medit_file_value_box{
			border: 1px solid transparent;
		}
		#medit_file_box .medit_file_value_box:hover{
			border: 1px dashed #ccc;
		}


	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>

	<script>
		var this_data = {
			pac:  '${LOCAL_CLINET_USER.pac }',
		};
	</script>

</head>

<body>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form class="form-horizontal" name="form1" id="form1">


						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="name" id="name" value="" placeholder="名称">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="shape">几何</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<textarea class="form-control" name="shape" id="shape" placeholder="非必填" rows="3"></textarea>
								<span class="help-block m-b-none">示例-点：<code>POINT(15 20)</code></span>
								<span class="help-block m-b-none">示例-线：<code>LINESTRING(0 0, 10 10, 20 25, 50 60)</code></span>
								<span class="help-block m-b-none">示例-面：<code>POLYGON((0 0,10 0,10 10,0 10,0 0),(5 5,7 5,7 7,5 7, 5 5))</code></span>
								<span class="help-block m-b-none">示例-多点：<code>MULTIPOINT(0 0, 20 20, 60 60)</code></span>
								<span class="help-block m-b-none">示例-多线：<code>MULTILINESTRING((10 10, 20 20), (15 15, 30 15))</code></span>
								<span class="help-block m-b-none">示例-多面：<code>MULTIPOLYGON(((0 0,10 0,10 10,0 10,0 0)),((5 5,7 5,7 7,5 7, 5 5)))</code></span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" >状态</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="status1" id="status1" value="option1" checked>
										优秀
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="status1" id="status2" value="option2">
										良好
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="status1" id="status3" value="option3"  >
										一般
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="status1" id="status4" value="option4"  >
										较差
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="optionsRadios" id="status5" value="option4"  >
										报废
									</label>
								</div>
							</div>

						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-xs-2 col-xs-offset-2 control-label"  >类型</div>

							<div class="col-xs-8 col-md-6 col-md-6">
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
										铁质了望台
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
										砖瓦了望台
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3"  >
										木质了望台
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="optionsRadios" id="optionsRadios4" value="option4"  >
										其他了望台
									</label>
								</div>

							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="telescope">望远镜</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="telescope" id="telescope" value="" placeholder="望远镜">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="interphone">对讲机</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="interphone" id="interphone" value="" placeholder="对讲机">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="compass_instrument">罗盘仪</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="compass_instrument" id="compass_instrument" value="" placeholder="罗盘仪">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="telephone">有线电话</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="telephone" id="telephone" value="" placeholder="有线电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="radio">无线电</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="radio" id="radio" value="" placeholder="无线电">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="look_area">	瞭望面积</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="look_area" id="look_area" value="" placeholder="瞭望面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="look_forest_area">瞭望森林面积</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="look_forest_area" id="look_forest_area" value="" placeholder="瞭望森林面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="look_coverage">瞭望覆盖率</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="look_coverage" id="look_coverage" value="" placeholder="瞭望覆盖率">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="c_area">建筑面积</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="c_area" id="c_area" value="" placeholder="建筑面积">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="elevation">海拔</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="elevation" id="elevation" value="" placeholder="海拔">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="build_year">建设年度</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="build_year" id="build_year" value="" placeholder="建设年度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="manager">管理员</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="manager" id="manager" value="" placeholder="管理员">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="phone">管理员电话</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="phone" id="phone" value="" placeholder="管理员电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" >结构</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="structure1" id="structure1" value="structure1" checked>
										一层
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="structure1" id="structure2" value="structure2">
										两层
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="structure1" id="structure3" value="structure3"  >
										三层
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="structure1" id="structure4" value="structure4"  >
										四层
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="structure1" id="structure5" value="structure5"  >
										四层以上
									</label>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">

							<label class="col-xs-2 col-xs-offset-2 control-label" for="video_surveillance">视频监测</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="text" class="form-control" name="video_surveillance" id="video_surveillance" value="" placeholder="视频监测">
								<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 视频监测可填有或无</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label"  >修建单位</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="build_unit1" id="build_unit1" value="build_unit1" checked>
										林业局
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="build_unit1" id="build_unit2" value="build_unit2">
										林场
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="build_unit1" id="build_unit3" value="build_unit3"  >
										其他
									</label>
								</div>

							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">经度</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="number" class="form-control" name="longitude" id="longitude" value="" placeholder="经度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="latitude">纬度</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<input type="number" class="form-control" name="latitude" id="latitude" value="" placeholder="纬度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="note">说明</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<textarea class="form-control" name="note" id="note" rows="4" placeholder="说明"></textarea>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="province" class="col-xs-2 col-xs-offset-2 control-label">行政区</label>

							<div class="col-xs-8 col-md-6 col-md-6">
								<select class="form-control" id="province"></select>
								<select class="form-control" id="city" onchange="changeCity(this)"></select>
								<select class="form-control" id="county" onchange="changeCounty(this)"></select>
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

								<div id="medit_file_box">

								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">

							<div class="col-xs-8 col-md-6 col-md-6 col-xs-offset-4">
								<button class="btn btn-primary" type="submit" id="submit">提交</button>
								<button class="btn btn-warning" onclick="window.parent.layer.closeAll();">取消</button>
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

		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.province = $('#province');

		$dom.bar_progress = $('#bar-progress', window.top.document);

		this_data.pac_level = formatPacLevel(this_data.pac);

		peggingPac(function(){
			initPac(this_data.pac, function () {
				validateForm()
			})
		});


	});


	function validateForm() {

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
				return false;
			}
		});

	}

	function _submit(form){

		$('#submit').attr('disabled', true).text('提交中...')

		var formData = new FormData(form);

		formData.append("f", 4131001);
		formData.append("pac", this_data.pac);

		var files = document.getElementById("mediaFile");
		files = files.files;
		for (var i in files){
			formData.append("file" + i,  files[i]);
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
						window.parent.addOrUpdHotCallback();
					});
				} else {
					layer.msg(data.msg);
					$('#submit').removeAttr('disabled').text('保存内容')
				}
			}
		});

		return false;
	}

	function peggingPac(callback) {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210006,
				code: this_data.pac
			},
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000) {
					var obj = data.obj;
					switch (obj.length) {
						case 1:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled');
							break;
						case 2:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>' + obj[1].name + '</option>').attr('disabled', 'disabled')
							break;
						case 3:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>' + obj[1].name + '</option>').attr('disabled', 'disabled')
							$dom.county.html('<option>' + obj[2].name + '</option>').attr('disabled', 'disabled')
							break;
					}
				}
				try {
					callback()
				} catch (e) {

				}

			}
		})
	}

	//工具-拉取pac数据
	function initPac(id, callback) {
		if (this_data.pac_level > 2){

			this_data.pac = id;

			return;
		}
		$.ajax({
			url: cg_vte.service,
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				var children = data.rows;
				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i < 1){
							switch (this_data.pac_level){
								case 1:
									$dom.city.append('<option value="' + children[i].code + '">全部</option>');
									break;
								case 2:
									$dom.county.append('<option value="' + children[i].code + '">全部</option>');
									break;
							}
							this_data.pac_name = children[i].name;
							continue;
						}
						switch (this_data.pac_level){
							case 1:
								$dom.city.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break;
							case 2:
								$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break
						}

					}
				}

				this_data.pac = id;

				if (this_data.pac_level == 1){
					$dom.county.html('<option>全部</option>')
				}

				try{
					callback()
				} catch (e){

				}

			}
		})
	}

	//加载区县
	function initPacCounty(id, callback) {
		if ($dom.city.val() == 450000) {
			$dom.county.html('<option>全部</option>');
			try {
				callback();
			} catch (e) {

			}
			return;
		}

		$.ajax({
			url: "/if/serviceController/action",
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				$dom.county.empty();

				var children = data.rows;

				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i == 0) {
							this_data.pac_name = children[i].name;
							this_data.pac = children[i].code;
							$dom.county.append('<option value="' + children[i].code + '">全部</option>');
							continue;
						}
						$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')

					}
				}

				try {
					callback();
				} catch (e) {

				}

			}
		})
	}
	//当市高等框发生改变
	function changeCity(dom) {

		this_data.pac = dom.value;

		if (this_data.pac_level == 1){

			$dom.county.unbind("change");
			initPacCounty(dom.value, function () {


				$dom.county.bind("change", function () {

					this_data.pac = $(this).val();

				})

			});

		}

	}
	function changeCounty(item) {
		this_data.pac = item.value;
	}

	function readyFile(files) {

		$('#medit_file_box').empty();
		$('#medit_file_input').val('');
		var fileNames = '';
		files = files.files;
		for (var i in files){
			if (files.hasOwnProperty(i)){
				if (files[i].type.indexOf('video') >= 0){
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_video"><video src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 video 标签。</video><p>'+files[i].name+'</p></div>')
				} else if(files[i].type.indexOf('audio') >= 0){
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_audio"><audio src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 audio 标签。</audio><p>'+files[i].name+'</p></div>')
				} else if(files[i].type.indexOf('image') >= 0){
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_image"><img src="'+getObjectURL(files[i])+'"><p>'+files[i].name+'</p></div>')
				}

				if (i == 0){
					fileNames =  files[i].name;
					continue;
				}
				fileNames += "," + files[i].name;
			}
		}
		$('#medit_file_input').val(fileNames)

	}



	//获取文件地址，显示预览用
	var getObjectURL = function (file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	};

	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt) {
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100 * loaded / tot);     //已经上传的百分比

		$dom.bar_progress.css("width", per + "%");
		$('#bar-progress-text').text(per + "%")
		console.log(per)
		if (per == 100) {

			console.log("上传成功")
			$dom.bar_progress.css("width", '0');
		}

	}

</script>
</body>
</html>