package look.imp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import look.imp.models.Job;

@Repository
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
private SessionFactory sessionFactory;
	public void addJob(Job job) {
	Session session=sessionFactory.getCurrentSession();
	session.save(job);	
	}

}
