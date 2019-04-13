package de.stl.saar.internetentw1.model;

import de.stl.saar.internetentw1.utils.StringUtils;

public class User {
	private static final String ADMIN = "admin";
	
	private int userId;
	private String username;
	private String password;
	private Role role;
	private boolean isPasswordChangeNecessary;

	public User(int userId, String username, String password, Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isPasswordChangeNecessary = false;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Ermittelt ob das aktuelle User-Objekt ein Admin ist
	 * 
	 * @author Michelle Blau
	 * @return true, falls Admin, sonst false
	 */
	public boolean isAdmin() {
		return StringUtils.areStringsEqual(this.role.getRoleName(), ADMIN);
	}

	public boolean getIsPasswordChangeNecessary() {
		return isPasswordChangeNecessary;
	}

	public void setIsPasswordChangeNecessary(boolean isPasswordChangeNecessary) {
		this.isPasswordChangeNecessary = isPasswordChangeNecessary;
	}
}
