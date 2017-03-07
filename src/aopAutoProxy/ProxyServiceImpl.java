package aopAutoProxy;

public class ProxyServiceImpl implements ProxyService{

	@Override
	public void save() {
		System.out.println("...保存方法");
	}

	@Override
	public void delete() {
		System.out.println("...删除方法");
	}

}
