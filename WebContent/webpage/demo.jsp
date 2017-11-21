<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/22
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<script type="text/javascript" src="jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/jsxc-master/build/lib/jquery.slimscroll.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/jsxc-master/build/lib/jquery.fullscreen.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/jsxc-master/build/lib/jsxc.dep.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/jsxc-master/build/jsxc.min.js"></script>
</head>
<body>

<input type="text" id="username" />
<input type="password" id="password" />
<button id="submit">Log in</button>

<script>
	jsxc.init({
		xmpp: {
			url: 'http://xmppyw.tunnel.qydev.com/http-bind/'
		},
		root: '/jsxc/'
	});

	$('#submit').click(function(){
		var username = $('#username').val();
		var password = $('#password').val();

		var jid = username + '@xmppyw.tunnel.qydev.com';

		jsxc.start(jid , password);
	});
</script>
</body>
</html>
