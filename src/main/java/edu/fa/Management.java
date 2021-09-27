package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import edu.fa.model.Address;
import edu.fa.model.Course;
import edu.fa.model.Fresher;
import edu.fa.model.Group;
import edu.fa.model.Syllabus;

public class Management {

	public static void main(String[] args) {
//		createCouseSyllabuses();
//		getCourseSyllabuses(1);
//		createFresherAndAddress();
//		createFresherAndCourse();
//		createFresherAndGroup();
		createGroup();
		showFirstLevel();
//		useNamedQuery();
//		useCriteria();
//		deleteGroupUsingHQL();
//		updateGroupUsingHQL();
//		queryGroupUsingHQL();
//		getGroup();
		ConnectionUtil.getSessionFactory().close();
	}
	
	private static void showFirstLevel() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group group1 = (Group)session.get(Group.class, 1);			
			System.out.println(group1);
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			group1 = (Group)session.get(Group.class, 1);
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	
	private static void useNamedQuery() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("groupByName");
			query.setParameter("name", "Java Group");
			System.out.println(query.list());
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	private static void useCriteria() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria groupCriteria = session.createCriteria(Group.class);
			groupCriteria.add(Restrictions.eq("id", 1));
			System.out.println(groupCriteria.list());
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	private static void deleteGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "delete from Group WHERE id= :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			int result = query.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	private static void updateGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "UPDATE Group set name= :name WHERE id= :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			query.setParameter("name", "Funny Java Group");
			int result = query.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void queryGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "FROM Group WHERE id = ?";
			Query query = session.createQuery(queryStr);
			query.setInteger(0, 1);
			List<Group> groups = (List<Group>)query.list();
			System.out.println(groups);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void getGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = (Group) session.get(Group.class, 1);
			System.out.println(javaGroup);
			javaGroup.setName("New Java Group");
			session.update(javaGroup);
//			session.delete(javaGroup);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void createGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = new Group("Java Group");
			Group jsGroup = new Group("Js Goup");
			session.save(javaGroup);
			session.save(jsGroup);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void createFresherAndGroup() {

		Fresher fresher1 = new Fresher();
		Fresher fresher2 = new Fresher();
		Group group1 = new Group("Group 1");
		Group group2 = new Group("Group 2");
		Set<Fresher> freshers = new HashSet<Fresher>();
		freshers.add(fresher1);
		freshers.add(fresher2);
		Set<Group> groups = new HashSet<Group>();
		groups.add(group1);
		groups.add(group2);
		fresher1.setName("fresher 1");
		fresher2.setName("fresher 2");
		fresher1.setGroups(groups);
		fresher2.setGroups(groups);
		group1.setFreshers(freshers);
		group2.setFreshers(freshers);

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresher1);
			session.save(fresher2);
			session.save(group1);
			session.save(group2);

			session.getTransaction().commit();
//			Course hibernate = (Course) session.get(Course.class, 2);
//			System.out.println(hibernate);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void createFresherAndCourse() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Java"));
		courses.add(new Course("Hibernate"));

		Fresher fresher = new Fresher("Thang", courses);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for (Course course : courses) {
				session.save(course);
			}
			session.save(fresher);
			session.getTransaction().commit();
//			Course hibernate = (Course) session.get(Course.class, 2);
//			System.out.println(hibernate);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void createFresherAndAddress() {
		Address address = new Address("huynh lam", "hoa hai");
		Fresher fresher = new Fresher("Than", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(address);
			session.save(fresher);
			session.getTransaction().commit();
//			Course hibernate = (Course) session.get(Course.class, 2);
//			System.out.println(hibernate);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void getCourseSyllabuses(int id) {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Course course = (Course) session.get(Course.class, id);
			System.out.println(course.getName());
			System.out.println(course.getSyllabuses());
//			Course hibernate = (Course) session.get(Course.class, 2);
//			System.out.println(hibernate);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createCouseSyllabuses() {
		List<Syllabus> syllabuses = new ArrayList<Syllabus>();
		syllabuses.add(new Syllabus("Hibernate online content", 30));
		syllabuses.add(new Syllabus("Hibernate ofline content", 50));

		Course course = new Course("Hibernate", new Date(), syllabuses);
//		Configuration configuration = new Configuration();
//		configuration.configure();
//		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
//				.build();
//		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
//			Course hibernate = (Course) session.get(Course.class, 2);
//			System.out.println(hibernate);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
