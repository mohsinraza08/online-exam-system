package com.exam.onlineexamsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="student")
public class studentModel {
    @Id
    @Column(nullable=false, length=50)
    private String rollno;
    @Column(nullable=false, length=50)
    private String name;
    @Column(nullable=false, length=50)
    private String password; 

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
