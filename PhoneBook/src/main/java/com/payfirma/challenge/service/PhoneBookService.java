package com.payfirma.challenge.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Contacts")
public class PhoneBookService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	//call link: HostingService/BlueExchangeEligibilityService-${VERSION}/XmlLoggingFlag/get
	public String getXmlLoggingFlag() {
		return "{\"worked\":true}";
	}	
	
}
