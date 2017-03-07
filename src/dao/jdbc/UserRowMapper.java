/*
 * UserRowMapper.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dao.User;

/**
 * spring 的jdbc的对象封装接口
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public class UserRowMapper implements RowMapper{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setDetails(rs.getString("details"));
		return user;
	}

}
