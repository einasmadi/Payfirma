package com.payfirma.challenge.PhoneBook;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloSpark {
	
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloSpark.class, "/");
		
		Spark.get("/", new Route() {
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				StringWriter sw = new StringWriter();

				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					Map<String, Object> helloMap = new HashMap<>();
					helloMap.put("name", "Einas");
					
					helloTemplate.process(helloMap, sw);
					
					
				} catch (TemplateNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedTemplateNameException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
				
				return sw.toString();
			}
		});
	}

}
