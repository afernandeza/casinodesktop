package gamesmanager.beans;

public class User {

    private int userid;
    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(int userid, String username, String password) {
        super();
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        final String TAB = ", ";

        StringBuffer retValue = new StringBuffer();

        retValue.append("User ( ").append(super.toString()).append(TAB).append(
                "userid = ").append(this.userid).append(TAB).append(
                "username = ").append(this.username).append(TAB).append(
                "password = ").append(this.password).append(TAB).append(" )");

        return retValue.toString();
    }

}
