package ru.tinkoff.fintech.qa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileDataProvider {
    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static List<String> readNameAndGender() {
        List<String> nameAndGender = new ArrayList<>();
        if (randBetween(0, 1) == 1) {
            String name = read_file("src/main/resources/MaleName.txt", randBetween(1, 30));
            nameAndGender.add(name);
            nameAndGender.add("М");
        } else {
            String name = read_file("src/main/resources/FemaleName.txt", randBetween(1, 30));
            nameAndGender.add(name);
            nameAndGender.add("Ж");
        }
        return nameAndGender;
    }

    public static String readSurname(String gender) {
        String surname;
        if (gender.equals("М")) {
            surname = read_file("src/main/resources/MaleSurname.txt", randBetween(1, 30));
        } else {
            surname = read_file("src/main/resources/FemaleSurname.txt", randBetween(1, 30));

        }
        return surname;
    }

    public static String readPatronymic(String gender) {
        String patronymic;
        if (gender.equals("М")) {
            patronymic = read_file("src/main/resources/MalePatronymic.txt", randBetween(1, 30));
        } else {
            patronymic = read_file("src/main/resources/FemalePatronymic.txt", randBetween(1, 30));
        }
        return patronymic;
    }

    public static String readCountry() {
        return read_file("src/main/resources/Countries.txt", randBetween(1, 30));
    }

    public static String readCity() {
        return read_file("src/main/resources/Cities.txt", randBetween(1, 30));
    }

    public static String readStreet() {
        return read_file("src/main/resources/Streets.txt", randBetween(1, 30));
    }

    public static String readRegion() {
        return read_file("src/main/resources/Regions.txt", randBetween(1, 30));

    }

    public static int readPostcode() {
        return randBetween(1000000, 10000000 - 1);
    }

    public static int readApartment() {
        return randBetween(1, 100);
    }

    public static int readHouse() {
        return randBetween(1, 100);
    }

    public static long readItn() {
        int branchNumber = randBetween(10, 51);
        long itn = 770000000000L + branchNumber * 100000000L;
        int k = 100;
        for (int i = 1; i <= 6; ++i) {
            int n = randBetween(0, 9);
            itn += n * k;
            k *= 10;
        }
        int[] array = new int[10];
        k = 1;
        for (int i = 0; i != 10; ++i) {
            array[i] = (int) (itn / 100) % (10 * k);
            k *= 10;
        }
        int[] secondIndex = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum2 = 0;
        for (int i = 0; i != 10; ++i) {
            sum2 = secondIndex[i] * array[i];
        }
        int secondKnt = sum2 % 11;
        itn += secondKnt * 10;

        int[] firstIndex = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum1 = firstIndex[10] * secondKnt;

        int firstKnt = sum1 % 11;
        itn += firstKnt;
        return itn;
    }

    private static String read_file(String path, int line_number) {
        String fileContent = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String sub;
            int i = 1;
            while ((sub = br.readLine()) != null) {
                if (i == line_number) {
                    fileContent = sub;
                    break;
                }
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

}
