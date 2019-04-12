package de.stl.saar.internetentw1.dao.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.utils.RandomUtils;

/**
 * Simuliert die User-Tabelle einer Datenbank mithilfe
 * einer Map, wobei der Primärschlüssel der Schlüssel der
 * Map ist. 
 * @author christopher
 * @author Johannes Gerwert
 *
 */
public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl INSTANCE;
	
	private Map<Integer, User> userTable;
	private RoleDao roleDao;
	
	private UserDaoImpl() {
		userTable = new HashMap<>();
		roleDao = RoleDaoImpl.getInstance();
		final Role adminRole = roleDao.findRoleByName("admin");
		final Role userRole = roleDao.findRoleByName("user");
		final User user1 = new User(1, "colbertz", "1234", adminRole);
		final User user2 = new User(2, "wpy", "qwertz", userRole);
		final User user3 = new User(3, "api", "5678", userRole);
		addUser(user1);
		addUser(user2);
		addUser(user3);
	}
	
	/**
	 * Implementiert das Singleton Entwurfsmuster.
	 * Wird genutzt, um eine Datenbank zu emulieren.
	 * 
	 * @return Das UserDaoImpl Objekt.
	 */
	public static UserDaoImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new UserDaoImpl();
		}
		
		return INSTANCE;
	}

	@Override
	public void addUser(final User user) {
		int primaryKeyValue = user.getUserId();
		
		if (primaryKeyValue > 0) {
			if (!primaryKeyValueFree(primaryKeyValue)) {
				primaryKeyValue = createPrimaryKeyValue();
			}
		} else {
			primaryKeyValue = createPrimaryKeyValue();
			user.setUserId(primaryKeyValue);
		}
		
		userTable.put(primaryKeyValue, user);
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
		final User userWithPrimaryKey = userTable.get(primaryKeyValue);
		if (userWithPrimaryKey == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void removeUser(final int userId) {
		userTable.remove(userId);
	}
	
	@Override
	public List<User> findAllUsers() {
		final Collection<User> userCollection = userTable.values();
		final List<User> users = new ArrayList<>(userCollection);
		return users;
	}
	
	@Override
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public User findUserByName(String username) {
		User resultUser = null;
		final Collection<User> userCollection = userTable.values();
		final List<User> userList = new ArrayList<>(userCollection);
		
		for(User u : userList) {
			String cmpName = u.getUsername();
			if(username.equals(cmpName)) {
				resultUser = u;
			}
		}

		return resultUser;
	}
}
