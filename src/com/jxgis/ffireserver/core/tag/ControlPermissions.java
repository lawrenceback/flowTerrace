package com.jxgis.ffireserver.core.tag;


import com.jxgis.ffireserver.core.constant.DataBaseConstant;
import com.jxgis.ffireserver.web.bean.TSControl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by YanWei on 2017/7/18.
 *
 */
public class ControlPermissions extends BodyTagSupport {


	private String code;    //权限代码
	private String name;    //权限名称


	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	@Override
	public int doAfterBody() throws JspException {

		return SKIP_BODY;
	}

	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {

			out = this.pageContext.getOut();

			List<TSControl> permissions = (List<TSControl>) pageContext.getSession().getAttribute(DataBaseConstant.Role_Permissions);

			boolean flog = true;
			for (TSControl permission : permissions){
				if (code.equals(permission.getControlCode())){
					flog = false;
				}
			}

			if (flog){
				out.print(bodyContent.getString());
			}

			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clear();
				out.close();
			} catch (Exception e2) {
			}
		}
		return EVAL_PAGE;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
