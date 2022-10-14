package annotaionconfig.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class User {
    private String userName;
    private Date birth;
    private Double height;
    private List<MultipartFile> photos;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", birth=" + birth +
                ", height=" + height +
                ", photos=" + photos +
                '}';
    }
}
