package coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {
    public static void main(String[] args) {

        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");

        String[] options = {
                "add",
                "remove",
                "list",
                "exit"
        };
        for (int i = 0; i < options.length; i++) {
            System.out.println(pl.coderslab.ConsoleColors.WHITE_BRIGHT + options[i]);

        }
        String[][] tasks = dataInput();
        // for (int i = 0; i < tasks.length; i++) {
        //     for (int j = 0; j < tasks[i].length; j++) {
        //        System.out.println(tasks[i][j]);
        //     }
        // }

        //System.out.println(tasks[0][1]);
        // System.out.println(tasks[1][1]);
        //System.out.println(tasks[2][1]);

        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        switch (input) {
            case "add":
                String[][] tasksAdd = addTask();
                break;
            case "remove":
                System.out.println("Usuń zadanie");
                break;
            case "list":
                String lista = displayList();
                System.out.println(lista);
                break;
            case "exit":
                break;
            default:
                System.out.println("Please select a correct option.");
        }
    }

    public static String[][] addTask() {
        Scanner scanTask = new Scanner(System.in);

        System.out.println("Please add task description");
        String inputTask = scanTask.next();
        System.out.println(inputTask);

        System.out.println("Please add task due date (yyyy-mm-dd");
        String date = scanTask.next();
        System.out.println(date);


        System.out.println("Is your task important: true/false");
        boolean bn = scanTask.nextBoolean();
        if (bn == true) {
            System.out.println(bn);
        } else if (bn == false) {
            System.out.println(bn);
        }

        String[][] tasks = dataInput();
        String[][] tasks2 = new String[tasks.length + 1][];
        for (int i = 0; i < tasks.length; i++) {
            tasks2[i] = Arrays.copyOf(tasks[i], tasks[i].length);
        }
        String statement = Boolean.toString(bn);
        String[] collect = {inputTask, date, statement};
        tasks2[tasks.length] = collect;
        //tasks2[tasks.length + 1][1]= date;
        //tasks2[tasks.length + 1][2]= "t/f";

        return tasks2;
    }

    public static String displayList() {
        String[][] tasks = dataInput();
        StringBuilder sb = new StringBuilder();
        StringBuilder inner = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                inner.append(tasks[i][j] + " ");
            }
            sb.append(i + " : " + inner.toString() + "\n");
        }

        String display = sb.toString();
        return display;
    }

    public static String[][] dataInput() {

        // File file = new File(path);
        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        //System.out.println(reading.toString());

        String fromFile = reading.toString();
        //System.out.println(fromFile);

        String[] outer = fromFile.split("\\r?\\n");
        int size = outer.length;
        String[][] result = new String[size][];
        int count = 0;
        for (String line : outer) {
            result[count] = line.split(", ");
            count++;
        }
        return result;
        //System.out.println("TEST");
        // System.out.println(result[0][0]);
    }
}
