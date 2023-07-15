package com.example.user.entity;


import jakarta.persistence.*;

@Entity
@Table(name="user_details")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "username")
    private String username;

    public Users(String email, String username, String password, boolean b) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Users(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", name='" + username + '\'' +
                ", emailAddress='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
