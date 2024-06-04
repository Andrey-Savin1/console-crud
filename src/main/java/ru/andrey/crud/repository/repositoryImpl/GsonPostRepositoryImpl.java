package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.repository.LabelRepository;
import ru.andrey.crud.repository.PostRepository;
import ru.andrey.crud.utils.Util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {

    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();
    private final Util<Post> util = new Util<>();
    private final Gson gson = new Gson();

    private final String PATH = "src/main/resources/posts.json";
    private long postIdCounter = 1;


    @Override
    public Post create(Post post) {
        List<Post> listPosts = getAllInternal();
        post.setId(postIdCounter);
        postIdCounter++;

        listPosts.add(post);
        util.writeToFile(listPosts, PATH);

        return post;
    }

    @Override
    public Post getById(Long id) {
        return getAllInternal().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return getAllInternal();
    }

    @Override
    public void deleteById(Long id) {
        List<Post> listPosts = getAllInternal();

        listPosts.removeIf(label -> id.equals(label.getId()));
        util.writeToFile(listPosts, PATH);
    }

    @Override
    public Post update(Post post) {
        List<Post> listPosts = getAllInternal();

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

        util.writeToFile(result, PATH);
        return post;
    }

    private List<Post> getAllInternal() {

        try (Reader reader = new FileReader(PATH)) {
            Type listType = new TypeToken<ArrayList<Label>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
