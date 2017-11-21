package com.jxgis.ffireserver.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces({ MediaType.APPLICATION_XML })
@Path("timeService")
public interface TimeService
{
	@POST
	@Path("getDateTime")
	public String getDateTime();
}
