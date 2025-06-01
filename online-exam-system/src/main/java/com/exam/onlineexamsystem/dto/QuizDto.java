package com.exam.onlineexamsystem.dto;

import com.exam.onlineexamsystem.model.exam.Category;

public class QuizDto {
    private long qid;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestion;
    private boolean active;
    private Category category;

    public QuizDto() {
    }

    public QuizDto(long qid, String title, String description, String maxMarks, String numberOfQuestion, boolean active, Category category) {
        this.qid = qid;
        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        this.numberOfQuestion = numberOfQuestion;
        this.active = active;
        this.category = category;
    }

    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
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

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(String numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

