/*
 * jdbcDao.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dao.User;
import dao.UserDao;

/**
 * spring对jdbc的支持
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public class JdbcUserDao extends JdbcDaoSupport implements UserDao {
	
////	若不继承 jdbcdaosupport，可用此
//	private JdbcTemplate template;
//	public void setDataSource(DataSource dataSource) {
//		this.template = new JdbcTemplate(dataSource);
//	}

	@Override
	public User findById(int id) {
		String sql = "select * from d_user where id = ?  ";
		return (User) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserRowMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String sql = "select * from d_user";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());
	}

	@Override
	public int maxId(){
		String sql = "select max(id) from d_user";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public void save(User user) {
		String sql = "insert into d_user(email,password,details) values(?,?,?) ";
		this.getJdbcTemplate().update(sql,new Object[]{user.getEmail(),user.getPassword(),user.getDetails()});
	}

	@Override
	public void update(User user) {
		String sql = "update d_user set details=? where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{user.getDetails(),user.getId()});
	}

	@Override
	public void deleteById(int id) {
		String sql = "delete from d_user where id=?";
		this.getJdbcTemplate().update(sql,new Object[]{id});
	}
	
}
