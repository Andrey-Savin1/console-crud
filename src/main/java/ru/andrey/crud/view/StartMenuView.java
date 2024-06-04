package ru.andrey.crud.view;

import java.util.Scanner;

public class StartMenuView {

    private final Scanner scanner = new Scanner(System.in);
    private final ViewWriter writer = new ViewWriter();
    private final ViewPost viewPost = new ViewPost();
    private final ViewLabel viewLabel = new ViewLabel();

    public void startMenu() {
        String scan;
        do {
            System.out.println("""
                    1 -> Создать писателя
                    2 -> Создать пост
                    3 -> Создать лейбл
                    4 -> Выход"""
            );
            scan = scanner.nextLine();

            switch (scan) {
                case "1" -> writer.runWriter();
                case "2" -> viewPost.runPost();
                case "3" -> viewLabel.runLabel();
            }
        } while (!scan.equals("4"));
    }
}
