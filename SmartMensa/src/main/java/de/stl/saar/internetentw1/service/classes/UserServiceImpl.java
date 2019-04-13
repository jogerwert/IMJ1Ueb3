package de.stl.saar.internetentw1.service.classes;

import java.util.List;


import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.service.interfaces.UserService;

/**
 * Teil der Service-Schicht. Greift auf UserDao zu.
 * 
 * @author Michelle Blau
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl() {
		this.userDao = UserDaoImpl.getInstance();
	}
	
	@Override
	public void addUser(User user) {
		this.userDao.addUser(user);
		
	}

	@Override
	public void removeUserById(int userId) {
		this.userDao.removeUser(userId);
		
	}

	@Override
	public List<User> findAllUsers() {
		return this.userDao.findAllUsers();
	}

	@Override
	public User findUserByName(String username) {
		return this.userDao.findUserByName(username);
	}

	@Override
	public boolean doesUsernameExist(String username) {
		User tmpUser = findUserByName(username);
		
		if(tmpUser != null) {
			return true;		
		}
		
		return false;
	}

}
