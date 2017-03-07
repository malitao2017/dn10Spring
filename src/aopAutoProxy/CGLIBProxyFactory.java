package aopAutoProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxyFactory implements MethodInterceptor{

	private Object target;
	private CGLIBProxyFactory(){}
	public static CGLIBProxyFactory getInstance(){
		return new CGLIBProxyFactory();
	}
	
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class cla) throws Exception{
		target = cla.newInstance();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cla);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] params,
			MethodProxy arg3) throws Throwable {
		Object retVal = null;
		try{
			System.out.println("调用前置通知");
			retVal = method.invoke(target, params);
			System.out.println("调用后置return执行正确通知");
		}catch(Exception e){
			System.out.println("调用异常通知");
		}finally{
			System.out.println("调用最终通知");
		}
		return retVal;
	}

}
