package ru.andrey.crud.view;

import ru.andrey.crud.controller.LabelController;
import ru.andrey.crud.controller.PostController;
import ru.andrey.crud.model.Label;
import ru.andrey.crud.model.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewPost {

    private final Scanner scanner = new Scanner(System.in);
    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();

    public void runPost() {
        String line;

        do {
            menu();
            line = scanner.nextLine();
            if (line.equals("1")) {
                do {
                    createPost();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("2")) {
                do {
                    deletePost();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("3")) {
                do {
                    updatePostById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("4")) {
                do {
                    getPostById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("5")) {
                do {
                    getAllPost();
                } while (!scanner.nextLine().equals("2"));
            }
        } while (!line.equals("6"));
    }

    private void menu() {
        System.out.println("""
                Какое действие вы ходите сделать?
                 1 -> Создать Post
                 2 -> Удалить Post
                 3 -> Обновить Post
                 4 -> Получить Post по id
                 5 -> Получить все посты
                 6 -> В начальное меню"""
        );
    }

    private void createPost() {

        System.out.println("Введите заголовок");
        var title = scanner.nextLine();
        System.out.println("Введите содержание");
        var content = scanner.nextLine();
        var labels = new ArrayList<Label>();
        ViewWriter.addLabel(labels, scanner, labelController);

        postController.createPost(title, content, labels);
        System.out.println();
        System.out.println("1 -> Создать еще один Post?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void updatePostById() {
        System.out.println("Введите id поста");
        var postId = Long.parseLong(scanner.nextLine());
        System.out.println("Введите заголовок");
        var title = scanner.nextLine();
        System.out.println("Введите содержание");
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

        postController.updatePost(postId, title, content,labels);
        System.out.println();
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void deletePost() {

        System.out.println("Введите id поста");
        var id = Long.parseLong(scanner.nextLine());
        postController.deletePost(id);
        System.out.println();
        System.out.println("1 -> Удалить еще один Post?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void getPostById() {

        System.out.println("Введите id поста");
        var postId = Long.parseLong(scanner.nextLine());

        System.out.println(postController.getPostById(postId));
        System.out.println();
        System.out.println("1 -> Получить еще один Post?");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void getAllPost() {
        postController.getAllPosts().forEach(System.out::println);
        System.out.println();
        System.out.println("2 -> Вернуться в предыдущее меню");
    }
}
