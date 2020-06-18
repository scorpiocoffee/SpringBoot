package com.demo.ui8thymeleaf.bean;

public class Account {
    private String name;
    private String role;
    private String team;
    private String gender;
    private String tel;

    public Account(String name, String role, String team, String gender, String tel) {
        super();
        this.name = name;
        this.role = role;
        this.team = team;
        this.gender = gender;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
