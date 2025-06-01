package com.exam.onlineexamsystem.dto;

import java.util.Set;
import com.exam.onlineexamsystem.model.exam.Quiz;

public class CategoryDto {
    private long cid;
    private String title;
    private String description;
    private Set<Quiz> quizzes; // Optional: Remove if you don't need quizzes in the DTO

    public CategoryDto() {
    }

    public CategoryDto(long cid, String title, String description, Set<Quiz> quizzes) {
        this.cid = cid;
        this.title = title;
        this.description = description;
        this.quizzes = quizzes;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
