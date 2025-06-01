package com.exam.onlineexamsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.onlineexamsystem.model.teacherModel;

@Repository
public interface teacherRepository extends JpaRepository<teacherModel, String> {
}
