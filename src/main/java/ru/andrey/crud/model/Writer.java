package ru.andrey.crud.model;

import java.util.List;
import java.util.Objects;

public class Writer {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
        Writer writer = (Writer) o;
        return id.equals(writer.id) && Objects.equals(firstName, writer.firstName) && Objects.equals(lastName, writer.lastName) && Objects.equals(posts, writer.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, posts);
    }

    @Override
    public String toString() {
        return "Writer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", posts=" + posts + ", status=" + status + '}';
    }
}
