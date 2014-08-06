package com.photobank.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.photobank.db.entities.Password;
import com.photobank.db.entities.User;
import com.photobank.db.util.HibernateUtil;
import com.photobank.mail.Mail;

public class UserDao {
	public List<User> selectAll() {
		// Query
		return null;

	}

	public String checkPass(String email, String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where name = :name");
		query.setString("name", email);

		List<User> users = (List<User>) query.list();
		//session.close();
		if (users.isEmpty())
			return "Error";
		if (!pass.equals(users.get(0).getPassword().getPassword()))
			return "Error";
		if (!users.get(0).getActivated())
			return "Error";
		
		session.close();
		return email;
	}

	public Integer selectById(String userName) {
		// Query
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where name = :name");		
		//query.setInteger("userId", userId);
		query.setString("name",userName  );

		List<User> users = (List<User>) query.list();
		session.close();
		return users.get(0).getUserId();

	}

	public String addUser(String userName, String pass) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where name = :name");		
		//query.setInteger("userId", userId);
		query.setString("name",userName  );

		List<User> users = (List<User>) query.list();
		if (!users.isEmpty()){
			System.out.println("User name exists!");
			return "User name exists!";
		}
		//session.close();
		
		User user = new User();
		user.setName(userName);
		user.setActivation(false);//for activation
		user.setLocked(false);//for moderator. False - unlocked
		
		String uuid = UUID.randomUUID().toString();
		user.setSecretKey(uuid);
		Mail.sendMail(userName, uuid);
		
		Password password = new Password();
		password.setPassword(pass);
        user.setPassword(password);
	    password.setUser(user);
		
		session.beginTransaction();
		session.save(user);
		session.save(password);
		session.getTransaction().commit();
		session.close();
		return userName;
	}


	public void deleteUser(User userId) {

	}

	public void updateUser(User userId) {
		// Query
	}
	
	//For user activation. Get user by secretKey
	public Integer getUserBySecretKey(String secretKey){
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where secretKey = :secretKey");		
		//query.setInteger("userId", userId);
		query.setString("secretKey",secretKey);

		List<User> users = (List<User>) query.list();
		session.close();
		return users.get(0).getUserId();
	}
	
	public void activateUser(Integer userId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where userId = :userId");		
		//query.setInteger("userId", userId);
		query.setInteger("userId",userId );

		List<User> users = (List<User>) query.list();
		//Set true for activation
		users.get(0).setActivation(true);
		
		session.beginTransaction();
		session.save(users.get(0));//Change field of activation
		session.getTransaction().commit();
		session.close();
		
		
		
	}
	
	//Get all users for moderator
	public Map<Integer, String> getAllUsers() {
		Map<Integer, String> result = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User");		
		//query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		for(User u:users){
			result.put(u.getUserId(), u.getName());
		}
		session.close();
		return result;
	}

	//LockUser. For moderator page
	public void lockUser(Integer userId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where userId=:userId");
		query.setInteger("userId", userId);
		//query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		User user = users.get(0);
		user.setLocked(true);//True - lockedUser
		session.beginTransaction();
		session.save(user);//Change field of activation
		session.getTransaction().commit();
		session.close();
		
		
	}
	//UnLockUser. For moderator page
	public void unlockUser(Integer userId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Query query = session
				.createQuery("FROM User Where userId=:userId");
		query.setInteger("userId", userId);
		//query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		User user = users.get(0);
		user.setLocked(false);//false  - unLockUser
		session.beginTransaction();
		session.save(user);//Change field of activation
		session.getTransaction().commit();
		session.close();	
	}
	
	

}
