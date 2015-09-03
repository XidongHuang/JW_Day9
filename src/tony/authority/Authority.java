package tony.authority;

public class Authority {

	
	//The authority name shown on the page
	private String name;
	
	
	//The URL address for authority:
	private String URL;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getURL() {
		return URL;
	}


	public void setURL(String URL) {
		this.URL = URL;
	}


	public Authority(String name, String URL) {
		super();
		this.name = name;
		this.URL = URL;
	}


	public Authority() {
		super();
	}
	
	
	
	
}
