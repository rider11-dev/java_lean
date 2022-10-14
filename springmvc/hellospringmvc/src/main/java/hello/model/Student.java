package hello.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Student {
    // 学号
    private String stuId;
    // 姓名
    private String stuName;
    // 年龄
    private Integer age;
    // 用于接收后台上传的文件
    private List<MultipartFile> photos;
    // 文件名称的字符串
    private String fileNameStr;
    // 已上传图片的路径集合
    private List<String> path;

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public void setPhotos(List<MultipartFile> photos) {
        this.photos = photos;
    }

    public String getFileNameStr() {
        return fileNameStr;
    }

    public void setFileNameStr(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<MultipartFile> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", age=" + age +
                ", photos=" + photos +
                ", photoPath='" + fileNameStr + '\'' +
                ", path=" + path +
                '}';
    }
}
