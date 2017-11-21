package com.jxgis.ffireserver.util;

public abstract class Constants {
	
	/******************接口**开始****************/
	public static final String LOGIN = "/userService/login";
	public static final String REGIST = "/userService/regist";
	public static final String GETDATETIME = "/timeService/getDateTime";
	/******************接口**结束****************/
	
	/******************错误码**开始****************/
	//缺少鉴权信息或鉴权信息非法
	public static final int NO_OR_INVALID_CREDENTIAL = 401;
	public static final String NO_OR_INVALID_CREDENTIAL_DESC = "缺少鉴权信息或鉴权信息非法";
	public static final int Exist_No_Imei = 60001;//IMEI不存在
	public static final int Imei_Overdue = 60002;//IMEI已经过期
	public static final int Imei_Invalid = 60003;//IMEI已注销
	public static final int Exist_Imei = 60004;//IMEI已经存在
	public static final int Imei_Key_Exist_No = 61001;//IMEI授权码(key)不存在
	public static final int Imei_Key_Occupy = 61002;//IMEI授权码(key)被占用
	public static final int Imei_Pac_Exist_No = 62001;//IMEI授权码(pac)不存在
	public static final int Device_Type_Exist_No = 51001;//设备类型不存在
	/******************错误码**结束****************/

	// 成功
	public static final int SUCCESS = 0;
	// 失败
	public static final int FAILURE = -1;
	
	// 用户在线状态
	public static final int ONLINE = 1;
	public static final int OFFLINE = 0;
	
	// 用户权限
	public static final int COMPETENCE_OTHER = -1;
	public static final int COMPETENCE_PROVINCE = 0;
	public static final int COMPETENCE_CITY = 1;
	public static final int COMPETENCE_COUNTRY = 2;

}
