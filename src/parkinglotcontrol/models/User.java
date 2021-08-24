package parkinglotcontrol.models;

public class User {
	private String username;
	private String email;
	private char[] password;
	
	
	public User(String username, String email, char[] password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public char[] getPassword() {
		return password;
	}


	public void setPassword(char[] password) {
		this.password = password;
	}


	
}
