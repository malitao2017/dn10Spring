/*
 * HelloBeanZH.java
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
@Service("helloBeanZH")
public class HelloBeanZH implements HelloBean{

	@Override
	public void printHello() {
		System.out.println("你好，世界");
	}

}
