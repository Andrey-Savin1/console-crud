package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.andrey.crud.utils.Util;
import ru.andrey.crud.model.Writer;
import ru.andrey.crud.repository.PostRepository;
import ru.andrey.crud.repository.WriterRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GsonWriterRepositoryImpl implements WriterRepository {


    private final Util<Writer> util = new Util<>();
    private final Gson gson = new Gson();
    private final PostRepository postRepository = new GsonPostRepositoryImpl();


    private long writerIdCounter = 1;
    private final String PATH_WRITERS = "src/main/resources/writers.json";


    @Override
    public Writer create(Writer writer) {

        var listWriters = getAllInternal();
        writer.setId(writerIdCounter);
        writerIdCounter++;

        listWriters.add(writer);
        util.writeToFile(listWriters, PATH_WRITERS);

        return writer;
    }

    @Override
    public Writer getById(Long id) {

        return getAllInternal().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return getAllInternal();
    }

    @Override
    public void deleteById(Long id) {

        var listWriters = getAllInternal();
        listWriters.removeIf(writer -> id.equals(writer.getId()));
        util.writeToFile(listWriters, PATH_WRITERS);

    }

    @Override
    public Writer update(Writer writer) {
        var listWriters = getAllInternal();

        var result = listWriters.stream().map(t -> {
            if (t.getId().equals(writer.getId())) {
                t.setFirstName(writer.getFirstName());
                t.setLastName(writer.getLastName());
                t.setPosts(writer.getPosts());

                for (var post : writer.getPosts()) {
                    postRepository.update(post);
                    postRepository.create(post);
                }
                return writer;
            } else return t;
        }).toList();

        util.writeToFile(result, PATH_WRITERS);
        return writer;
    }

    public List<Writer> getAllInternal() {
        try (Scanner scanner = new Scanner(new FileReader(PATH_WRITERS))) {

            if (scanner.hasNextLine()) {
                Type listType = new TypeToken<List<Writer>>() {}.getType();
                return gson.fromJson(scanner.next(), listType);
            }
            return new ArrayList<>();
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }
}



