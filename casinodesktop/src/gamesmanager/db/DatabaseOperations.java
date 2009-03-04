package gamesmanager.db;

import gamesmanager.beans.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {

	public static boolean login(String username, String password) {
		boolean authorized = true;
		System.out.println("authorizing");
		return authorized;
	}

	public static Client findMember(String memberid) {
		return null;
	}
}
