package gamesmanager.db;

import gamesmanager.beans.Member;

public class DatabaseOperations {

	public static boolean login(String username, String password) {
		boolean authorized = true;
		System.out.println("authorizing");
		return authorized;
	}

	public static Member findMember(String memberid) {
		return null;
	}

	public static boolean connect() {
		boolean connected = false;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver class:");
			cnfe.printStackTrace();
		}
		return connected;
	}
}
