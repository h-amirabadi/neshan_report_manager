package com.neshan.DTO;

import com.neshan.model.Account;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

public class ReportDTO implements Serializable {
    private Long id;
    private short type;
    private String x;
    private String y;
    private Timestamp createdAt;
    private Timestamp activeUntil;
    private String accountName;
    private boolean likedByCurrentUser;
    private boolean dislikedByCurrentUser;
    private Integer likes;
    private Integer dislikes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Timestamp activeUntil) {
        this.activeUntil = activeUntil;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }

    public boolean isDislikedByCurrentUser() {
        return dislikedByCurrentUser;
    }

    public void setDislikedByCurrentUser(boolean dislikedByCurrentUser) {
        this.dislikedByCurrentUser = dislikedByCurrentUser;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }
}
