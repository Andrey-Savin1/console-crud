package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.andrey.crud.Utils.Util;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.repository.LabelRepository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final Util<Label> util = new Util<>();
    private final Gson gson = new Gson();

    private final String PATH = "src/main/resources/labels.json";
    private long labelIdCounter = 1;


    @Override
    public Label create(Label label) {
        List<Label> listLabels = getAllInternal();
        label.setId(labelIdCounter);
        labelIdCounter++;

        listLabels.add(label);
        util.writeToFile(listLabels, PATH);
        return label;
    }

    @Override
    public Label getById(Long id) {

        List<Label> listLabels = getAllInternal();

        return listLabels.stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {

        List<Label> listLabels = getAllInternal();
        listLabels.removeIf(label -> id.equals(label.getId()));
        util.writeToFile(listLabels, PATH);
    }

    @Override
    public Label update(Label label) {

        List<Label> listLabels = getAllInternal();
        var result = listLabels.stream()
                .map(t -> {
                    if (t.getId().equals(label.getId())) {
                        return label;
                    } else return t;
                }).toList();

        util.writeToFile(result, PATH);
        return label;
    }

    @Override
    public List<Label> getAll() {
        return getAllInternal();
    }

    private List<Label> getAllInternal() {

        try (Reader reader = new FileReader(PATH)) {
            Type listType = new TypeToken<ArrayList<Label>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
