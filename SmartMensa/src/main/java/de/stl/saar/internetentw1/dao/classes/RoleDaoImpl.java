package de.stl.saar.internetentw1.dao.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.utils.RandomUtils;
import de.stl.saar.internetentw1.utils.StringUtils;

/**
 * Simuliert die Role-Tabelle einer Datenbank mithilfe
 * einer Map, wobei der Primärschlüssel der Schlüssel der
 * Map ist. 
 * @author christopher
 *
 */
public class RoleDaoImpl implements RoleDao {
	private Map<Integer, Role> roleTable;
	
	public RoleDaoImpl() {
		roleTable = new HashMap<>();
		final Role role1 = new Role(1, "admin");
		final Role role2 = new Role(2, "user");
		addRole(role1);
		addRole(role2);
	}

	@Override
	public void addRole(final Role role) {
		int primaryKeyValue = role.getRoleId();
		
		if (primaryKeyValue > 0) {
			if (!primaryKeyValueFree(primaryKeyValue)) {
				primaryKeyValue = createPrimaryKeyValue();
			}
		} else {
			primaryKeyValue = createPrimaryKeyValue();
			role.setRoleId(primaryKeyValue);
		}
		
		roleTable.put(primaryKeyValue, role);
	}
	
	/**
	 * Erzeugt einen neuen Primärschlüsselwert. Dabei wird der Wert
	 * zufällig erzeugt und dann wird geprüft, ob es bereits einen
	 * Datensatz mit diesem Wert gibt. Falls nein, wird dieser
	 * Primärschlüsselwert zurückgegeben. 
	 * @return Der neu erzeugte und noch nicht vergebene Primärschlüsselwert.
 	 */
	private int createPrimaryKeyValue() {
		int primaryKey = 0;
		
		do {
			primaryKey = RandomUtils.nextInt();
		} while(!primaryKeyValueFree(primaryKey));
		
		return primaryKey;
	}
	
	/**
	 * Prüft, ob ein Primärschlüsselwert bereits vergeben ist. 
	 * @param primaryKeyValue Der zu prüfende Wert.
	 * @return True, falls der Wert bereits als Primärschlüsselwert in 
	 * der Tabelle vorkommt, sonst false. 
	 */
	private boolean primaryKeyValueFree(final int primaryKeyValue) {
		final Role roleWithPrimaryKey = roleTable.get(primaryKeyValue);
		if (roleWithPrimaryKey == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void removeRole(final int roleId) {
		roleTable.remove(roleId);
	}
	
	@Override
	public List<Role> findAllRoles() {
		final Collection<Role> roleCollection = roleTable.values();
		final List<Role> roles = new ArrayList<>(roleCollection);
		return roles;
	}
	
	@Override
	public Role findRoleByName(final String roleName) {
		final List<Role> roleList = findAllRoles();
		for (final Role role: roleList) {
			final boolean roleNameFound = StringUtils.areStringsEqual(roleName, role.getRoleName()); 
			if (roleNameFound) {
				return role;
			}
		}
		
		return null;
	}
}
