package Payloads;

public class Payload {
	
	
	
	public static String addBook(String name, String aisle, String ISBN, String author) {
		
		return"{\r\n"
				+ "\r\n"
				+ "\"name\":\""+name+"\",\r\n"
				+ "\"isbn\":\""+ISBN+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\""+author+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
	
	public static String deleteBook(String bookID) {
		
		
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+bookID+"\"\r\n"
				+ " \r\n"
				+ "} ";
			 
	
	}
	
	

}
