package com.amr.project.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnswerFromModerator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer_from_moderator;

    public AnswerFromModerator() {
    }

    public AnswerFromModerator(String answer_from_moderator) {
        this.answer_from_moderator = answer_from_moderator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer_from_moderator() {
        return answer_from_moderator;
    }

    public void setAnswer_from_moderator(String answer_from_moderator) {
        this.answer_from_moderator = answer_from_moderator;
    }
}
