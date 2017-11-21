package com.jxgis.ffireserver.core.util;

import com.jxgis.ffireserver.web.bean.TSFunction;

import java.util.List;
import java.util.Map;

/**
 * Created by YanWei on 2017/6/17.
 *
 * 界面左侧菜单工具类
 *
 * 当确定不再使用H+风格时，该类可删除
 *
 * @author 言伟
 */
public class MenuUtil {

	private static String getHplusSubMenu(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
		StringBuffer menuString = new StringBuffer();
		List<TSFunction> list = map.get(level);
		for (TSFunction function : list) {
			if (function.getFunctionParent().getId().equals(parent.getId())){
				if(!function.hasSubFunction(map)){
					menuString.append(getLeafOfHplusTree(function,map));
				}else{
					menuString.append(getLeafOfHplusTree(function,map));

				}
			}
		}
		return menuString.toString();
	}

	private static String getLeafOfHplusTree(TSFunction function,Map<Integer, List<TSFunction>> map) {
		StringBuffer menuString = new StringBuffer();


		menuString.append("<li> <a class=\"J_menuItem\" href=\"").append(function.getFunctionUrl()).append("\">");
		if(!function.hasSubFunction(map)){

		}else {

		}
		return menuString.toString();
	}
}
