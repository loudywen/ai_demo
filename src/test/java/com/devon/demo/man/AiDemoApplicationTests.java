package com.devon.demo.man;

import com.devon.demo.main.model.sapdetail.SapDetailRoot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;


public class AiDemoApplicationTests {

	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	ObjectMapper mapper = new ObjectMapper();
	String json ="{\"d\":{\"__metadata\":{\"id\":\"http://24.172.189.102:8011/sap/opu/odata/sap/ZSAP_USER_INFO_SRV/ZUserDetailSet('admin')\",\"uri\":\"http://24.172.189.102:8011/sap/opu/odata/sap/ZSAP_USER_INFO_SRV/ZUserDetailSet('admin')\",\"type\":\"ZSAP_USER_INFO_SRV.ZUserDetail\"},\"Username\":\"admin\",\"AgrName\":\"/PERSONAS/ADMIN_ROLE\",\"FromDat\":\"/Date(1462406400000)/\",\"ToDat\":\"/Date(253402214400000)/\",\"AgrText\":\"SAP Screen Personas Administrator with full access to every available feature.\",\"OrgFlag\":\"\"}}";
	@Test
	public void contextLoads() {
		try{
			SapDetailRoot sdr = mapper.readValue(json, SapDetailRoot.class);
			System.out.println("---- "+sdr.getD().getAgrtext());

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
