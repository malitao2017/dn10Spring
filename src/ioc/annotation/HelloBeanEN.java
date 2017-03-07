/*
 * HelloBeanEN.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package ioc.annotation;

import org.springframework.stereotype.Service;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月22日
 */
@Service("helloBeanEN")
public class HelloBeanEN implements HelloBean {
	@Override
	public void printHello() {
		System.out.println("Hello World");
	}

}
