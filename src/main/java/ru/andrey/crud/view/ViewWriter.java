package ru.andrey.crud.view;

import ru.andrey.crud.controller.LabelController;
import ru.andrey.crud.controller.PostController;
import ru.andrey.crud.controller.WriterController;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Post;
import ru.andrey.crud.model.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewWriter {

    private final Scanner scanner = new Scanner(System.in);
    private final WriterController writerController = new WriterController();
    private final LabelController labelController = new LabelController();
    private final PostController postController = new PostController();

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
        } while (!line.equals("6"));
    }

    private void menu() {
        System.out.println("""
                Какое действие вы хотите выполнить?
                 1 -> Создать Писателя
                 2 -> Удалить Писателя
                 3 -> Обновить Писателя
                 4 -> Получить Писателя по id
                 5 -> Получить всех писателей
                 6 -> В начальное меню"""
        );
    }

    private void createWriter() {

        System.out.println("Введите имя писателя");
        var firstName = scanner.nextLine();
        System.out.println("Введите фамилию писателя");
        var lastName = scanner.nextLine();
        var posts = new ArrayList<Post>();
        do {
            System.out.println("Введите название поста");
            var title = scanner.nextLine();
            System.out.println("Введите содержание поста");
            var content = scanner.nextLine();
            var labels = new ArrayList<Label>();
            addLabel(labels, scanner, labelController);
            posts.add(postController.createPost(title, content,  labels));

            System.out.println();
            System.out.println("1 -> Добавить еще один Пост?");
            System.out.println("2 -> Вернуться в предыдущее меню");
        }while (!scanner.nextLine().equals("2"));
        writerController.createWriter(firstName, lastName, posts);
        System.out.println();
        System.out.println("1 -> Создать еще одного писателя");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    static void addLabel(ArrayList<Label> labels, Scanner scanner, LabelController labelController) {
        do {
            System.out.println("Введите название лейбла");
            var name = scanner.nextLine();
            labels.add(labelController.addLabel(name, Status.ACTIVE));

            System.out.println();
            System.out.println("1 -> Добавить еще один Label?");
            System.out.println("2 -> Вернуться в предыдущее меню");
        }while (!scanner.nextLine().equals("2"));
    }

    private void updateWriterById() {

        System.out.println("Введите id писателя");
        var writerId = Long.parseLong(scanner.nextLine());

        System.out.println("Введите имя писателя");
        var firstName = scanner.nextLine();
        System.out.println("Введите фамилию писателя");
        var lastName = scanner.nextLine();

        var posts = new ArrayList<Post>();
        do {
            System.out.println("Введите id поста");
            var postId = Long.parseLong(scanner.nextLine());
            System.out.println("Введите название поста");
            var title = scanner.nextLine();
            System.out.println("Введите содержание поста");
            var content = scanner.nextLine();
            var labels = new ArrayList<Label>();
            do {
                System.out.println("Введите id лейбла");
                var id = Long.parseLong(scanner.nextLine());
                System.out.println("Введите название лейбла");
                var name = scanner.nextLine();
                labels.add(labelController.updateLabel(id, name, Status.ACTIVE));

                System.out.println();
                System.out.println("1 -> Обновить еще один Label?");
                System.out.println("2 -> Вернуться в предыдущее меню");
            }while (!scanner.nextLine().equals("2"));
            posts.add(postController.updatePost(postId, title, content,  labels));

            System.out.println();
            System.out.println("1 -> Обновить еще один Пост?");
            System.out.println("2 -> Вернуться в предыдущее меню");
        }while (!scanner.nextLine().equals("2"));

        writerController.updateWriter(writerId, firstName, lastName, posts);
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

}
