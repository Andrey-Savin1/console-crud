package ru.andrey.crud.repository.repositoryImpl;

import com.google.gson.Gson;
import ru.andrey.crud.utils.Util;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.repository.LabelRepository;

import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final Util<Label> util = new Util<>(Label.class);

    private final String PATH = "src/main/resources/labels.json";
    private long labelIdCounter = 1;


    @Override
    public Label create(Label label) {
        List<Label> listLabels = util.getAllInternal(PATH);
        label.setId(labelIdCounter);
        labelIdCounter++;

        listLabels.add(label);
        Util.writeToFile(listLabels, PATH);
        return label;
    }

    @Override
    public Label getById(Long id) {

        List<Label> listLabels = util.getAllInternal(PATH);

        return listLabels.stream()
                .filter(f -> id.equals(f.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {

        List<Label> listLabels = util.getAllInternal(PATH);
        listLabels.removeIf(label -> id.equals(label.getId()));
        Util.writeToFile(listLabels, PATH);
    }

    @Override
    public Label update(Label label) {

        List<Label> listLabels = util.getAllInternal(PATH);
        var result = listLabels.stream()
                .map(t -> {
                    if (t.getId().equals(label.getId())) {
                        return label;
                    } else return t;
                }).toList();

        Util.writeToFile(result, PATH);
        return label;
    }

    @Override
    public List<Label> getAll() {
        return util.getAllInternal(PATH);
    }

}
