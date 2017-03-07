package ioc;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MyIOC_DI {
	//构造方式注入
	private String msg;
	public MyIOC_DI(){}
	public MyIOC_DI(String m){
		this.msg = m;
	}
	//setter方式注入
	private Map<String, String> map;
	private Set<String> set;
	private List<String> list;
	private Properties props;
	public void setProps(Properties props) {
		this.props = props;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public void setList(List<String> list) {
		this.list = list;
	}

	public void getInfo(){
		System.out.println("构造方式住入："+msg);
		System.out.println("setter方式注入集合：");
		for (String s : list) {
			System.out.println(s);
		}
		for (String s : set) {
			System.out.println(s);
		}
		for (String key : map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}
		for (Object key : props.keySet()) {
			System.out.println(key+":"+props.getProperty((String)key));
		}
	}
}
