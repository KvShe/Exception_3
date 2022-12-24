package org.example;

import org.example.sustom_exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter data: ");
        String[] input = scanner.nextLine().split(" ");

        if (isValid(input)) {
            writeToFile(input);
        } else {
            System.err.println("Data is not valid");
        }
    }

    private static void writeToFile(String[] input) {
        String nameFile = "src/main/resources/" + input[0] + ".txt";
        try {
            FileWriter writer = new FileWriter(nameFile, true);
            writer.write(Arrays.toString(input));
            writer.append("\n");
            writer.close();
            System.out.println("Record successfully added");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static boolean isValid(String[] input) {
        try {
            verity(input);
        } catch (InputSizeException | NameFormatException | DateFormatException | PhoneFormatException |
                 SexFormatException exception) {
            return false;
        }
        return true;
    }

    private static void verity(String[] input) throws InputSizeException, NameFormatException, DateFormatException, PhoneFormatException, SexFormatException {

        if (input.length != 6) {
            throw new InputSizeException("Data is not valid");
        }

        boolean regex = !input[0].matches("[а-яА-Я]+|\\w+") || !input[1].matches("[а-яА-Я]+|\\w+") || !input[2].matches("[а-яА-Я]+|\\w+");
        if (regex) {
            throw new NameFormatException("Fields mast have letters");
        }

        if (!input[3].matches("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d{2})")) {
            throw new DateFormatException("Date birthday is not valid");
        }

        if (!input[4].matches("\\d{11}")) {
            throw new PhoneFormatException("Phone is not valid");
        }

        String sex = input[5].toLowerCase();
        if (!(sex.equals("m") || sex.equals("f"))) {
            throw new SexFormatException("Sex is incorrect");
        }
    }
}
