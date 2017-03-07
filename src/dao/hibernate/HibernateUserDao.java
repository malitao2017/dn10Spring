/*
 * HibernateDao.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao.hibernate;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import dao.User;
import dao.UserDao;

/**
 * spring对hibernate的支持
 * @author LT
 * @version 1.0, 2015年10月29日
 */
public class HibernateUserDao extends HibernateDaoSupport implements UserDao {

	//若是不继承 HibernateDaoSupport 可用此方法
//	private HibernateTemplate hibernateTemplate;
//	public void setSessionFactory(SessionFactory sessionFactory){
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findById(int id) {
		User user = null;
		//第一种方式
		user = (User) this.getHibernateTemplate().get(User.class, id);
		//第二种方式
		String hql = "from User where id = ?";
		List<User> list  = this.getHibernateTemplate().find(hql,new Object[]{id});
		if(null != list && !list.isEmpty())
			user = list.get(0);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String hql = " from User ";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings({"rawtypes"})
	@Override
	public int maxId() {
		String hql = "select max(id) from User";
		List list = this.getHibernateTemplate().find(hql);
		return Integer.parseInt(list.get(0).toString());
	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public void deleteById(int id) {
		User user = findById(id);
		this.getHibernateTemplate().delete(user);
	}
}

