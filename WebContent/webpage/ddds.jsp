<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/22
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Latest content</title>
	<script type="text/javascript" src="jquery-1.9.1.js"></script>
	<script type="text/javascript" src="../plug-in/strophejs-1.2.14/strophe.js"></script>
	<script>
		var BOSH_SERVICE = 'http://127.0.0.1:7070/http-bind/';
		var connection = null;

		function log(msg)
		{
			$('#log').append('<div></div>').append(document.createTextNode(msg));
		}

		/**
		 * 连接绑定方法
		 * @param status
		 */
		function onConnect(status)
		{
			if (status == Strophe.Status.CONNECTING) {
				log('Strophe is connecting.');
			} else if (status == Strophe.Status.CONNFAIL) {
				console.log("连接失败！");
				log('Strophe failed to connect.');
				$('#connect').get(0).value = 'connect';
			} else if (status == Strophe.Status.AUTHFAIL) {
				console.log("登录失败！");
			} else if (status == Strophe.Status.DISCONNECTING) {
				log('Strophe is disconnecting.');
			} else if (status == Strophe.Status.DISCONNECTED) {
				console.log("连接断开！");
				log('Strophe is disconnected.');
				$('#connect').get(0).value = 'connect';
			} else if (status == Strophe.Status.CONNECTED) {
				console.log("连接成功，可以开始聊天了！");
				log('Strophe is connected.');
				log('ECHOBOT: Send a message to ' + connection.jid +
					' to talk to me.');

				connection.addHandler(onMessage, null, 'message', null, null,  null);
				connection.send($pres().tree());
			}
		}

		/**
		 * 获取消息时的方法
		 * @param msg
		 * @returns {Boolean}
		 */
		function onMessage(msg) {

			console.log(msg)
			var to = msg.getAttribute('to');
			var from = msg.getAttribute('from');
			var type = msg.getAttribute('type');
			var elems = msg.getElementsByTagName('body');

			if (type == "chat" && elems.length > 0) {
				var body = elems[0];

				log('ECHOBOT: I got a message from ' + from + ': ' +
					Strophe.getText(body));

				/*  关闭echo机器的自动回复
				 var reply = $msg({to: from, from: to, type: 'chat'})
				 .cnode(Strophe.copyElement(body));
				 connection.send(reply.tree());

				 log('ECHOBOT: I sent ' + from + ': ' + Strophe.getText(body));*/


			}
			return true;
		}

		/**
		 * 发信息
		 * @param toId
		 * @param fromId
		 * @param msg
		 */
		function sendMsg(toId,fromId,msg) {
			var reply = $msg({to: toId, from:fromId , type: 'chat'}).cnode(Strophe.xmlElement('body', '' ,msg));
			connection.send(reply.tree());
			log('ECHOBOT: I sent ' + toId + ': ' + msg);
		}

		/**
		 * 事件监听
		 */
		$(document).ready(function () {
			connection = new Strophe.Connection(BOSH_SERVICE);

			// Uncomment the following lines to spy on the wire traffic.
			connection.rawInput = function (data) { console.log('RECV: ' + data); };
			connection.rawOutput = function (data) { console.log('SEND: ' + data); };

			// Uncomment the following line to see all the debug output.
			//Strophe.log = function (level, msg) { log('LOG: ' + msg); };

			$('#connect').bind('click', function () {
				var button = $('#connect').get(0);
				if (button.value == 'connect') {
					button.value = 'disconnect';
					connection.connect($('#jid').get(0).value,
						$('#pass').get(0).value,
						onConnect);
				} else {
					button.value = 'connect';
					connection.disconnect();
				}
			});

			$('#replay').bind('click', function () {
				toId = $('#tojid').val();
				fromId = $('#jid').val();
				msg=$('#msg').val();
				sendMsg(toId,fromId,msg);
			});
		});
	</script>
</head>
<body>
<div id='login' style='text-align: left'>
	<form name='cred'>
		<label for='jid'>JID:</label>
		<input type='text' id='jid' value="114@127.0.0.1" /><br/>
		<label for='pass'>Password:</label>
		<input type='password' id='pass' value="123456" /><br/>
		<input type='button' id='connect' value='connect' />
	</form>
</div>
<hr />
<div style="text-align: left">
	<label for='tojid'>tojid</label>
	<input type='text' id='tojid' value="admin@127.0.0.1" /><br/>
	<label for='msg'>msg:</label>
	<textarea id="msg">Hello world!!!</textarea>
	<br/>
	<input type='button' id='replay' value='replay' />
</div>
<hr />
<div id='log'></div>
</body>
</html>