package com.jxgis.ffireserver.util;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Glossary {


	/**
	 * 设备类型
	 */
	public static final int Device_Type_Android = 1;
	public static final int Device_Type_IOS = 2;
	public static final int Device_Type_PC = 3;


	public static final int File_Type_Img = 1;
	public static final int File_Type_Video = 2;
	public static final int File_Type_Audio = 3;

	public static final String File_Ffmpeg_ads = "/plug-in/ffmpeg/ffmpeg.exe";


	/**
	 * 字符号集编码
	 * */
	public static final String Char_UTF_8 = "utf-8";
	public static final String Char_GBK = "gbk";



	/**
	 * 返回状态码
	 * */
	public static final int Return_State_Success= 10000;//数据访问/操作成功
	public static final int Return_State_Repetitive_Operation = 10001;  // 重复操作
	public static final int Return_State_User_Password_Error = 10002;   //密码错误
	public static final int Return_State_Parameter_Error = 10003;  // 参数错误
	public static final int Return_State_Parameter_Occupy = 10004;  // 参数被占用
	public static final int Return_State_Parameter_Null = 10005;  // 参数是空
	public static final int Return_State_Data_Not_Exist = 10006;    //数据不存在
	public static final int Return_State_Data_Exist = 10007;    //数据已经存在
	public static final int Return_State_Data_Linked = 10008;    //数据已被关联
	public static final int Return_State_Error = 70000;      //数据访问/操作失败
	public static final int Return_State_Exception_Operation = 77777;       //数据操作异常
	public static final int Return_State_User_Not_Exist = 20001;            //用户不存在
	public static final int Return_State_User_Exist = 20000;                //用户已存在
	public static final int Return_State_User_Not_Power = 20002;            //用户权限不够
	public static final int Return_State_Not_Function = 99999;              //接口功能不明确

	//TSUser.class[31****]
	public static final int Return_State_User_Account_Not_Null = 310001;//用户帐号不得为空
	public static final int Return_State_User_Name_Not_Null = 310002;//用户名不得为空
	public static final int Return_State_User_Password_Not_Null = 310003;//用户密码不得为空
	public static final int Return_State_User_VoipAccount_Not_Null = 310004;//用户行政区号不得为空

	//FFPFireHot[32****]
	public static final int Return_State_Fire_Hot_NO_Exist_Today = 320001;  //该编号当天已经存在

	public static final int Return_State_Geometry_Format_Error = 330001;    //非正确的几何图形
	/**********************************返回状态码结束*************************************/


	public static final int Filter_Int = -9999;  //筛选时过滤
}
