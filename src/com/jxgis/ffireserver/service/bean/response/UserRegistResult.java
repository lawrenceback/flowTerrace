package com.jxgis.ffireserver.service.bean.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.bean.Result;

@XmlRootElement(name = "registResult")
public class UserRegistResult {

	// 统一结果
	private Result result;
	// 登录成功的Token值
	private String token;
	private String voipAccount;

	public UserRegistResult() {

	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public UserRegistResult(int resCode) {
		result = new Result(resCode);
		if (resCode == 0L) {
			result.setResDesc("用户注册成功");
		} else {
			result.setResDesc("用户注册失败");
		}
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

}
