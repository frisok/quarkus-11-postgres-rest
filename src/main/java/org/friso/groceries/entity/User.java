package org.friso.groceries.entity;

import javax.persistence.*;

@Entity(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_generator")
    private Long id;

    @Column(name = "user_name", columnDefinition = "varchar(255)", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(255)", nullable = false)
    private String password;

    @Column(name = "active", columnDefinition = "smallint default 1", nullable = false)
    private boolean active = true;

    @Column(name = "login_attempts", columnDefinition = "integer default 0", nullable = false)
    private int loginAttempts = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

}
