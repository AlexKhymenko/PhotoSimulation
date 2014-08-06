package com.photobank.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;

import com.photobank.db.dao.PhotoDao;
import com.photobank.db.dao.UserDao;
import com.photobank.db.dao.UserSessionDao;
import com.photobank.db.entities.Photo;
import com.photobank.db.entities.User;
import com.photobank.db.entities.UserSession;
import com.photobank.db.util.HibernateUtil;
import com.photobank.image.ChangeImage;
import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/jsonServices")
public class JerseyRestService {
	private Logger logger = Logger.getLogger(com.photobank.ws.JerseyRestService.class);
	//����������
	@GET
	@Path("/logout")
	//@Produces("text/html")
	public Response getIndex(@CookieParam(value = "name") String name) {
		logger.fatal("Pressed LogOut BUtton");
	    System.out.println("cookie  Name:  " + name );
	    //return Response.ok(new Viewable("/index", model)).build();
	    Cookie c = new Cookie("name", "");
	    NewCookie nc = new NewCookie(c, "", 0, false);
	    
	    //Delete session from DB
	    UserSessionDao dao = new UserSessionDao();
	    System.out.println(dao.getUserForSession(name));
	    dao.deleteUserSession(dao.getUserForSession(name), name);
	    
	    
	    
	  return Response.status(200).cookie(nc).build();
	}
	//����� ����������� ��� ��������� �������� ��������
	@GET
	//@Produces("text/html")
	public Response getsession(@CookieParam(value = "name") String name) {
	    System.out.println("cookie  Name:  " + name );
/*	    String session = null;
	    if (name == null)
	     name = "no session";*/
	    
	    //return Response.ok(new Viewable("/index", model)).build();
	 //   Cookie c = new Cookie("name", "");
	  return Response.status(200).entity(name).build();
	}

/*	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON( @PathParam("name") String name ) {
		System.out.println("Hello!");
		Student st = new Student(name, "Diaz",22,1);

		return st;

	}*/
	//�������� �����. �� ������������ � ���������
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON( JSONObject student ) throws JSONException{
		logger.fatal("Pressed Send BUtton");
		System.out.println(student.getString("email"));
		System.out.println("----");
		System.out.println(student.getString("pass"));
		System.out.println("POST!"); 
		//String output = student.toString();

		return Response.status(200).entity("").build();
	}
	//������ ��� ����� �� ���������
	@POST
	@Path("/login")	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logUser(JSONObject user)throws JSONException{
		logger.fatal("Pressed Login BUtton");
		String email = user.getString("email");
		String pass = user.getString("pass");
		//Generate sessionID
		String sessionID = UUID.randomUUID().toString();
		NewCookie cookies = new NewCookie("name", sessionID);
		System.out.println();
		System.out.println(cookies.getValue());
		UserDao userDao = new UserDao();
		String result;
		if(!userDao.checkPass(email, pass).equals("Error"))	{	
				result = "Hello: "+ email;
				//get user my login
				userDao.selectById(email);
				//set user session(to DB)
				UserSessionDao userSessionDao = new UserSessionDao();
				userSessionDao.setUserSession(userDao.selectById(email), sessionID);
				return Response.status(200).cookie(new NewCookie("name", sessionID)).entity(result).build();
		}
					else{
						result = "Error"; 
						return Response.status(400).entity(result).build();
						
					}
			
		
		//return Response.status(200).cookie(new NewCookie("name", sessionID)).entity(result).build();
		//Add cookie
		
	}
	//������ ��� ����������� ������ ������������
	@POST
	@Path("/regUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response regUser(JSONObject user) throws JSONException{
		logger.fatal("Pressed Register User BUtton");
		String email = user.getString("email");
		String pass = user.getString("pass");		
		UserDao userDao = new UserDao();
		String result;
		if(!userDao.addUser(email, pass).equals("User name exists!")){	
			//Send email
		result = "Hello: "+ email;	
		}
		else
			result = "Error";	
		return Response.status(200).entity(result).build();
	}
	
	
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "D://Users/"; 

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	//public Response uploadFile(
			public void uploadFile(
					
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
			@CookieParam(value = "name") String name,  
			@FormDataParam("folder") String folder,
			@FormDataParam("title") String title) { 
		logger.fatal("Pressed  upload file Button");
		System.out.println("Folder:" + folder);
		System.out.println("Phoro name:" + title);
		//DAO Session get USer by Id
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ contentDispositionHeader.getFileName();
		
		UserSessionDao session = new UserSessionDao();
		System.out.println(session.getUserForSession(name)); 
		 PhotoDao photo = new PhotoDao();
		 //Change Path
	// photo.addPhoto(session.getUserForSession(name), filePath);
	//	 photo.addPhoto(session.getUserForSession(name), "pics/" + contentDispositionHeader.getFileName());
		 photo.addPhoto(session.getUserForSession(name), contentDispositionHeader.getFileName(), folder, title);	 
		System.out.println("cookie  Name:  " + name + "Work");
		
System.out.println(contentDispositionHeader.getFileName());
		// save the file to the server
		saveFile(fileInputStream, filePath);

		String output = "File saved to server location : " + filePath;

		//return Response.status(200).entity(output).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}	
	//������ ��� ������ ���� ���������������� ����. ������������ ������. ����� � ������������ ����� ������������
	@GET
	@Path("/allUserPhoto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUserPhoto(@CookieParam(value = "name") String name) throws JSONException{
		logger.fatal("Pressed All user BUtton");
		
		UserSessionDao session = new UserSessionDao();
		Integer userId = session.getUserForSession(name);
		PhotoDao dao = new PhotoDao();
		//Map<Integer, String> photo = dao.
		Map<Integer, String> photoes = dao.getAllUserPhotoNew(userId);
		Set<String> pathes = null;
		try{
		pathes = dao.getAllUserPhoto(userId);
		}catch(Exception e){
			System.out.println("EXCEPTION");
		}
     // return Response.status(200).entity(pathes).build();
		  return Response.status(200).entity(photoes).build();

	}
	
	//������������ ��� ������������ �������� ����� ������ ���� ��� ����. ������������ � �� ������ ��� ���������
/*	@GET
	 @Path("/images/{image}")
	 @Produces("image/*")
	 public Response getImage(@PathParam("image") String image) {
		System.out.println("Hello!" + image);
		//D://Current/Bank1/WebContent/pics/krasivaya-devushka-risunok-2.jpg
	     File f = new File("D://Users/" + image);
		// File f = new File(image);

	     if (!f.exists()) {
	         throw new WebApplicationException(404);
	     }

	     String mt = new MimetypesFileTypeMap().getContentType(f);
	     return Response.ok(f, mt).build();
	 }*/
	
	
	@GET
	 @Path("/images/{image}")
	 @Produces("image/*")
	 public Response getImage(@PathParam("image") Integer imageId) {
		logger.fatal("Pressed Image ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Photo Where imageId=:imageId");
		query.setInteger("imageId", imageId);
		List<Photo> photoes = query.list();
		
		System.out.println("Hello!" + photoes.get(0).getPath());
		//D://Current/Bank1/WebContent/pics/krasivaya-devushka-risunok-2.jpg
	     File f = new File("D://Users/" + photoes.get(0).getPath());
		// File f = new File(image);

	     if (!f.exists()) {
	         throw new WebApplicationException(404);
	     }

	     String mt = new MimetypesFileTypeMap().getContentType(f);
	     return Response.ok(f, mt).build();
	 }
	
	

	
	
	
	//Search by title. ���������. �� ��� ��������� ������������
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeSearch(@QueryParam("key") String key){
		logger.fatal("Pressed  Search BUtton");
		List<Integer> pathes = new PhotoDao().makeSearch(key);		
		return Response.status(200).entity(pathes).build();		
	}
	
//��� ��������� ����������	
	@GET
	@Path("/allUserPhotoForModerator")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPhotoForModerator() throws JSONException{
		logger.fatal("Pressed all user photo BUtton");
		UserSessionDao session = new UserSessionDao();
		
		PhotoDao dao = new PhotoDao();
		Map<Integer,String> pathes = null;
		try{
		pathes = dao.getAllUsersPhoto();
		}catch(Exception e){
			System.out.println("EXCEPTION");
		}
      return Response.status(200).entity(pathes).build();

	}

	//�������� ����. �� ��� ����������
	@GET
	 @Path("/delete/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletePhoto(@PathParam("imageId") Integer imageId) {
		logger.fatal("Pressed delete BUtton");
		System.out.println("Hello!");
		//����� ����� �������
		//Integer imageId = 42;
		
		new PhotoDao().deletePhoto(imageId);
		//System.out.println("delete" + image);
	}
	//���������� ���� �� ����. �� ��� ����������
	@GET
	@Path("/photoByDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response photoByDate(@PathParam("date") String dateStr) throws JSONException{
		logger.fatal("Pressed PhotoByDATE ");
		//String dateStr = date.getString("Text_form");
		System.out.println("DAte: " + dateStr);		
	
		PhotoDao dao = new PhotoDao();
		
	Map<Integer,String> pathes = new HashMap<>();
		try{
		pathes = dao.selectByDate(dateStr);
		}catch(Exception e){
			System.out.println("EXCEPTION");
		}
      return Response.status(200).entity(pathes).build();

	}
	

//��� ��������� ������������	
	// ����� �������� �������� �� �� ��������, ������� �������� �� ������������
/*	@GET
	@Path("/photoTitle/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPhotoTitle(@PathParam("imageId") Integer imageId) {
		System.out.println("Hello from title!");
		//����� ����� �������
		//Integer imageId = 42;
		String title = new PhotoDao().getPhotoTitle(imageId);
		return Response.status(200).entity(title).build();
		//System.out.println("delete" + image);
	}*/
	
	@GET
	@Path("/photoTitle/{path}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPhotoTitle(@PathParam("path") String path) {
		System.out.println("Hello from title!");
		//����� ����� �������
		//Integer imageId = 42;
		String title = new PhotoDao().getPhotoTitle(path);
		return Response.status(200).entity(title).build();
		//System.out.println("delete" + image);
	}

	
	//�� ��� ���������  ������������ 
	//Search by title. ���������. �� ��� ��������� ������������
	@GET
	@Path("/changeTitle")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeTitle(@QueryParam("imageId") Integer imageId, @QueryParam("newTitle") String newTitle){
		//List<Integer> pathes = new PhotoDao().makeSearch(key);
		logger.fatal("Pressed change title Button");
		System.out.println("Hello" + newTitle );
		new PhotoDao().changeTitle(imageId, newTitle);
		Object pathes = null;
		return Response.status(200).entity(pathes).build();		
	}

	//Fro user page. Get user photo by folder name
	@GET
	@Path("/getPhotoByFolder")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPhotoByFolder(@CookieParam(value = "name") String name, @QueryParam("folder") String folder){
		logger.fatal("Pressed get photo by folder");
		Integer userId = new UserSessionDao().getUserForSession(name);
		Map<Integer,String> pathes = new PhotoDao().getPhotoByFolder(userId, folder);
		return Response.status(200).entity(pathes).build();		
	}	
	
//Activation of account
	@GET
	@Path("/activateAccount")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAccount( @QueryParam(value = "secretKey") String secretKey){
		logger.fatal("Activate account");
		UserDao ud = new UserDao();
		Integer userId = ud.getUserBySecretKey(secretKey);
		if (userId!=null){
		ud.activateUser(userId);	
			 //activate user by userId		
		}
		//Integer userId = new UserSessionDao().getUserForSession(name);
	//	Map<Integer,String> pathes = new PhotoDao().getPhotoByFolder(userId, folder);
		return Response.status(200).entity("Good boy!").build();		
	}
	
	//For user page. Get title of photo by imageiD
	@GET
	@Path("/photoTitleByImageId/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPhotoByImageId(@PathParam("imageId") Integer imageId) {
		logger.fatal("photoTitlebyImageID");
		System.out.println("Hello from title!");
		//����� ����� �������
		//Integer imageId = 42;
		String title = new PhotoDao().getPhotoTitleByImageId(imageId);
		return Response.status(200).entity(title).build();
		//System.out.println("delete" + image);
	}
	
	//Chage photo Sepia/Black&White. For User.html
	@GET
	@Path("/imagetoSepia")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response imagetoSepia(@QueryParam(value = "imageId") Integer imageId, @QueryParam(value = "sepiaIntensity") Integer sepiaIntensity ) {
		//Integer sepiaIntensity = 100;
		logger.fatal("Pressed imageToSepia");
		System.out.println("Hello from imagetoSepia!");
		String path = new PhotoDao().getImagePathByImageId(imageId);
		try {
			ChangeImage.toSepia(path, sepiaIntensity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity("").build();
		//System.out.println("delete" + image);
	}
	
	@GET
	@Path("/imagetoBlack/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void imagetoBlack(@PathParam("imageId") Integer imageId) {
		logger.fatal("Image to black");
		System.out.println("Hello from imagetoBlack!");
		//����� ����� �������
		//Integer imageId = 42;
		String path = new PhotoDao().getImagePathByImageId(imageId);
		try {
			ChangeImage.toBlackAndWhiteImg(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	return Response.status(200).entity(path).build();
		//System.out.println("delete" + image);
	}
	
	//For moder confirm
	@GET
	@Path("/moderConfirm/{imageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void confirmImage(@PathParam("imageId") Integer imageId) {
		logger.fatal("Pressed moderConfirm");
		System.out.println("Hello from confirm!");

		//confirmImage
		 new PhotoDao().confirmImage(imageId);
		
		//return Response.status(200).entity("").build();

	}
	
	//Get all users. For moderator page.
	@GET
	@Path("/getAllUsers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		logger.fatal("Pressed get all user BUtton");
		//Integer sepiaIntensity = 100;
		System.out.println("Hello from getAllUsers!");
		//key - userId, value - name
		Map<Integer, String> users = new UserDao().getAllUsers();
		return Response.status(200).entity(users).build();
		//System.out.println("delete" + image);
	}
	
	//Lock user. Moder page
	@GET
	@Path("/lockUser/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void lockUser(@PathParam("userId") Integer userId) {
		logger.fatal("Pressed  lock user BUtton");
		System.out.println("Hello from lockUser!");
//LockUser(userId)
		new UserDao().lockUser(userId);
		//confirmImage
		// new PhotoDao().confirmImage(imageId);
		
		//return Response.status(200).entity("").build();

	}
	//UnlockUser. Moder page.
	
	@GET
	@Path("/unLockUser/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void unLockUser(@PathParam("userId") Integer userId) {
		logger.fatal("Pressed unlockUser BUtton");
		System.out.println("Hello from lockUser!");
		new UserDao().unlockUser(userId);
          //UnLockUser(userId)
		//confirmImage
		// new PhotoDao().confirmImage(imageId);
		
		//return Response.status(200).entity("").build();

	}
	
	
	/*	//login
	@POST
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")
	public String post(@FormParam("email") String email, @FormParam("pass") String pass) {
		UserDao userDao = new UserDao();
		String result;
		if(!userDao.checkPass(email, pass).equals("Error"))		
				result = "Hello: "+ email;
					else
			result = "Error";
		return result;
		}*/
	//Registration
	/*@Path("/regUser")
	@POST
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")
	public String regUser(@FormParam("email") String email, @FormParam("pass") String pass) {
		UserDao userDao = new UserDao();
		String result;
		if(!userDao.addUser(email, pass).equals("User name exists!"))			
		result = "Hello: "+ email;		
		else
			result = "Error";		
		return result;
		}*/


}
