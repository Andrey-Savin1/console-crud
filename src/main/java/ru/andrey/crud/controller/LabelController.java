package ru.andrey.crud.controller;


import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Status;
import ru.andrey.crud.repository.repositoryImpl.GsonLabelRepositoryImpl;
import ru.andrey.crud.repository.LabelRepository;

import java.util.List;

public class LabelController {

    private final LabelRepository gsonLabelRepository = new GsonLabelRepositoryImpl();


    public Label addLabel(String name, Status status) {

        Label label = new Label();
        label.setName(name);
        label.setStatus(status);
        return gsonLabelRepository.create(label);
    }

    public Label getLabelById(Long id) {
        return gsonLabelRepository.getById(id);
    }

    public void deleteLabel(Long id) {
        gsonLabelRepository.deleteById(id);
    }

    public Label updateLabel(Long id, String name, Status status) {

        Label label = new Label();
        label.setId(id);
        label.setName(name);
        label.setStatus(status);
        return gsonLabelRepository.update(label);
    }

    public List<Label> getAll() {
        return gsonLabelRepository.getAll();
    }
}
