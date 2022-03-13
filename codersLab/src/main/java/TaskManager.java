package coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {
    public static void main(String[] args) {

//Method01- display the list of available options regarding the color.
        String[][] tasks = dataInput();
        optionsList();

//Method02- obtain a 2D array from the csv file.


//scan the options.
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String input = scan.next();

        switch (input) {
                case "add":
//Method03- add tasks, obtain new array.
                    String[][] tasksAdd = addTask();
                    break;
                case "remove":
//Method04- remove tasks
                    removeLine(tasks, numberObtain());
                    System.out.println("Value was succesfully deleted");
                    for (int i = 0; i < tasks.length; i++) {
                        for (int j = 0; j < tasks[i].length; j++) {
                            System.out.println(tasks[i][j]);
                        }
                    }
                    break;
//Method05- display list
                case "list":
                    displayList(tasks);
                    break;
                case "exit":
                    saveFile();
                    System.out.println(ConsoleColors.RED + "Bye, bye!");
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            optionsList();
        }
    }

    //Method03
    public static String[][] addTask() {
        Scanner scanTask = new Scanner(System.in);
        System.out.println("Please add task description");
        String inputTask = scanTask.next();
        //System.out.println(inputTask);

        System.out.println("Please add task due date (yyyy-mm-dd");
        String date = scanTask.next();
        //System.out.println(date);


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

    //Method05
    public static void displayList(String[][] tab) {
        //String[][] tasks = dataInput();
        //StringBuilder sb = new StringBuilder();
        //StringBuilder inner = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Method04a
    public static void removeLine(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                String[][]tasks = ArrayUtils.remove(tab, index);
        }
    } catch(ArrayIndexOutOfBoundsException ex){
                System.out.println("Element doesn't exist");
            }
        }

    //Method04b.1
    public static int numberObtain() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove");

        String number = scan.nextLine();
        while (!isNumberGreaterEqualZero(number)) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0.");
            scan.nextLine();
        }
        return Integer.parseInt(number);
    }

    //Method04b.1
    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static void saveFile() {
        String[][] tasks = dataInput();
        try (PrintWriter printWriter = new PrintWriter("tasks01.csv")) {
            for (int i = 0; i < tasks.length; i++) {
                for (int j = 0; j < tasks[i].length; j++) {
                    printWriter.println(tasks[i][j]);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Błąd zapisu do pliku.");
        }

    }

    //Method02
    public static String[][] dataInput() {

//add a file and convert it into the String
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

        //Convert the String into a 2D array

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


    //Method01
    public static void optionsList() {
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
    }
}
