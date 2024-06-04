package ru.andrey.crud.controller;

import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.model.Status;
import ru.andrey.crud.repository.repositoryImpl.GsonPostRepositoryImpl;
import ru.andrey.crud.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostController {

    private final PostRepository postRepository = new GsonPostRepositoryImpl();

    public Post createPost(String title, String content) {

        Post post = new Post();

        post.setTitle(title);
        post.setContent(content);
        post.setLabels(new ArrayList<>());
        post.setStatus(Status.ACTIVE);

        return postRepository.create(post);
    }

    public Post updatePost(Long postId, String title, String content) {
        Post post = new Post();

        post.setId(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setLabels(new ArrayList<>());
        post.setStatus(Status.ACTIVE);

        return postRepository.update(post);
    }

    public Post getPostById(Long id) {
        return postRepository.getById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post addLabel(Long id, String name) {

        Post post = postRepository.getById(id);

        Label label = new Label();

        label.setName(name);
        label.setStatus(Status.ACTIVE);
        if (post.getLabels() == null){
            post.setLabels(new ArrayList<>());
        }
        post.getLabels().add(label);

        return postRepository.update(post);

    }
}
