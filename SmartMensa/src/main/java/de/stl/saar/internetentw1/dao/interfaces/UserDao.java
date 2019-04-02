package de.stl.saar.internetentw1.dao.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.model.User;

public interface UserDao {

	void addUser(User user);

	void removeUser(int userId);

	List<User> findAllUsers();

	void setRoleDao(RoleDao roleDao);

	User findUserByName(String username);
}
