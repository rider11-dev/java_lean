package hello.model;

import java.util.Date;

public class User {
    private String UserId;
    private String UserName;
    private String Password;
    private Date Birth;
    private Double Height;

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double height) {
        Height = height;
    }

    public Date getBirth() {
        return Birth;
    }

    public void setBirth(Date birth) {
        Birth = birth;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{UserId='" + UserId + "',UserName='" + UserName + "',Password='" + Password + "',Birth='"
                + Birth + "',Height=" + Height + "}";
    }
}
