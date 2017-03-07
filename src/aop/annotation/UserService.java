package aop.annotation;
/*
 * UserService.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */

/**
 * 模拟aop对方法的日志等处理
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public interface UserService {
	public boolean update();
	public void delete();
	public void insert();
}
