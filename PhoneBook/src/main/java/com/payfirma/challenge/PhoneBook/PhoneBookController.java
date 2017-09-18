package com.payfirma.challenge.PhoneBook;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.Route;

public class PhoneBookController {

	private final Configuration freemarkerConfiguration;
	private ContactsDAO contactsDAO;

	public static void main(String[] args) {

		try {
			new PhoneBookController();
			
		} catch (Exception e) {
			//security blanket - good for testing
			e.printStackTrace();
		}
	}

	public PhoneBookController() {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("phonebook");
		final MongoCollection<Document> contacts = database.getCollection("contacts");

		contactsDAO = new ContactsDAO(contacts);
		freemarkerConfiguration = createFreemarkerConfiguration();
		initializeRoutes();

		// client.close();

	}

	private void initializeRoutes() {

		StringWriter sw = new StringWriter();		
		
		// Display the main page
		Spark.get("/index.html", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {

				System.out.println("get index");

				// empty the stringwriter
				sw.getBuffer().setLength(0);
				Template indexTemplate = freemarkerConfiguration.getTemplate("index.ftl");
				indexTemplate.process(null, sw);

				return sw.toString();
			}
		});

		// Process the contents page
		Spark.post("/index.html", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {

				System.out.println("post index");
				// empty the stringwriter
				sw.getBuffer().setLength(0);

				String contactInfo = "name%3D" + request.queryParams("name")
						+ "%26occupation%3D" + request.queryParams("occupation")
						+ "%26phone%3D" + request.queryParams("phone");

				String action = request.queryParams("action");
				
				//Redirect to relevant page
				if(action.equals("Add")){
					response.redirect("/addContact/" + contactInfo);
				} else if(action.equals("Find")){
					response.redirect("/findContact/" + contactInfo);					
				} else if(action.equals("Update")){
					response.redirect("/updateContact/" + contactInfo);					
				} else if(action.equals("Delete")){
					response.redirect("/findContact/" + contactInfo);
				}

				return sw.toString();
			}
		});
		
		Spark.get("/addContact/:contactInfo", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				sw.getBuffer().setLength(0);
				
				String[] contactInfo = getContactInfo(request.params(":contactinfo"));
				String name = contactInfo[0];
				String occupation = contactInfo[1];
				String phone = contactInfo[2];
				

				if(name == null){
					response.redirect("/index.html");
				} else if(name.isEmpty()){
					response.redirect("/error");
				}
				
				if (contactsDAO.addContact(name, occupation, phone)){
					response.redirect("/");
				} else {
					response.redirect("/error");
				}
				return "";
			}
		});

		Spark.get("/findContact/:contactInfo", new Route() {

			public Object handle(Request request, Response arg1) throws Exception {
				sw.getBuffer().setLength(0);

				String[] contactInfo = getContactInfo(request.params(":contactinfo"));
				String name = contactInfo[0];
				String occupation = contactInfo[1];
				String phone = contactInfo[2];
				
				List<Document> matchingNames = new ArrayList<>();

				try {

					Template findResults = freemarkerConfiguration.getTemplate("find_results.ftl");
					Map<String, Object> searchResults = new HashMap<>();

					matchingNames = contactsDAO.findContact(name, occupation, phone);
					searchResults.put("contacts", matchingNames);

					findResults.process(searchResults, sw);

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

				return sw.toString();
			}

		});
		
		Spark.get("/updateContact", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				sw.getBuffer().setLength(0);
				sw.append("Page not implemented");
				return sw.toString();
			}
		});
		
		Spark.get("/deleteContact", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				sw.getBuffer().setLength(0);
				sw.append("Page not implemented");
				return sw.toString();			}
		});
		
		Spark.get("/error", new Route() {
			
			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				
				sw.getBuffer().setLength(0);
				Map<String, Object> error = new HashMap<>();
				Template errorTemplate = freemarkerConfiguration.getTemplate("error.ftl");

				error.put("error", "System has encountered an error.");
				errorTemplate.process(error, sw);
                return sw.toString();
			}
		});
		
		Spark.get("/", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				sw.getBuffer().setLength(0);
				arg1.redirect("/index.html");
				return "";
			}
		});
	}

	private String[] getContactInfo(String recievedInfo) {
		String[] contactInfo = recievedInfo.split("&");
		String[] contactInfoRefined = new String[3];
		contactInfoRefined[0] = (contactInfo[0].split("=").length == 1)?"":contactInfo[0].split("=")[1];
		contactInfoRefined[1] = (contactInfo[1].split("=").length == 1)?"":contactInfo[1].split("=")[1];
		contactInfoRefined[2] = (contactInfo[2].split("=").length == 1)?"":contactInfo[2].split("=")[1];
		
		return contactInfoRefined;
	}
	
	private Configuration createFreemarkerConfiguration() {
		Configuration retVal = new Configuration(new Version("2.3.23"));
		retVal.setClassForTemplateLoading(PhoneBookController.class, "/");
		return retVal;
	}
}
