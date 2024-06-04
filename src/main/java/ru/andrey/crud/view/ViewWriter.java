package ru.andrey.crud.view;

import ru.andrey.crud.controller.WriterController;

import java.util.Scanner;

public class ViewWriter {

    private final Scanner scanner = new Scanner(System.in);
    private final WriterController writerController = new WriterController();

    public void runWriter() {
        String line;
        do {
            menu();
            line = scanner.nextLine();
            if (line.equals("1")) {
                do {
                    createWriter();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("2")) {
                do {
                    deleteWriter();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("3")) {
                do {
                    updateWriterById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("4")) {
                do {
                    getWriterById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("5")) {
                do {
                    getAllWriters();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("6")) {
                do {
                    addLabelToPost();
                } while (!scanner.nextLine().equals("2"));
            }
        } while (!line.equals("7"));
        StartMenuView startMenuView = new StartMenuView();
        startMenuView.startMenu();
    }

    private void menu() {
        System.out.println("""
                Какое действие вы хотите выполнить?
                 1 -> Создать Писателя
                 2 -> Удалить Писателя
                 3 -> Обновить Писателя
                 4 -> Получить Писателя по id
                 5 -> Получить всех писателей
                 6 -> Добавить Post к писателю
                 7 -> В начальное меню"""
        );
    }

    private void createWriter() {

        System.out.println("Введите имя писателя");
        var firstName = scanner.nextLine();
        System.out.println("Введите фамилию писателя");
        var lastName = scanner.nextLine();

        writerController.createWriter(firstName, lastName);
        System.out.println();
        System.out.println("1 -> Создать еще одного писателя");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void updateWriterById() {

        System.out.println("Введите id писателя");
        var writerId = Long.parseLong(scanner.nextLine());

        System.out.println("Введите имя писателя");
        var firstName = scanner.nextLine();
        System.out.println("Введите фамилию писателя");
        var lastName = scanner.nextLine();

        writerController.updateWriter(writerId, firstName, lastName);
        System.out.println();
        System.out.println("2 -> Вернуться в предыдущее меню");

    }

    private void deleteWriter() {

        System.out.println("Введите id писателя");
        var id = Long.parseLong(scanner.nextLine());
        writerController.deleteWriter(id);
        System.out.println();
        System.out.println("1 -> Удалить еще одиного писателя?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void getWriterById() {

        System.out.println("Введите id писателя");
        var postId = Long.parseLong(scanner.nextLine());

        System.out.println(writerController.getWriterById(postId));
        System.out.println();
        System.out.println("1 -> Получить еще одиного писателя?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void getAllWriters() {

        writerController.getAllWriters().forEach(System.out::println);
        System.out.println();
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void addLabelToPost() {

        System.out.println("Введите id поста");
        var id = Long.parseLong(scanner.nextLine());
        System.out.println("Введите заголовок");
        var title = scanner.nextLine();

        System.out.println("Введите контент");
        var content = scanner.nextLine();
        writerController.addPost(id, title, content);

        System.out.println();
        System.out.println("1 -> Добавить еще один Label?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

}
