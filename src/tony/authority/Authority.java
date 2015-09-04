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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((URL == null) ? 0 : URL.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (URL == null) {
			if (other.URL != null)
				return false;
		} else if (!URL.equals(other.URL))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
