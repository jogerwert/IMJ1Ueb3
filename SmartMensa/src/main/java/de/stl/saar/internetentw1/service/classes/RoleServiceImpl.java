package de.stl.saar.internetentw1.service.classes;

import java.util.List;

import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.service.interfaces.RoleService;

/**
 * Teil der Service-Schicht. Greift auf RoleDao zu.
 * 
 * @author Michelle Blau
 */
public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	
	public RoleServiceImpl() {
		this.roleDao = RoleDaoImpl.getInstance();
	}

	@Override
	public void addRole(Role role) {
		this.roleDao.addRole(role);
		
	}

	@Override
	public void removeRoleById(int roleId) {
		this.roleDao.removeRole(roleId);
		
	}

	@Override
	public List<Role> findAllRoles() {
		return this.roleDao.findAllRoles();
	}

	@Override
	public Role findRoleByName(String roleName) {
		return this.roleDao.findRoleByName(roleName);
	}
	
	
}
