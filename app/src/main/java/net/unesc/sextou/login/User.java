package net.unesc.sextou.login;


import net.unesc.sextou.database.SqlTable;

import java.util.Date;

public class User implements SqlTable {
    private String email;
    private String password;
    private String name;
    private Date birthday;

    public User() {
    }

    public User(String email, String password, String name, Date birthday) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
