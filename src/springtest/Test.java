/*
 * base.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package springtest;

import ioc.HelloBeanEN;
import ioc.MyBean;
import ioc.MyIOC_DI;
import ioc.UseBean;

import org.junit.Assert;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import dao.User;
import dao.UserDao;
import aop.UserService;
import aopAutoProxy.CGLIBProxyFactory;
import aopAutoProxy.JDKProxyFactory;
import aopAutoProxy.ProxyService;
import aopAutoProxy.ProxyServiceImpl;

/**
 * spring特性实验
 * @author LT
 * @version 1.0, 2015年10月22日
 */
public class Test {
	
	public static void main(String[] args) throws Exception {
		Test base = new Test();
		/**
		 * 基础特性
		 */
		//1 spring 配置文件读取
		base.mySpring();
		//2 创建bean的属性
		base.myScope();
		
		/**
		 * IOC
		 */
		//3 IOC的DI依赖注入两种方式
		base.myIOC();
		//4 IOC注解的方式
		base.myIOCAnnotation();
		
		/**
		 * AOP
		 */
		//5 AOP
		base.myAOP();
		//6 AOP的注解模式
		base.myAOPAnnotation();
		
		/**
		 * spring 对数据库的支持
		 * 增加断言Assert.assertEquals
		 * 注意： new Object[]{obj1,obj2}
		 */
		//7 spring对jdbc的支持
		base.myDaoJdbc();
		//8 spring对hibernate的支持
		base.myDaoHibernate();
		
		/**
		 * AOP动态代理
		 * 1.有接口的使用jdk反射即可
		 * 2.没有接口的使用CGLIB
		 */
		base.myProxyJDK();
		base.myProxyCGLIB();
	}
	/**
	 * 1. java调用spring 
	 * 容器的生成方式
	 */
	public void mySpring(){ 
		//方式一 applicationContext  类路径 文件系统硬盘路径
		ApplicationContext ac = null;
		ac = new ClassPathXmlApplicationContext("ioc.xml");
//		ac = new FileSystemXmlApplicationContext("D:\\work\\eclipse\\workspace-jee\\dn10Spring\\src\\ioc.xml");
		UseBean useBean = (UseBean)ac.getBean("useBean");
		useBean.use();
		
		//方式二 beanFactory  类路径 文件系统硬盘路径
		Resource resource = null;
		resource = new ClassPathResource("ioc.xml");
//		resource = new FileSystemResource("D:\\work\\eclipse\\workspace-jee\\dn10Spring\\src\\ioc.xml");
		BeanFactory bf = new XmlBeanFactory(resource);
		UseBean useBeanbf = (UseBean)bf.getBean("useBean");
		useBeanbf.use();
		
		//方式三 只能按类来,相当于动态代理
		BeanWrapper beanWrapper = new BeanWrapperImpl(new HelloBeanEN());
		beanWrapper.setPropertyValue("msg", "这是外部注入");
		System.out.println(beanWrapper.getPropertyValue("msg"));
	}
	
	/**
	 * spring管理bean的属性：
	 * 1.创建模式  <bean scope = singleton(单例)、prototype(原型)、WEB相关: request、 session、 global session
	 * 2.创建时机 延时加载 <bean lazy-init  批量延时 <beans defualt-lazy-init
	 * 3.初始化和销毁方法 <bean init-method destroy-method（只适用于单例）  批量 <beans defual-init-method defualt-destroy-method
	 */
	@SuppressWarnings("unused")
	public void myScope(){
//		System.out.println("1.默认情况:没有延时加载  创建为单例模式");
//		//<bean id="myBean" class="bean.MyBean" ></bean>
//		ApplicationContext ac = new ClassPathXmlApplicationContext("ioc.xml");
//		MyBean myBean = (MyBean)ac.getBean("myBean");
		
//		System.out.println("2.创建模式: ");
//		//<bean id="myBean" class="bean.MyBean" ></bean>
//		//<bean id="myBean" class="bean.MyBean" scope="prototype" ></bean>
//		ApplicationContext ac = new ClassPathXmlApplicationContext("ioc.xml");
//		System.out.println( "单例/原型: " + ((MyBean)ac.getBean("myBean") == (MyBean)ac.getBean("myBean")));
		
//		System.out.println("3.创建时机：");
////		<bean id="myBean" class="bean.MyBean"  lazy-init="true" ></bean>
//		ApplicationContext ac = new ClassPathXmlApplicationContext("ioc.xml");
//		System.out.println("--延时加载--");
////		MyBean lazy = (MyBean)ac.getBean("myBean");
		
		System.out.println("4.初始化和销毁方法,销毁仅对单例有效。有两种方式：配置和接口");
//		<bean id="myBean" class="bean.MyBean"  init-method="myinit" destroy-method="mydestroy"></bean>
		AbstractApplicationContext aac =  new ClassPathXmlApplicationContext("ioc.xml");
		MyBean id = (MyBean)aac.getBean("myBean");
		aac.close();
	}
	
	/**
	 * DI是ioc的一种手段，ioc通过di来实现
	 * ioc的依赖注入DI两种方式
	 * 1.setter方式注入
	 * 2.构造方式注入
	 */
	public void myIOC(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ioc.xml");
		MyIOC_DI di = (MyIOC_DI)ac.getBean("DI");
		di.getInfo();
	}
	
	/**
	 * annotation注解的方式
	 * 1.注解的方式可以不用定义名称，默认是类名，改为类名开头小写的形式：如 UseBean 会自动装成 useBean
	 * 2.两种方式
	 * 	Resource 是先按类型后按名称 ，推荐使用
	 * 	Autowired 是先按名称后类型 ，若是接口有多个实现则配合使用 Qualifier("id名")
	 * 3.注解的方式不用写setter
	 * 4.支持 创建模式 初始化和销毁程序
	 */
	public void myIOCAnnotation(){
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("ioc_annotation.xml");
		ioc.annotation.UseBean useBean = (ioc.annotation.UseBean)ac.getBean("useBean");
		System.out.println("已经spring进行扫描");
		useBean.use();
		ac.close();//单例可支持程序销毁
	}
	
	
	/**
	 * aop的相应内容
	 */
	public void myAOP(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
		UserService us = (UserService)ac.getBean("userService");
		us.update();
		us.delete();
		try {
			us.insert();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * aop的注解模式
	 */
	public void myAOPAnnotation(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop_annotation.xml");
		aop.annotation.UserService us = (aop.annotation.UserService)ac.getBean("userService");
		us.update();
		us.delete();
		try {
			us.insert();
		} catch (Exception e) {}
	}
	
	
	/**
	 * spring 对jdbc的支持
	 * 增加断言,注意： new Object[]{obj1,obj2}
	 */
	public void myDaoJdbc(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("dao.xml");
		UserDao dao = (UserDao)ac.getBean("jdbcUserDao");
		//按id查询
		User user = dao.findById(1);
		System.out.println("findById:"+user.getEmail());
		//加junit断言
		Assert.assertEquals("admin", user.getEmail());
		/**
		 * 其他的方法
		 */
		System.out.println("findAll:"+dao.findAll().size());
		user = new User("email1jdbc","pwd1jdbc","details1jdbc");
		dao.save(user);
		int maxId = dao.maxId();
		System.out.println("maxId:"+maxId);
		user.setDetails("details1jdbcUpdate");user.setId(maxId);
		dao.update(user);
		dao.deleteById(maxId);
	}
	
	public void myDaoHibernate(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("dao.xml");
		UserDao dao = (UserDao)ac.getBean("hibernateUserDao");
		System.out.println("findById:"+dao.findById(1).getEmail());
		System.out.println("findAll:"+dao.findAll().size());
		User user = new User("email1Hibernate","pwd1Hibernate","details1Hibernate");
		dao.save(user);
		int maxId = dao.maxId();
		user.setDetails("details1Update");user.setId(maxId);
		dao.update(user);
		dao.deleteById(maxId);
	}
	
	/**
	 * 动态代理，有接口的话，使用jdk的动态代理
	 * @throws Exception 
	 */
	public void myProxyJDK() throws Exception{
		JDKProxyFactory jdk = JDKProxyFactory.getInstance();
		ProxyService service = (ProxyService)jdk.getProxy(ProxyServiceImpl.class);
//		ProxyServiceImpl service = (ProxyServiceImpl)jdk.getProxy(ProxyServiceImpl.class);//没有接口的话就会出错
		System.out.println("有接口使用JDKProxy："+service.getClass());
		service.save();
		service.delete();
	}
	
	/**
	 * 动态代理，没有接口的话，使用CGLIB的动态代理
	 * @throws Exception 
	 */
	public void myProxyCGLIB() throws Exception{
		CGLIBProxyFactory cglib = CGLIBProxyFactory.getInstance();
		ProxyServiceImpl service = (ProxyServiceImpl) cglib.getProxy(ProxyServiceImpl.class);
		System.out.println("没有接口使用CGLIBProxy："+service.getClass());
		service.delete();
		service.save();
	}
}
