package de.stl.saar.internetentw1.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.stl.saar.internetentw1.dao.classes.DishDaoImpl;
import de.stl.saar.internetentw1.dao.classes.RoleDaoImpl;
import de.stl.saar.internetentw1.dao.classes.UserDaoImpl;
import de.stl.saar.internetentw1.dao.interfaces.DishDao;
import de.stl.saar.internetentw1.dao.interfaces.RoleDao;
import de.stl.saar.internetentw1.dao.interfaces.UserDao;

@Configuration
public class DaoConfiguration {
	@Bean(name = "dishDao")
	public DishDao createDishDao() {
		final DishDao dishDao = new DishDaoImpl();
		return dishDao;
	}
	
	@Bean(name = "userDao")
	public UserDao createUserDao() {
		final UserDao userDao = new UserDaoImpl();
		userDao.setRoleDao(createRoleDao());
		return userDao;
	}
	
	@Bean(name = "roleDao")
	public RoleDao createRoleDao() {
		final RoleDao roleDao = new RoleDaoImpl();
		return roleDao;
	}
}
