package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;
import com.jxgis.ffireserver.util.Glossary;

@XmlRootElement(name = "loginResult")
public class UserLoginResult {

	// 统一结果
	private Result result;
	// 登录成功的Token值
	private String token;

	private int id;
	private String name;
	private String account;
	private String voipAccount;
	private String voipPassword;
	private String headUrl;
	private String subscriber;

	private String key;//设备授权码
	private String pac;



	public UserLoginResult() {

	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public UserLoginResult(int resCode) {
		result = new Result(resCode);
		result.setResCode(resCode);
		if (resCode == 0L) {
			result.setResDesc("用户登录成功");
		} else {
			result.setResDesc("用户名或密码错误");
		}
	}
	public UserLoginResult(int resCode, String resDesc) {
		result = new Result(resCode);
		result.setResCode(resCode);
		result.setResDesc(resDesc);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getVoipPassword() {
		return voipPassword;
	}

	public void setVoipPassword(String voipPassword) {
		this.voipPassword = voipPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}
}
