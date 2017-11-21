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
	<title>重点防火单位</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
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
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac:  '${LOCAL_CLINET_USER.pac }',
		};
	</script>
</head>

<body>

	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12 animated fadeInUp">
				<form method="post" class="form-horizontal" name="form1" id="form1">



					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称 </label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="name" id="name" placeholder="名称">

						</div>
					</div>
					<div class="hr-line-dashed"></div>


					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="manager">管理员</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="manager" id="manager" placeholder="管理员">

						</div>
					</div>
					<div class="hr-line-dashed"></div>


					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="phone">联系电话</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="phone" id="phone" placeholder="联系电话">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="num_people">人数</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="num_people" id="num_people" placeholder="人数">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="build_area">建筑面积</label>

						<div class="col-xs-8 col-md-6 col-md-6 por">
							<input type="text" class="form-control" name="build_area" id="build_area" placeholder="建筑面积">
							<span class="prafont poa">㎡</span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label"  >类型</label>
						<div class="col-xs-8 col-md-6 col-md-6">
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="typee" id="type1" value="type1" checked>
									县级防火检查站
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="typee" id="type2" value="type2" checked>
									林场防火检查站
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="typee" id="type3" value="type3" checked>
									重点防火企业
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="typee" id="type4" value="type4" checked>
									重点防火场所
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="typee" id="type5" value="type5" checked>
									消防水源
								</label>
							</div>
						</div>

					</div>
					<div class="hr-line-dashed"></div>


					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label"  >状态</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="optionsRadios" id="status1" value="status1" checked>
									优秀
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="optionsRadios" id="status2" value="status2">
									良好
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="optionsRadios" id="status3" value="status3"  >
									一般
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="optionsRadios" id="status4" value="status4"  >
									差
								</label>
							</div>
							<div class="radio fl mr20">
								<label>
									<input type="radio" name="optionsRadios" id="status5" value="status5"  >
									废弃
								</label>
							</div>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">经度</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="longitude" id="longitude" placeholder="经度">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="latitude">纬度</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="latitude" id="latitude" placeholder="纬度">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="management_unit">管理单位</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="management_unit" id="management_unit" placeholder="管理单位">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="note">说明</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<textarea class="form-control" name="note" id="note" placeholder="说明" rows="3"></textarea>
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
						<label for="province" class="col-xs-2 col-xs-offset-2 control-label">行政区</label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<select class="form-control" id="province"></select>
							<select class="form-control" id="city" onchange="changeCity(this)"></select>
							<select class="form-control" id="county" onchange="changeCounty(this)"></select>
						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="address">所在地点 </label>

						<div class="col-xs-8 col-md-6 col-md-6">
							<input type="text" class="form-control" name="address" id="address" placeholder="所在地点">

						</div>
					</div>
					<div class="hr-line-dashed"></div>

					<div class="form-group">
						<label class="col-xs-4 control-label">媒体文件</label>
						<div class="col-xs-8 col-md-6 col-md-6">
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


					<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
						<button class="btn btn-primary" type="submit">提交内容</button>
						<button class="btn btn-warning" onclick="window.parent.layer.closeAll();">取消</button>
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
	$(document).ready(function() {


		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.province = $('#province');

		$dom.bar_progress = $('#bar-progress', window.top.document);

		this_data.pac_level = formatPacLevel(this_data.pac);

		peggingPac(function () {
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
				name: {
					required: true
				},

			},

			messages: {
				pac: {
					required: '行政区号代码不得为空.'
				},
				name: {
					required: '名称不得为空.'
				},

			},

			submitHandler: function(form)
			{
				_submit(form);

			}
		});
	}


	function _submit(form){


		$('#submit').attr('disabled', true).text('提交中...')

		var formData = new FormData(form);

		formData.append("f", 4231001 );
		formData.append("pac", this_data.pac );

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
			processData: false,
			contentType: false,
			xhr: function () {
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
					$('#submit').removeAttr('disabled').text("保存内容")
				}
			}
		})
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
		if (this_data.pac_level > 2) {

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
						if (i < 1) {
							switch (this_data.pac_level) {
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
						switch (this_data.pac_level) {
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

				if (this_data.pac_level == 1) {
					$dom.county.html('<option>全部</option>')
				}

				try {
					callback()
				} catch (e) {

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

		if (this_data.pac_level == 1) {

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
		for (var i in files) {
			if (files.hasOwnProperty(i)) {
				if (files[i].type.indexOf('video') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_video"><video src="' + getObjectURL(files[i]) + '" controls="controls">您的浏览器不支持 video 标签。</video><p>' + files[i].name + '</p></div>')
				} else if (files[i].type.indexOf('audio') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_audio"><audio src="' + getObjectURL(files[i]) + '" controls="controls">您的浏览器不支持 audio 标签。</audio><p>' + files[i].name + '</p></div>')
				} else if (files[i].type.indexOf('image') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_image"><img src="' + getObjectURL(files[i]) + '"><p>' + files[i].name + '</p></div>')
				}

				if (i == 0) {
					fileNames = files[i].name;
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