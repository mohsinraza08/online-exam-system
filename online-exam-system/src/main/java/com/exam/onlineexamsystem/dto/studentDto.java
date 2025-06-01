package com.exam.onlineexamsystem.dto;


public class studentDto {
    private String name;
    private String rollno;
    private String password;
    private String role;  // Can be "student" or "teacher"

    // Constructors
    public studentDto() {}

    public studentDto(String name, String rollno, String password, String role) {
        this.name = name;
        this.rollno = rollno;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
