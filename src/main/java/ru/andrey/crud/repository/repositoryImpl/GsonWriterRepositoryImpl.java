package ru.andrey.crud.repository.repositoryImpl;

import ru.andrey.crud.model.Writer;
import ru.andrey.crud.repository.PostRepository;
import ru.andrey.crud.repository.WriterRepository;
import ru.andrey.crud.utils.Util;

import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {


    private final Util<Writer> util = new Util<>(Writer.class);
    private final PostRepository postRepository = new GsonPostRepositoryImpl();

    private long writerIdCounter = 1;
    private final String PATH = "src/main/resources/writers.json";


    @Override
    public Writer create(Writer writer) {

        var listWriters = util.getAllInternal(PATH);
        writer.setId(writerIdCounter);
        writerIdCounter++;

        listWriters.add(writer);
        Util.writeToFile(listWriters, PATH);

        return writer;
    }

    @Override
    public Writer getById(Long id) {

        return util.getAllInternal(PATH).stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return util.getAllInternal(PATH);
    }

    @Override
    public void deleteById(Long id) {

        var listWriters = util.getAllInternal(PATH);
        listWriters.removeIf(writer -> id.equals(writer.getId()));
        Util.writeToFile(listWriters, PATH);

    }

    @Override
    public Writer update(Writer writer) {
        var listWriters = util.getAllInternal(PATH);

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

        Util.writeToFile(result, PATH);
        return writer;
    }
}




