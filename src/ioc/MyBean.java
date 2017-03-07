package ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBean implements InitializingBean , DisposableBean{
	/**
	 * 这两个为继承接口实现的初始化和销毁程序
	 * 和配置的方法同样起作用
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init-implements");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy-implements");
	}
	
	public MyBean(){
		System.out.println("创建mybean");
	}
	
	public void myinit(){
		System.out.println("init");
	}
	
	public void mydestroy(){
		System.out.println("destroy");
	}
	
	public void print(){
		System.out.println("打印信息");
	}
}
