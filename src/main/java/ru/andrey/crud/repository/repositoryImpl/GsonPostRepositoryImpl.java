package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.repository.LabelRepository;
import ru.andrey.crud.repository.PostRepository;
import ru.andrey.crud.utils.Util;

import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {

    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();
    private final Util<Post> util = new Util<>(Post.class);

    private final String PATH = "src/main/resources/posts.json";
    private long postIdCounter = 1;


    @Override
    public Post create(Post post) {
        List<Post> listPosts = util.getAllInternal(PATH);
        post.setId(postIdCounter);
        postIdCounter++;

        listPosts.add(post);
        Util.writeToFile(listPosts, PATH);

        return post;
    }

    @Override
    public Post getById(Long id) {
        List<Post> listPosts = util.getAllInternal(PATH);
        return listPosts.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return util.getAllInternal(PATH);
    }

    @Override
    public void deleteById(Long id) {
        List<Post> listPosts = util.getAllInternal(PATH);

        listPosts.removeIf(label -> id.equals(label.getId()));
        Util.writeToFile(listPosts, PATH);
    }

    @Override
    public Post update(Post post) {
        List<Post> listPosts = util.getAllInternal(PATH);

        var result = listPosts.stream().map(t -> {
            if (t.getId().equals(post.getId())) {
                t.setContent(post.getContent());
                t.setTitle(post.getTitle());
                t.setLabels(post.getLabels());
                t.setStatus(post.getStatus());

                for (var label : post.getLabels()) {
                    labelRepository.update(label);
                    labelRepository.create(label);
                }
                return post;
            }  else return t;
        }).toList();

        Util.writeToFile(result, PATH);
        return post;
    }
}
