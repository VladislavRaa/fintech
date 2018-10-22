package ru.tinkoff.fintech.qa;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.mashape.unirest.http.exceptions.UnirestException;

import static ru.tinkoff.fintech.qa.GetObject.getObject;

public class CreateSheet {

    private static String[] List = {"Имя", "Фамилия", "Отчество", "Возраст", "Пол",
            "Дата Рождения", "ИНН", "Почтовый индекс", "Страна",
            "Область", "Город", "Улица", "Дом", "Квартира"};

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static void main(String[] args) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Новый лист");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i != List.length; ++i) {
            row.createCell(i).setCellValue(List[i]);
        }
        int size = 1 + (int) (Math.random() * 30);
        List<MainPojo> mainPojoList = fillData(size);
        for (MainPojo mainPojo : mainPojoList) {
            createSheetHeader(sheet, ++rowNum, mainPojo);
        }
        File file = new File("MainPojo.xls");

        try {
            System.setErr(new PrintStream(new File("log.txt")));
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("Файл создан:" + file.getAbsolutePath());
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

    private static void write_name(MainPojo mainPojo) {
        if (randBetween(0, 1) == 1) {
            String name = read_file("src/main/resources/MaleName.txt", randBetween(1, 30));
            mainPojo.setName(name);
            mainPojo.setGender("М");
        } else {
            String name = read_file("src/main/resources/FemaleName.txt", randBetween(1, 30));
            mainPojo.setName(name);
            mainPojo.setGender("Ж");
        }
    }

    private static void write_surname(MainPojo mainPojo) {
        if (mainPojo.getGender().equals("М")) {
            String surname = read_file("src/main/resources/MaleSurname.txt", randBetween(1, 30));
            mainPojo.setSurname(surname);
        } else {
            String surname = read_file("src/main/resources/FemaleSurname.txt", randBetween(1, 30));
            mainPojo.setSurname(surname);
        }
    }

    private static void write_patronymic(MainPojo mainPojo) {
        if (mainPojo.getGender().equals("М")) {
            String patronymic = read_file("src/main/resources/MalePatronymic.txt", randBetween(1, 30));
            mainPojo.setPatronymic(patronymic);
        } else {
            String patronymic = read_file("src/main/resources/FemalePatronymic.txt", randBetween(1, 30));
            mainPojo.setPatronymic(patronymic);
        }
    }

    static void write_country(MainPojo mainPojo) {
        String country = read_file("src/main/resources/Countries.txt", randBetween(1, 30));
        mainPojo.setCountry(country);
    }

    private static void write_city(MainPojo mainPojo) {
        String city = read_file("src/main/resources/Cities.txt", randBetween(1, 30));
        mainPojo.setCity(city);
    }

    private static void write_street(MainPojo mainPojo) {
        String street = read_file("src/main/resources/Streets.txt", randBetween(1, 30));
        mainPojo.setStreet(street);
    }

    static void write_region(MainPojo mainPojo) {
        String region = read_file("src/main/resources/Regions.txt", randBetween(1, 30));
        mainPojo.setRegion(region);
    }

    static void write_dob(MainPojo mainPojo) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, randBetween(1920, 2000));
        gc.set(Calendar.DAY_OF_MONTH, randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR)));
        mainPojo.setDob(gc);
    }

    static void write_age(MainPojo mainPojo) {
        Calendar calendar = Calendar.getInstance();
        int age = calendar.get(Calendar.YEAR) - mainPojo.getDob().get(Calendar.YEAR);
        if (((mainPojo.getDob().get(Calendar.MONTH)) - calendar.get(Calendar.MONTH) > 0) | (((mainPojo.getDob().get(Calendar.MONTH)) - calendar.get(Calendar.MONTH) > 0) & ((mainPojo.getDob().get(Calendar.DAY_OF_MONTH)) - calendar.get(Calendar.DAY_OF_MONTH) >= 0))) {
            mainPojo.setAge(age - 1);
        } else mainPojo.setAge(age);
    }

    private static void write_postcode(MainPojo mainPojo) {
        mainPojo.setPostcode(randBetween(1000000, 10000000 - 1));
    }

    private static void write_apartment(MainPojo mainPojo) {
        mainPojo.setApartment(randBetween(1, 100));
    }

    private static void write_house(MainPojo mainPojo) {
        mainPojo.setHouse(randBetween(1, 100));
    }

    static void write_itn(MainPojo mainPojo) {
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
        int sum1 = 0;
        for (int i = 0; i != 10; ++i) {
            sum1 = firstIndex[i] * array[i];
        }

        sum1 = firstIndex[10] * secondKnt;

        int firstKnt = sum1 % 11;
        itn += firstKnt;
        mainPojo.setItn(itn);
    }

    private static List<MainPojo> fillData(int size) {
        List<MainPojo> data = new ArrayList<>();
        try {
            getObject(data, size);
            return data;
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("No connection");
            for (int i = 0; i != size; ++i) {
                data.add(new MainPojo());
                write_name(data.get(i));
                write_surname(data.get(i));
                write_patronymic(data.get(i));
                write_country(data.get(i));
                write_region(data.get(i));
                write_city(data.get(i));
                write_street(data.get(i));
                write_dob(data.get(i));
                write_age(data.get(i));
                write_postcode(data.get(i));
                write_apartment(data.get(i));
                write_house(data.get(i));
                write_itn(data.get(i));
            }
            return data;
        }
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, MainPojo mainPojo) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(mainPojo.getName());
        row.createCell(1).setCellValue(mainPojo.getSurname());
        row.createCell(2).setCellValue(mainPojo.getPatronymic());
        row.createCell(3).setCellValue(mainPojo.getAge());
        row.createCell(4).setCellValue(mainPojo.getGender());
        row.createCell(5).setCellValue(mainPojo.getDob().get(Calendar.DAY_OF_MONTH) + "-" + (mainPojo.getDob().get(Calendar.MONTH) + 1) + "-" + mainPojo.getDob().get(Calendar.YEAR));
        row.createCell(6).setCellValue(mainPojo.getItn().toString());
        row.createCell(7).setCellValue(mainPojo.getPostcode());
        row.createCell(8).setCellValue(mainPojo.getCountry());
        row.createCell(9).setCellValue(mainPojo.getRegion());
        row.createCell(10).setCellValue(mainPojo.getCity());
        row.createCell(11).setCellValue(mainPojo.getStreet());
        row.createCell(12).setCellValue(mainPojo.getHouse());
        row.createCell(13).setCellValue(mainPojo.getApartment());
    }

}
