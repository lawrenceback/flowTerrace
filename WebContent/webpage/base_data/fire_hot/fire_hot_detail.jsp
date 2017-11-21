<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/25
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<script src="${pageContext.request.contextPath}/config/cg.js"></script>
</head>
<body>

	<div class="wrapper wrapper-content  animated fadeInRight">

		<div class="col-sm-12 animated fadeInUp">
			<div class="row">

				<div class="col-sm-6">

					<div class="ibox">
						<div class="ibox-title">
							<h5>基本信息</h5>
						</div>

						<div class="ibox-content">

							<form method="post" class="form-horizontal" name="form1" id="form1">

								<input type="hidden" class="form-control" name="id" id="id" placeholder="id" >

								<div class="form-group">
									<label class="col-xs-4 control-label" for="no">no</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="no" id="no" placeholder="no" disabled>
									</div>
								</div>

								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="satellite">satellite</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="satellite" id="satellite" placeholder="satellite" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="county">county</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="county" id="county" placeholder="county" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="note">note</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="note" id="note" placeholder="note" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="reporter">reporter</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="reporter" id="reporter" placeholder="reporter" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="opinion">opinion</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="opinion" id="opinion" placeholder="opinion" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="duty">duty</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="duty" id="duty" placeholder="duty" disabled>
									</div>
								</div>

								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="longitude">longitude</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="longitude" id="longitude" placeholder="longitude" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="latitude" disabled>latitude</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="latitude" id="latitude" placeholder="latitude" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="type">type</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="type" id="type" placeholder="type" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="pixels">pixels</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="pixels" id="pixels" placeholder="pixels" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="smoke">smoke</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="smoke" id="smoke" placeholder="smoke" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-xs-4 control-label" for="continuous">continuous</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="continuous" id="continuous" placeholder="continuous" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>


								<div class="form-group">
									<label class="col-xs-4 control-label" for="reporttime">reporttime</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="reporttime" id="reporttime" placeholder="reporttime" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-xs-4 control-label" for="receiptt">receiptt</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="receiptt" id="receiptt" placeholder="receiptt" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-xs-4 control-label" for="landtype">landtype</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="landtype" id="landtype" placeholder="landtype" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-xs-4 control-label" for="shape">shape</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="shape" id="shape" placeholder="shape" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>


								<div class="form-group">
									<label class="col-xs-4 control-label" for="continuous">source</label>

									<div class="col-xs-6">
										<input type="text" class="form-control" name="source" id="source" placeholder="source" disabled>
									</div>
								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-xs-2 col-xs-offset-2 control-label" for="status">状态</label>

									<div class="col-xs-8 col-md-4 col-md-4">
										<select name="status" id="status" class="form-control" disabled>
											<option value="0">未反馈</option>
											<option value="1">已反馈</option>
										</select>
									</div>

								</div>
								<div class="hr-line-dashed"></div>

								<div class="form-group">
									<label class="col-xs-4 control-label" for="city">行政区 *</label>

									<div class="col-xs-6" style="display: flex">
										<select class="form-control" id="city" onchange="changeCity(this)" disabled></select>
										<select class="form-control" id="county_" onchange="changeCounty(this)" disabled></select>
									</div>
								</div>

								<div class="hr-line-dashed"></div>

								<div id="imgs_box"></div>


							</form>




						</div>
					</div>


				</div>

				<div class="col-md-6">
					<div class="ibox">
						<div class="ibox-title">
							<h5>反馈信息</h5>
						</div>
						<div class="ibox-content">


							<p>面积: <small id="area"></small></p>
							<p>反馈时间: <small id="createTime"></small></p>
							<p>反馈信息: <small id="description"></small></p>
							<p>fireFighting: <small id="fireFighting"></small></p>
							<p>类型: <small id="fireType"></small></p>
							<p>反馈人: <small id="initiator_name"></small></p>
							<p>state: <small id="state"></small></p>

							<div class="hr-line-dashed"></div>
							<h3>审核反馈</h3>
							<p>审核反馈信息: <small id="examineOption"></small></p>
							<p>审核反馈时间: <small id="examineTime"></small></p>
							<p>审核人: <small id="examineUser_name"></small></p>
							<div class="hr-line-dashed"></div>

							<h3>反馈媒体：</h3>
							<div id="imgs_box_fk">

							</div>

						</div>
					</div>
				</div>



			</div>
		</div>

	</div>



<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js"></script>
<script>

	var $dom = {}
	var temp = {}
	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};

	jQuery(document).ready(function($){

		$dom.media_list = $('#media_list');
		$dom.media_true = $('#media_true');
		$dom.city = $('#city');
		$dom.county = $('#county_');
		$dom.imgs_box = $('#imgs_box');
		$dom.imgs_box_fk = $('#imgs_box_fk');

		initHotDatail()

	});

	function initHotDatail(){

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 4110002,
				id: data_tr.id
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);

				if (data.status == 10000){
					var obj = data.obj;


					$('#continuous').val( obj.continuous );
					$('#county').val( obj.county );
					$('#duty').val( obj.duty );
					$('#landtype').val( obj.landtype );
					$('#latitude').val( obj.latitude );
					$('#longitude').val( obj.longitude );
					$('#no').val( obj.no );
					$('#note').val( obj.note );
					$('#opinion').val( obj.opinion );
					$('#pixels').val( obj.pixels );
					$('#receiptt').val( obj.receiptt );
					$('#reporter').val( obj.reporter );
					$('#reporttime').val( obj.reporttime );
					$('#satellite').val( obj.satellite );
					$('#shape').val( obj.shape );
					$('#smoke').val( obj.smoke );
					$('#source').val( obj.source );

					$('#status').val( obj.status );
					$('#type').val( obj.type );

					var mediaFiles = obj.mediaFiles;
					if (mediaFiles.length > 0){

						var $ul = $('<ul>');
						var $divs = $('<div>');
						for (var i in mediaFiles){
							if(mediaFiles.hasOwnProperty(i)){

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

						$dom.media_list.html($ul)
						$dom.media_true.html($divs)

					}

					var hotFeedback = obj.hotFeedback;
					var this_data = {};
					this_data.isempty = false;
					console.log(hotFeedback)
					for ( var name in hotFeedback ) {
						this_data.isempty = true;
					}

					if (this_data.isempty){

						$('#area').html(hotFeedback.area)
						$('#createTime').html(hotFeedback.createTime)
						$('#description').html(hotFeedback.description)
						$('#examineOption').html(hotFeedback.examineOption)
						$('#examineTime').html(hotFeedback.examineTime)
						$('#fireFighting').html(hotFeedback.fireFighting)
						$('#fireType').html(hotFeedback.fireType)
						$('#initiator_name').html(hotFeedback.initiator_name)
						$('#state').html(hotFeedback.state)

						var mediaFiles_ = hotFeedback.mediaFiles;
						if (mediaFiles_.length > 0){

							var $ul = $('<ul>');
							var $divs = $('<div>');
							for (var i in mediaFiles_){
								if(mediaFiles_.hasOwnProperty(i)){

									switch (mediaFiles_[i].fileType){
										case 1:
											$dom.imgs_box_fk.append('<img src="${pageContext.request.contextPath}/'+mediaFiles_[i].fileUrl+'"><hr>')
											break;
										case 2:
											$dom.imgs_box_fk.append('<video width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles_[i].fileUrl+'" type="video/'+mediaFiles_[i].fileSuffix+'">您的浏览器不支持Video标签。</video><hr>');
											break;
										case 3:
											$dom.imgs_box_fk.append('<audio width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles_[i].fileUrl+'" type="audio/'+mediaFiles_[i].fileSuffix+'">您的浏览器不支持Video标签。</audio><hr>');
											break;
									}


								}
							}

							$dom.media_list.html($ul)
							$dom.media_true.html($divs)

						}

					}


					initPac( obj.pac )
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



</script>
</body>
</html>