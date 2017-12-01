package com.example.microservice.service.post;

public class Post {

    private long id;
    private long accountId;
    private String description;

    public Post() {
    }

    public Post(long id, long accountId, String description) {
        this.id = id;
        this.accountId = accountId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", description='" + description + '\'' +
                '}';
    }
}
