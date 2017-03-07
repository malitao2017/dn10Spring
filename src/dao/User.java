/*
 * User.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao;

/**
 * 表 d_user 的spring的jdbc的支持
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public class User{
	private int id;
	private String email;
	private String password;
	private String details;
	public User(){}
	public User(String email,String password,String details){
		this.email = email;
		this.password = password;
		this.details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
