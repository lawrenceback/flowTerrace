<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>IMEI 添加</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {
			pac: ''
		};
		var this_data = {
			pac: '',
		};
	</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">添加数据</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="number" id="number" class="form-control">
									<option value="1">1</option>
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="30">30</option>
									<option value="50">50</option>
									<option value="100">100</option>
								</select>
								<span class="help-block m-b-none">您要添加多少条授权码</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">设备类型</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="deviceType" id="deviceType" class="form-control">
									<option value="1">移动设备</option>
									<option value="2">电脑PC</option>
								</select>
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="status" class="col-xs-2 col-xs-offset-2 control-label">许可类型</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="status" id="status" class="form-control">
									<option value="1">永久</option>
									<option value="2">试许可</option>
								</select>
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div id="expTime_box" style="display: none">
							<div class="hr-line-dashed"></div>

							<div class="form-group" >
								<label class="col-xs-2 col-xs-offset-2 control-label">截止时间</label>

								<div class="col-xs-8 col-md-4 col-md-4">
									<input type="text" class="form-control" placeholder="截止时间" name="expTime" id="areaSelect" disabled>
									<span class="help-block m-b-none"></span>
								</div>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="city">行政区 *</label>

							<div class="col-xs-4" style="display: flex">
								<select class="form-control" id="province" style="width: 165px;margin-right: 30px;"></select>
								<select class="form-control" id="city" onchange="changeCity(this)" style="width: 165px;margin-right: 30px;"></select>
								<select class="form-control" id="county" onchange="changeCounty(this)" style="width: 165px;" ></select>


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
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in/tools/getUrlParam.js"></script>
<script>

	var $dom = {};
	$(document).ready(function() {


		this_data.pac = $.getUrlParam("pac");


		this_data.pac_level = formatPacLevel(this_data.pac);

		$dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');


		$('#areaSelect').datepicker({
			keyboardNavigation: true,
			forceParse: true,
			autoclose: true,
            startDate : new Date(new Date().getTime() + 1000*60*60*24),
            setStartDate: new Date(new Date().getTime() + 1000*60*60*24),
            todayBtn : "linked",
		}).on('changeDate',function(e){
            var endTime = e.date;
            $('#areaSelect').datepicker('setStartDate', new Date(new Date().getTime() + 1000*60*60*24));
        });

		peggingPac(function(){
			initPac(this_data.pac, function(){
				validate_form();
			})
		})



	});


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
				if (data.status == 10000){
					var obj = data.obj;
					switch (obj.length){
						case 1:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled');
							break;
						case 2:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							break;
						case 3:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							$dom.county.html('<option>'+obj[2].name+'</option>').attr('disabled', 'disabled')
							break;
					}
				}
				try{
					callback()
				} catch (e){

				}

			}
		})

	}

	function changeCounty(dom){
		this_data.pac = dom.value;
	}

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

	//初始化行政区
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

		var formData = new FormData(form);

		formData.append("f", 321001);
		formData.append("pac", this_data.pac);
		formData.append("account", '${user.account}');

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: formData,
			processData: false,  // 不处理数据
			contentType: false,   // 不设置内容类型
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);

				if (data.status == 10000){
					//layer.msg('添加成功');

					swal({
						title: "提示",
						text: "成功",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.initImeiList();
						window.parent.layer.closeAll();
					});

				} else {

					layer.msg(data.msg);
				}
			}
		})
	}

    $('#status').on("change", function(){

        if($(this).val() == 1){
            $('#areaSelect').attr("disabled",true);
	        $('#expTime_box').css({display: 'none'});
        }else{
            $('#areaSelect').attr("disabled",false);
	        $('#expTime_box').css({display: 'block'});
        }
    })
</script>
</body>
</html>

