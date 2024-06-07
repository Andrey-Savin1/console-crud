package ru.andrey.crud.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.model.Writer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util<T> {

    private final Class<T> type;

    public Util(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public List<T> getAllInternal(String path) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(path)) {

            Type listType = null;
            if (getType().equals(Label.class)) {
                listType = new TypeToken<ArrayList<Label>>() {}.getType();
            } else if (getType().equals(Post.class)) {
                listType = new TypeToken<ArrayList<Post>>() {}.getType();
            } else if (getType().equals(Writer.class)) {
                listType = new TypeToken<ArrayList<Writer>>() {}.getType();
            }

            if (listType != null) {
                return gson.fromJson(reader, listType);
            }
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return new ArrayList<>();
    }

    public static <T> void writeToFile(List<T> items, String path) {
        Gson gson = new Gson();
        byte[] jsonString = gson.toJson(items).getBytes();

        try {
            Files.write(Path.of(path), jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
