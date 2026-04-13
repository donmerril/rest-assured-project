package restAPIAutomation;

import java.util.List;
import java.util.Set;

import io.restassured.response.Response;

// this class is added to get both bookIDS and the api responses of the addbook api

public class AddBookResult {
	
	private Set<String> bookids;
	private List<Response> responses;
	
	public AddBookResult(Set<String> bookIds, List<Response> APIresponses) {
		
		this.bookids = bookIds;
		this.responses = APIresponses;
		
		
	}
	
	
	public Set<String> getBooksIDs(){
		
		return bookids;
	}
	
	public List<Response> getAddBooksResponses(){
		
		return responses;
	}

}
