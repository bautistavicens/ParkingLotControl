package parkinglotcontrol.models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 7015544203958162046L;
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
