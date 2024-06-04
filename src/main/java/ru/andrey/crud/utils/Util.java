package ru.andrey.crud.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Util<T> {

    private final Gson gson = new Gson();

//    public List<T> getAllInternal(String path) {
//
//
//        Scanner scanner;
//        try {
//            scanner = new Scanner(new FileReader(path));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (scanner.hasNextLine()) {
//            Type listType = new TypeToken<ArrayList<T>>() { }.getType();
//            return gson.fromJson(scanner.next(), listType);
//        }
//        scanner.close();
//        return new ArrayList<>();
//    }

    public void writeToFile(List<T> listLabels, String path) {
        byte[] jsonString = gson.toJson(listLabels).getBytes();

        try {
            Files.write(Path.of(path), jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
