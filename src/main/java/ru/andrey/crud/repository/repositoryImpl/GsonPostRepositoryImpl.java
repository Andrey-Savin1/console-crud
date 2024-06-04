package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.andrey.crud.Utils.Util;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.repository.LabelRepository;
import ru.andrey.crud.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GsonPostRepositoryImpl implements PostRepository {

    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();
    private final Util<Post> util = new Util<>();
    private final Gson gson = new Gson();

    private final String path = "src/main/resources/posts.json";
    private long postIdCounter = 1;


    @Override
    public Post create(Post post) {
        List<Post> listPosts = getAllInternal();
        post.setId(postIdCounter);
        postIdCounter++;

        listPosts.add(post);
        util.writeToFile(listPosts, path);

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
        util.writeToFile(listPosts, path);
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

        util.writeToFile(result, path);
        return post;
    }

    public List<Post> getAllInternal() {
        try (Scanner scanner = new Scanner(new FileReader(path))) {

            if (scanner.hasNextLine()) {
                Type listType = new TypeToken<List<Post>>() {}.getType();
                return gson.fromJson(scanner.next(), listType);
            }
            return new ArrayList<>();
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
}
