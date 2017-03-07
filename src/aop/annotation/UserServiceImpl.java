/*
 * UserServiceImpl.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package aop.annotation;

import org.springframework.stereotype.Service;

/**
 * 运行的service程序
 * @author LT
 * @version 1.0, 2015年10月29日
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public boolean update() {
		System.out.println("update...");
		return true;
	}

	@Override
	public void delete() {
		System.out.println("delete...");
	}

	@Override
	public void insert() {
		System.out.println("insert...");
		System.out.println("错误：" + Integer.valueOf("ssd1"));
	}

}
