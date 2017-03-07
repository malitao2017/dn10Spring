/*
 * userBean.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package ioc;

/**
 * 
 * @author LT
 * @version 1.0, 2015年10月22日
 */
public class UseBean {
	
	private HelloBean helloBean;
	
	public void use(){
		helloBean.printHello();
	}

	public void setHelloBean(HelloBean helloBean) {
		this.helloBean = helloBean;
	}
	
}
