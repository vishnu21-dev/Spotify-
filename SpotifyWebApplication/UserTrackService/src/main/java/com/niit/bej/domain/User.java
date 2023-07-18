package com.niit.bej.domain;

import com.niit.bej.service.UserTrackServiceImpl;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class User  {

    @MongoId
    private String emailId;
    private String name;

    private String address;
    @Transient
    private String password;
    private List<PlayList> userPlayList;

    public User(String emailId, String name, String address, String password, List<PlayList> userPlayList) {
        this.emailId = emailId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.userPlayList = userPlayList;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", userPlayList=" + userPlayList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && Objects.equals(userPlayList, user.userPlayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, name, address, password, userPlayList);
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PlayList> getUserPlayList() {
        return userPlayList;
    }

    public void setUserPlayList(List<PlayList> userPlayList) {
        this.userPlayList = userPlayList;
    }
}
