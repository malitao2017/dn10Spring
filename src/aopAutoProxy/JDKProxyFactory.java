package aopAutoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyFactory implements InvocationHandler {

	private Object target;
	//单例模式
	private JDKProxyFactory(){}
	public static JDKProxyFactory getInstance(){
		return new JDKProxyFactory();
	}
	
	/**
	 * 目标对象类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class cla) throws Exception{
		//获得对象实例
		target = cla.newInstance();
		//根据目标对象创建代理对象
		return Proxy.newProxyInstance(cla.getClassLoader(), cla.getInterfaces(), this);
	}
	/**
	 * 每次通过代理对象调用目标方法时，执行该方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		Object retVal = null;
		try{
			System.out.println("调用前置通知");
			//调用目标对象处理
			retVal = method.invoke(target, params);
			System.out.println("调用后置returning通知");
		}catch(Exception e){
			System.out.println("调用后置异常通知");
		}finally{
			System.out.println("调用最终通知");
		}
		return retVal; 
	}
}








