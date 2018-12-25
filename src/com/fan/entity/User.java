package com.fan.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer is_role;
    private Integer flag;

    public User() {
    }

    public User(String username, String password, String phone, String email, Integer is_role) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.is_role = is_role;
    }

    public User(Integer id, String username, String password, String phone, String email, Integer is_role, Integer flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.is_role = is_role;
        this.flag = flag;
    }
    public User(Integer id, String username, String password, String phone, String email, Integer is_role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.is_role = is_role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIs_role() {
        return is_role;
    }

    public void setIs_role(Integer is_role) {
        this.is_role = is_role;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", is_role=" + is_role +
                ", flag=" + flag +
                '}';
    }
}
