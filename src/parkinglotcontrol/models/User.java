package parkinglotcontrol.models;

import java.io.Serializable;

import javax.crypto.SecretKey;

public class User implements Serializable {

	private static final long serialVersionUID = 7015544203958162046L;
	private String username;
	private String email;
	private byte[] password;
	private SecretKey key;
	
	
	public User(String username, String email, byte[] password, SecretKey key) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.key = key;
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


	public byte[] getPassword() {
		return password;
	}


	public void setPassword(byte[] password) {
		this.password = password;
	}


	public SecretKey getKey() {
		return key;
	}


	public void setKey(SecretKey key) {
		this.key = key;
	}
	
}
