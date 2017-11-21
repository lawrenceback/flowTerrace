var cg_vte = {
	www:'http://www.wybj256.com',
	path: '/',
	url: "/userController/action",
	login: "/loginController.do?login",
	imei: "/imeiController/action",
	imei_service: "/imeiController.do?service",
	service: "/if/serviceController/action",

	service_href: '/if/serviceHrefController/action?f=1&href=',//系统后台页面内部跳转【带用户信息】
	service_href_no_user: '/if/serviceHrefController/action?f=2&href=',//系统后台页面内部跳转【不带用户信息】
	pagePath: {
		index: "system/index",//首页面
		userList: "user/list",//用户列表
		userManage: "user/userManage",//用户管理
		imeiList: "user/imeiList",//用户管理
	},
	initPac: '45',//初始起动【广西_45,江西_36】
	iconfont_js: '//at.alicdn.com/t/font_330309_9z45hpmfbxp6i529.js',
};