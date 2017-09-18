package com.payfirma.challenge.PhoneBook;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class TestPhoneBookController {

	@Test
	public void test() {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("phonebook");
		final MongoCollection<Document> contacts = database.getCollection("contacts");

		ContactsDAO contactsDAO = new ContactsDAO(contacts);
		final Configuration freemarkerConfiguration = createFreemarkerConfiguration();

		StringWriter sw = new StringWriter();
		//String name = request.params(":name");
		List<Document> matchingNames = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		try {

			Template helloTemplate = freemarkerConfiguration.getTemplate("table.ftl");
			Map<String, Object> helloMap = new HashMap<>();

			matchingNames = contactsDAO.findContact("", "", "");//("Yousuf");

			helloMap.put("contacts", matchingNames);

			helloTemplate.process(helloMap, sw);
			sb.append(sw);
			System.out.println(sb);

			// for (Document document : matchingNames) {
			// sb.append(Helpers.printJson(document)).append("\n");
			// }

			// helloMap.put("name", name);
			//
			//
			//
			// helloTemplate.process(helloMap, sw);

		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Configuration createFreemarkerConfiguration() {
		Configuration retVal = new Configuration();
		retVal.setClassForTemplateLoading(PhoneBookController.class, "/");
		return retVal;
	}
}
