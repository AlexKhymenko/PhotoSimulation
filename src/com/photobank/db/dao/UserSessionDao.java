package com.photobank.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import com.photobank.db.entities.User;
import com.photobank.db.entities.UserSession;
import com.photobank.db.util.HibernateUtil;

public class UserSessionDao {
	public void setUserSession(Integer userId, String sessionId) {
		// Integer userId = 1;
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where  userId = :userId");
		query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		User user = users.get(0);
		UserSession userSession = new UserSession();
		userSession.setSessionId(sessionId);
		userSession.setUser(user);
		session.beginTransaction();
		session.save(user);
		session.save(userSession);
		session.getTransaction().commit();
		session.close();
	}

	public void deleteUserSession(Integer userId, String sessionId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// String sessionId = "Session2";
		org.hibernate.Query query = session
				.createQuery("FROM UserSession Where  sessionId = :sessionId");
		query.setString("sessionId", sessionId);
		List<UserSession> userSession = (List<UserSession>) query.list();

		// Integer userId = 1;
		query = session.createQuery("FROM User Where  userId = :userId");
		query.setInteger("userId", userId);
		List<User> user = (List<User>) query.list();
		System.out.println(user.get(0).getName());
		// Set null before delete of userSession
		List<UserSession> sessions = user.get(0).getUserSessions();

		session.beginTransaction();
		session.delete(userSession.get(0));
		session.getTransaction().commit();
		session.close();

	}

	public Integer getUserForSession(String sessionId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM UserSession Where  sessionId = :sessionId");
		query.setString("sessionId", sessionId);
		List<UserSession> user = (List<UserSession>) query.list();
		System.out.println(user.get(0).getUser().getUserId());
		session.close();
		return user.get(0).getUser().getUserId();

	}

}
