package com.exam.onlineexamsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.onlineexamsystem.model.studentModel;

@Repository
public interface studentRepository extends JpaRepository<studentModel, String> {


}

