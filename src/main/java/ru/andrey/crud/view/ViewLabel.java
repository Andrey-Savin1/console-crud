package ru.andrey.crud.view;

import ru.andrey.crud.controller.LabelController;
import ru.andrey.crud.model.Status;

import java.util.Scanner;

public class ViewLabel {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelController labelController = new LabelController();

    public void runLabel() {
        String line;

        do {
            menu();
            line = scanner.nextLine();
            if (line.equals("1")) {
                do {
                    createLabel();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("2")) {
                do {
                    deleteLabelById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("3")) {
                do {
                    updateLabelById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("4")) {
                do {
                    getLabelById();
                } while (!scanner.nextLine().equals("2"));
            }
            if (line.equals("5")) {
                do {
                    getAllLabels();
                } while (!scanner.nextLine().equals("2"));
            }
        } while (!line.equals("6"));
        StartMenuView startMenuView = new StartMenuView();
        startMenuView.startMenu();
    }

    private void menu() {
        System.out.println("""
                Какое действие вы ходите сделать?
                 1 -> Создать Label
                 2 -> Удалить Label
                 3 -> Обновить Label
                 4 -> Получить Label по id
                 5 -> Получить все Labels
                 6 -> Начальное меню"""
        );
    }

    private void createLabel() {
        System.out.println("Введите имя");

        var name = scanner.nextLine();
        var newLabel = labelController.addLabel(name, Status.ACTIVE);
        System.out.println("Id созданого лейбла: " + newLabel.getId());
        System.out.println();
        System.out.println("1 -> Создать еще один Label");
        System.out.println("2 -> Вернуться в предыдущее меню");

    }

    private void getLabelById() {

        do {
            System.out.println("Введите id ");
            Long id = Long.parseLong(scanner.nextLine());

            if (labelController.getAll().stream().anyMatch(t -> id.equals(t.getId()))) {
                System.out.println(labelController.getLabelById(id));
                System.out.println();
                System.out.println("1 -> Найти еще один Label");
                System.out.println("2 -> Вернуться в предыдущее меню");

                if (scanner.nextLine().equals("1")) {
                    getLabelById();
                } else runLabel();
            } else {
                System.out.println("Такого id не существует");
                getLabelById();
            }
        } while (!scanner.nextLine().equals("2"));
    }

    private void deleteLabelById() {
        System.out.println("Введите id");
        var id = Long.parseLong(scanner.nextLine());
        labelController.deleteLabel(id);

        System.out.println();
        System.out.println("1 -> Удалить еще один Label");
        System.out.println("2 -> Вернуться в предыдущее меню");
    }

    private void updateLabelById() {
        do {
            System.out.println("Введите id Label");
            Long id = Long.parseLong(scanner.nextLine());

            if (labelController.getAll().stream().allMatch(t -> id.equals(t.getId()))) {
                System.out.println("Введите новый Label");
                var name = (scanner.nextLine());
                var updateLabel = labelController.updateLabel(id, name, Status.ACTIVE);

                System.out.println("Id обновленного лейбла: " + updateLabel.getId());
                System.out.println();

                System.out.println("1 -> Обновить еще один Label");
                System.out.println("2 -> Вернуться в предыдущее меню");
                if (scanner.nextLine().equals("1")) {
                    updateLabelById();
                } else runLabel();
            } else {
                System.out.println("Такого id не существует");
                updateLabelById();
            }
        } while (!scanner.nextLine().equals("2"));
    }

    private void getAllLabels() {
        labelController.getAll().forEach(System.out::println);
        System.out.println();
        System.out.println("2 -> Вернуться в предыдущее меню");
    }
}
