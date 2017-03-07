/*
 * HelloBeanEN.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package ioc;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月22日
 */
public class HelloBeanEN implements HelloBean {
	/**
	 * 不常用，spring的代理类赋值
	 */
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void printHello() {
		System.out.println("Hello World");
	}

}
