<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>许可管理-设置</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInUp">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<input type="hidden" name="id" id="id">

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-3 control-label">设备类型</label>

							<div class="col-xs-6">
								<select name="deviceType" id="deviceType" class="form-control">
									<option value="1">移动设备</option>
									<option value="2">调度台</option>
								</select>
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="status" class="col-xs-3 control-label">许可类型</label>

							<div class="col-xs-6">
								<select name="status" id="status" class="form-control" onchange="changeStatus(this)">
									<option value="1">永久</option>
									<option value="2">试用</option>
									<option value="3">作废</option>
								</select>
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="imei" class="col-xs-3 control-label">imei</label>

							<div class="col-xs-6">
								<input type="text" class="form-control" name="imei" id="imei" placeholder="imei">
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="key" class="col-xs-3 control-label">key</label>

							<div class="col-xs-6">
								<input type="text" class="form-control" name="key" id="key" placeholder="key" disabled>
								<span class="help-block m-b-none"></span>
							</div>
						</div>


						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="createTime" class="col-xs-3 control-label">创建时间</label>

							<div class="col-xs-6">
								<input type="text" class="form-control" id="createTime" placeholder="创建时间" disabled>
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label for="expTime" class="col-xs-3 control-label">截止时间</label>

							<div class="col-xs-6">
								<input type="text" class="form-control" id="expTime" name="expTime" placeholder="截止时间" data-plugin="datepicker" data-multidate="true">
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-3 control-label" for="city">行政区</label>

							<div class="col-xs-6" style="display: flex">
								<select class="form-control" id="city" onchange="changeCity(this)"></select>
								<select class="form-control" id="county_" onchange="changeCounty(this)"></select>
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="submit" id="submit">保存内容</button>
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
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script>

	var $dom = {};

    var temp = {
        selected_option_count : '<option value="{code}">{text}</option>'
    };
	var this_data = {}

	$(document).ready(function() {

        $dom.city = $('#city');
        $dom.county = $('#county_');

        var data_tr_license = JSON.parse(window.sessionStorage.getItem('data_tr_license'));
        this_data.data = data_tr_license;


		setValue(initPac(this_data.data.pac, form_va()));

	});


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

        try{
            callback()
		} catch (e){

		}

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


	function setValue(callback) {

	    $('#id').val(this_data.data.id)
	    $('#deviceType').val(this_data.data.deviceType)
	    $('#imei').val(this_data.data.imei)
	    $('#key').val(this_data.data.key)
	    $('#pac').val(this_data.data.pac)
	    $('#status').val(this_data.data.status)
	    $('#createTime').val(new Date(this_data.data.createTime).Format('yyyy-MM-dd hh:mm:ss'));
	    if (this_data.data.expTime){
            $('#expTime').val(new Date(this_data.data.expTime).Format('yyyy-MM-dd hh:mm:ss'))
		}

		this_data.pac = this_data.data.pac;

        $('#expTime').datepicker({
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            format: "yyyy-mm-dd"
        }).datepicker( 'setDate' , new Date(this_data.data.expTime).Format('yyyy-MM-dd') );


		try{
		    callback()
		} catch (e){

		}
    }

    function form_va() {
        $("form#form1").validate({
            rules: {},
            messages: {},

            // Form Processing via AJAX
            submitHandler: function(form)
            {
                _submit(form);

            }
        });
    }

	function _submit(form){

		var formData = new FormData(form);

		formData.append("f", 322002)
		formData.append("pac", this_data.pac)

        if ($('status').val() == 1){
            formData.append("expTime", null)
		}

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
	function changeStatus(status) {
		switch (Number(status.value)){
			case 2:
                $('#expTime').parents('.form-group').show()
			    break;
			default:
                $('#expTime').parents('.form-group').hide()
				break;
		}
    }
</script>
</body>
</html>

