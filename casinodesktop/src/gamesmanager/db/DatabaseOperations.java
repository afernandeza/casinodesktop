package gamesmanager.db;

import gamesmanager.beans.User;

public class DatabaseOperations {

	private final static String AUTH = "? = call authenticate(?, ?)";
	
	public static boolean login(User u) {
		boolean authorized = true;
		System.out.println("authorizing");
		return authorized;
	}
	
}
