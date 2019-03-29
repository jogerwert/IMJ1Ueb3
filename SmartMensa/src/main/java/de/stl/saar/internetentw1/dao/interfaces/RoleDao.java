package de.stl.saar.internetentw1.dao.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.model.Role;

public interface RoleDao {

	void addRole(Role role);

	void removeRole(int roleId);

	List<Role> findAllRoles();

	Role findRoleByName(String roleName);

}
