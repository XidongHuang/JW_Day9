package tony.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tony.authority.Authority;
import tony.authority.User;

public class UserDao {

	private static Map<String, User> users;

	private static List<Authority> authorities = null;

	static {

		users = new HashMap<String, User>();
		authorities = new ArrayList<>();
		authorities.add(new Authority("Article-1", "/article-1.jsp"));
		authorities.add(new Authority("Article-2", "/article-2.jsp"));
		authorities.add(new Authority("Article-3", "/article-3.jsp"));
		authorities.add(new Authority("Article-4", "/article-4.jsp"));

		User user1 = new User("AAA", authorities.subList(0, 2));
		users.put("AAA", user1);

		user1 = new User("BBB", authorities.subList(2, 4));
		users.put("BBB", user1);

	}

	public List<Authority> getAuthorities() {
		return authorities;

	}

	public User get(String username) {
		User user = users.get(username);

		return user;
	}

	public void update(String username, List<Authority> authorities) {
		users.get(username).setAuthorities(authorities);

	}

	public List<Authority> getAuthorities(String[] urls) {

		List<Authority> authorities2 = new ArrayList<>();

		for (Authority authority : authorities) {
			if (urls != null) {
				for (String url : urls) {
					if (url.equals(authority.getURL())) {
						authorities2.add(authority);

					}
				}
			}

		}

		return authorities2;
	}

}
