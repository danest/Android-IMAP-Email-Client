package com.email.android.kevinurrutia;

import java.io.Serializable;


/**
 * The Class Login.
 */
public class Login implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1874569235478615248L;
	
	/** The user id. */
	private String userID;
	
	/** The password. */
	private String password;
	
	/** The port. */
	private String port;
	
	/** The host name. */
	private String hostName;
	
	/**
	 * Instantiates a new login.
	 */
	public Login(){
		this.userID = "";
		this.password = "";
		this.port = "";
		this.hostName = "";
	}
	
	/**
	 * Instantiates a new login.
	 *
	 * @param userIDIN the user idin
	 * @param passwordIN the password in
	 * @param portIN the port in
	 * @param hostNameIN the host name in
	 */
	public Login(String userIDIN,String passwordIN,String portIN,String hostNameIN){
		this.userID = userIDIN;
		this.password = passwordIN;
		this.port = portIN;
		this.hostName = hostNameIN;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userID the new user id
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	/**
	 * Sets the host name.
	 *
	 * @param hostName the new host name
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	
	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public String getHostName() {
		return hostName;
	}
}
