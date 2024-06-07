package ru.andrey.crud.controller;

import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.model.Status;
import ru.andrey.crud.repository.PostRepository;
import ru.andrey.crud.repository.repositoryImpl.GsonPostRepositoryImpl;

import java.util.List;

public class PostController {

    private final PostRepository postRepository = new GsonPostRepositoryImpl();

    public Post createPost(String title, String content, List<Label> labels) {

        Post post = new Post();

        post.setTitle(title);
        post.setContent(content);
        post.setLabels(labels);
        post.setStatus(Status.ACTIVE);

        return postRepository.create(post);
    }

    public Post updatePost(Long postId, String title, String content, List<Label> labels) {
        Post post = new Post();

        post.setId(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setLabels(labels);
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

}
