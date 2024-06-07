package ru.andrey.crud.controller;

import ru.andrey.crud.model.Post;
import ru.andrey.crud.model.Status;
import ru.andrey.crud.model.Writer;
import ru.andrey.crud.repository.WriterRepository;
import ru.andrey.crud.repository.repositoryImpl.GsonWriterRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class WriterController {

    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl();

    public Writer createWriter(String firstName, String lastName, List<Post> posts) {

        Writer writer = new Writer();

        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);
        writer.setStatus(Status.ACTIVE);

        return writerRepository.create(writer);
    }

    public Writer updateWriter(Long writerId, String firstName, String lastName, List<Post> posts) {

        Writer writer = new Writer();

        writer.setId(writerId);
        writer.setStatus(Status.ACTIVE);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);

        return writerRepository.update(writer);
    }

    public Writer getWriterById(Long id) {
        return writerRepository.getById(id);
    }

    public void deleteWriter(Long id) {
        writerRepository.deleteById(id);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer addPost(Long id, String title, String content) {

        Writer writer = writerRepository.getById(id);
        Post post = new Post();

        if (post.getLabels() == null) {
            post.setLabels(new ArrayList<>());
        }
        post.setTitle(title);
        post.setStatus(Status.ACTIVE);
        post.setContent(content);
        writer.getPosts().add(post);

        return writerRepository.update(writer);
    }
}
