package net.vortex.springrestapi.api.model;

import java.time.OffsetDateTime;

public class CommentModel {

    private Long id;
    private String description;
    private OffsetDateTime commentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(OffsetDateTime commentDate) {
        this.commentDate = commentDate;
    }
}
