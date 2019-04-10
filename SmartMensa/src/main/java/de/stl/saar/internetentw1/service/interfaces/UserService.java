package de.stl.saar.internetentw1.service.interfaces;


import java.util.List;

import de.stl.saar.internetentw1.model.User;

public interface UserService {

	public void addUser (final User user);
	
	public void removeUserById(final int userId);
	
	public List<User> findAllUsers();
	
	public User findUserByName(String username);
	
	public boolean doesUsernameExist(String username);
	
}
