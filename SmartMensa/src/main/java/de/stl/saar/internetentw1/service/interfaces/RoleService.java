package de.stl.saar.internetentw1.service.interfaces;

import java.util.List;

import de.stl.saar.internetentw1.model.Role;

public interface RoleService {

	public void addRole(final Role role);
	
	public void removeRoleById(final int roleId);
	
	public List<Role> findAllRoles();
	
	public Role findRoleByName(final String roleName);
}
