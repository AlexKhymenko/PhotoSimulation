package com.photobank.db.passcrypt;




public class Main {
	public static void main(String[] args){
	String password = "ulalalla";
	 
	String password1 = "ulalalla";
	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

	// gensalt's log_rounds parameter determines the complexity
	// the work factor is 2**log_rounds, and the default is 10
	//String hashed1 = BCrypt.hashpw(password1, BCrypt.gensalt(12));{

	
	if (BCrypt.checkpw(password1, hashed))
		System.out.println("It matches");
	else
		System.out.println("It does not match");
}}
