/*
 * Dao.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao;

import java.util.List;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public interface UserDao {
	public User findById(int id);
	public List<User> findAll();
	public int maxId();
	public void save(User user);
	public void update(User user);
	public void deleteById(int id);
}
