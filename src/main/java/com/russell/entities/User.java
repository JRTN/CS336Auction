package com.russell.entities;

public class User {

    private String username;
    private String userpass;
    private String useremail;
    private String irlname;
    private String userrole;

    public User(String username, String userpass, String useremail, String irlname, String userrole) {
        this.username = username;
        this.userpass = userpass;
        this.useremail = useremail;
        this.irlname = irlname;
        this.userrole = userrole;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpass() {
        return userpass;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getIrlname() {
        return irlname;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setIrlname(String irlname) {
        this.irlname = irlname;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
}
