<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String wsPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
</head>
<body>
<textarea id="body" rows="10"></textarea>
<div id="message"></div>
<a onclick="send()">发送</a>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="sockjs.min.js"></script>
<script type="text/javascript">

	//建立socket连接
	var sock = undefined;

	$(function(){

		sock = new WebSocket("<%=wsPath%>websocket.ws/112");
		sock.onopen = function (e) {
			console.log(e);
			sock.send('{"user": 112}')
		};
		sock.onmessage = function (e) {
			console.log(e)
			$("#message").append("<p><font color='red'>"+e.data+"</font>")
		};
		sock.onerror = function (e) {
			console.log(e);
		};
		sock.onclose = function (e) {
			console.log(e);
		}
	});

	function send() {
		if (sock){
			sock.send("34243443")
		}
	}
</script>
</body>
</html>