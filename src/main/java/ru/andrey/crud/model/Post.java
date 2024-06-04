package ru.andrey.crud.model;

import java.util.List;
import java.util.Objects;

public class Post {

    private Long id;
    private String title;
    private String content;
    private List<Label> labels;
    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && Objects.equals(labels, post.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, labels);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }
}
