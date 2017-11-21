package com.jxgis.ffireserver.web.system.manager;

import com.jxgis.ffireserver.web.system.pojo.base.Client;

import java.util.Comparator;

public class ClientSort implements Comparator<Client> {

	
	public int compare(Client prev, Client now) {
		return (int) (now.getLogindatetime().getTime()-prev.getLogindatetime().getTime());
	}

}
