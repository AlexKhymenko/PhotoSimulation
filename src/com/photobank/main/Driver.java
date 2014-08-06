package com.photobank.main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.photobank.db.dao.PhotoDao;
import com.photobank.db.entities.Password;
import com.photobank.db.entities.Photo;
import com.photobank.db.entities.User;
import com.photobank.db.entities.UserRoleStr;
import com.photobank.db.entities.UserSession;
import com.photobank.db.util.HibernateUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.WebApplicationException;

public class Driver {

	public static void main(String[] args) {
		//http://www.mkyong.com/java/how-to-compare-dates-in-java/
		System.out.println(new java.util.Date());
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = sdf.parse("2014-07-30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where downloadDate=:date");
		query.setDate("date", date1);
		List<Photo> photoes = query.list();
		System.out.println(photoes);
		// info("hello");
//Проверка для загрузки фото
/*		File f = new File("D:/Current/Bank1/WebContent/pics/bug.jpg");

	     if (!f.exists()) {
	         throw new WebApplicationException(404);
	     }

	     String mt = new MimetypesFileTypeMap().getContentType(f);*/
		
		
/*		SessionFactory sessionFactory;
		ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	
		Set<String> pathes =  new PhotoDao().getAllUsersPhoto();
      System.out.println(pathes);*/
		
		//Check search by search key		
/*		PhotoDao search = new PhotoDao();
		search.makeSearch("bug");
		System.out.println();*/
		
		
//Code Work. Add user and password
/*	User user = new User();
		user.setName("Kolya");
		Password password = new Password();
		password.setPassword("1234");
		user.setPassword(password);
		password.setUser(user);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(password);
		session.getTransaction().commit();
		
        String name = "Kolya";
		org.hibernate.Query query = session .createQuery("FROM User Where name = :name"); 
		query.setString("name", name);
		List<User>
		  users = (List<User>) query.list();
		System.out.println(users.get(0).getName());
		session.close();*/
		// CODE WORK User dao - chaeck pass
/*		
		   session = sessionFactory.openSession(); org.hibernate.Query
		  query = session .createQuery("FROM User Where name = :name"); String
		  userName = "Kolya"; //query.setInteger("userId", userId);
		  query.setString("name",userName ); String pass = "1234"; List<User>
		  users = (List<User>) query.list(); if
		  (users.isEmpty())System.out.println("Error");
		  if(!pass.equals(users.get(0).getPassword().getPassword()))System.out.println("Error");
		  System.out.println(users.get(0).getName());
		  System.out.println(users.get(0).getPassword().getPassword());*/
		
		
		//Code Work. Add userSession for user
/*		Integer userId = 1;
		Session session = sessionFactory.openSession();
		org.hibernate.Query
		  query = session .createQuery("FROM User Where  userId = :userId");
		query.setInteger("userId",userId );
		List<User> users= (List<User>) query.list();
        User user = users.get(0);
				UserSession userSession =  new UserSession();
				userSession.setSessionId("Session1");
				userSession.setUser(user);
				session.beginTransaction();
				session.save(user);
				session.save(userSession);
				session.getTransaction().commit();
				session.close();*/
		
		//Code Work. Delete userSession of user
/*						Session session = sessionFactory.openSession();
						String sessionId = "Session2";
						org.hibernate.Query
						  query = session .createQuery("FROM UserSession Where  sessionId = :sessionId");
						query.setString("sessionId",sessionId ); 
						List<UserSession> userSession = (List<UserSession>) query.list();
												
						Integer userId = 1;
						  query = session .createQuery("FROM User Where  userId = :userId");
						query.setInteger("userId",userId ); 
						List<User> user= (List<User>) query.list();
						System.out.println(user.get(0).getName());
						//Set null before delete of userSession
						List<UserSession> sessions = user.get(0).getUserSessions();
					
						session.beginTransaction();
						session.delete(userSession.get(0));
						session.getTransaction().commit();
						session.close();*/
						
	//Get session of of user			
/*		Session session = HibernateUtil.getSessionFactory().openSession();
		String sessionId = "Session1";
		Query query = session
				.createQuery("FROM UserSession Where  sessionId = :sessionId");
		query.setString("sessionId", sessionId);
		List<UserSession> user = (List<UserSession>) query.list();
		System.out.println(user.get(0).getUser().getUserId());*/
		
		
		//WORK.Add photo to user
	/*	User user = new User();
		
		 String name = "Kolya";
	    Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session .createQuery("FROM User Where name = :name");
		query.setString("name", name);
		 List<User> users = (List<User>) query.list();
		 Photo photo1 = new Photo();
		 photo1.setPath("C:/a.jpg");
		 Photo photo2 = new Photo();
		 photo2.setPath("C:/b.jpg");
		 
		 photo1.setUser(users.get(0));
		 photo2.setUser(users.get(0));
		 session.beginTransaction();
			session.save(photo1);
			session.save(photo2);
			session.getTransaction().commit();
		
		 //Get All photo of user
		Set<Photo> photoes = users.get(0).getPhotoes();
		for(Photo p:photoes){
			System.out.println(p.getPath());
		}
		System.out.println(users.get(0).getName());
		*/
		
		
						// Add user roles to DB UserRoleStr
		/*
		 * Session session = sessionFactory.openSession(); UserRoleStr roleStr1
		 * = new UserRoleStr(); roleStr1.setRoleId(1);
		 * roleStr1.setRoleStr("Employee");
		 * 
		 * UserRoleStr roleStr2 = new UserRoleStr(); roleStr2.setRoleId(2);
		 * roleStr2.setRoleStr("Moderator");
		 * 
		 * UserRoleStr roleStr3 = new UserRoleStr(); roleStr3.setRoleId(3);
		 * roleStr3.setRoleStr("Admin");
		 * 
		 * session.beginTransaction(); session.save(roleStr1);
		 * session.save(roleStr2); session.save(roleStr3);
		 * session.getTransaction().commit(); session.close();
		 */

	}

	/*
	 * public static void info(Object obj){ Date date = new Date();
	 * System.out.println(date + " " +obj.toString()); }
	 */

}
