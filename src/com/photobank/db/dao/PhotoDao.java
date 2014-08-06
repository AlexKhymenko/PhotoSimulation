package com.photobank.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.photobank.db.entities.Photo;
import com.photobank.db.entities.User;
import com.photobank.db.entities.UserSession;
import com.photobank.db.util.HibernateUtil;

public class PhotoDao {
	// addPhoto
	// deletePhoto
	// updatePhoto
	// select
	//For one user by ID
	public Set<String> getAllUserPhoto(Integer userId) {
		Set<String> arrPath = new HashSet<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		//userId = 1; закомитил
		Query query = session.createQuery("FROM User Where userId = :userId");
		query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		Set<Photo> photoes = users.get(0).getPhotoes();
		//Add photoes path of userID
		for(Photo p:photoes){
			arrPath.add(p.getPath());
		}
		session.close();
		return arrPath;

	}
	
	
	/***********/
	public Map<Integer, String> getAllUserPhotoNew(Integer userId) {
		Map<Integer,String> arrPath = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		//userId = 1; закомитил
		Query query = session.createQuery("FROM User Where userId = :userId");
		query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		Set<Photo> photoes = users.get(0).getPhotoes();
		//Add photoes path of userID
		for(Photo p:photoes){
			arrPath.put(p.getImageId(),p.getTitle());
		}
		session.close();
		return arrPath;

	}
	
	/************/
	
	
	//For moderator
	public Map<Integer,String> getAllUsersPhoto() {
		Map<Integer,String> arrPath = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where confirm != 1");
		List<Photo> photoes = (List<Photo>) query.list();
		//Add photoes path of userID
		for(Photo p:photoes){
			arrPath.put(p.getImageId(), p.getPath());
		}
		session.close();
		return arrPath;
		
	}

	public void addPhoto(Integer userId, String path, String folder, String title) {
		//path = "C:/a.jpg";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM User Where userId = :userId");
		query.setInteger("userId", userId);
		List<User> users = (List<User>) query.list();
		Photo photo = new Photo();
		photo.setPath(path);
		photo.setTitle(title);
		photo.setFolder(folder);
		photo.setDownloadDate(getZeroTimeDate(new Date()));
		photo.setConfirm(false);
		// Set owner of user
		photo.setUser(users.get(0));

		session.beginTransaction();
		session.save(photo);
		session.getTransaction().commit();
		
		session.close();

	}
	///UTIL FOR DATE
	/***********************/
	public static Date getZeroTimeDate(Date fecha) {
	    Date res = fecha;
	    Calendar calendar = Calendar.getInstance();

	    calendar.setTime( fecha );
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    res = calendar.getTime();

		return res;
	}

	/*********************/

	public List<Integer> makeSearch(String searchKeyword) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Photo Where title like :title AND folder != 'Personal'");
		query.setParameter("title", "%" + searchKeyword + "%");
		List<Photo> users = (List<Photo>) query.list();
		List<Integer> pathes = new ArrayList<>();
		for (Photo p : users) {
			// pathes.add(p.getPath());
			pathes.add(p.getImageId());
		}
		session.close();
		// System.out.println(users);
		return pathes;

	}

	public void deletePhoto(Integer imageId) {
		// imageId = 42;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
		List<Photo> photoes = query.list();
		System.out.println(photoes.get(0));
		session.beginTransaction();
		session.delete(photoes.get(0));
		session.getTransaction().commit();
		session.close();
		// Вычисляем пользователя, у которого будем удвлять фото
		// photoes.get(0).getUser().getUserId();
		// In future
	}

	public void updatePhoto(Photo photoId) {
		// In future
	}

	// For moderator
	public Map<Integer, String> selectByDate(String dateStr) {
		Map<Integer, String> arrPath = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date searchDate = null;
		try {
			searchDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Photo Where downloadDate=:downloadDate");
		query.setDate("downloadDate", searchDate);
		List<Photo> photoes = query.list();

		for (Photo p : photoes) {

			arrPath.put(p.getImageId(), p.getPath());
		}
		session.close();
		return arrPath;

	}

	// For title change on user.html
	public void changeTitle(Integer imageId, String newTitle) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
		List<Photo> photoes = query.list();
		Photo photo = photoes.get(0);
		photo.setTitle(newTitle);
        photo.setConfirm(false);//set false. Moder should confirm
		session.beginTransaction();
		session.save(photo);
		session.getTransaction().commit();
		
		session.close();

	}

	// For user page. Get title of photo by imaheId.
	/*
	 * public String getPhotoTitle(Integer imageId){ String title = ""; Session
	 * session = HibernateUtil.getSessionFactory().openSession(); Query query =
	 * session.createQuery("FROM Photo Where imageId=:imageId");
	 * query.setInteger("imageId", imageId); List<Photo> photoes = query.list();
	 * System.out.println(photoes.get(0)); title = photoes.get(0).getTitle();
	 * return title; }
	 */

	public String getPhotoTitle(String path) {
		String title = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where path=:path");
		query.setString("path", path);
		List<Photo> photoes = query.list();
		System.out.println(photoes.get(0));
		title = photoes.get(0).getTitle();
		session.close();
		return title;
	}
	
	public String getPhotoTitleByImageId(Integer imageId) {
		String title = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
		List<Photo> photoes = query.list();
		System.out.println(photoes.get(0));
		title = photoes.get(0).getTitle();
		session.close();
		return title;
	}

	// User page. Get all photo by folder and userId
	public Map<Integer, String> getPhotoByFolder(Integer userId, String folder) {
		Map<Integer, String> arrPath = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Photo Where userId=:userId AND folder=" + "'" + folder + "'");
		query.setInteger("userId", userId);
	//	query.setString("folder", folder);
		List<Photo> photoes = query.list();
		for (Photo p : photoes) {

			arrPath.put(p.getImageId(), p.getTitle());
		}
		session.close();
		return arrPath;
		// TODO Auto-generated method stub

	}
	//Return image path. Gor image changing
	public String getImagePathByImageId(Integer imageId){
		String path = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
	//	query.setString("folder", folder);
		List<Photo> photoes = query.list();
		Photo photo = photoes.get(0);
		photo.setConfirm(false);//Moderator should confirm changing of image
		session.beginTransaction();
		session.save(photo);
		session.getTransaction().commit();
		
		session.close();
		return photoes.get(0).getPath();
	}
	
	//For moderator. Confirm Photo
	public void confirmImage(Integer imageId){
		String path = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
	//	query.setString("folder", folder);
		List<Photo> photoes = query.list();
		Photo photo = photoes.get(0);
		photo.setConfirm(true);
		
		session.beginTransaction();
		session.save(photo);
		session.getTransaction().commit();
		
		session.close();
	}

}
