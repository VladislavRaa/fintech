package ru.tinkoff.fintech.qa;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;

import java.util.*;

import com.mashape.unirest.http.exceptions.UnirestException;

import static ru.tinkoff.fintech.qa.GetObject.getObject;

public class CreateSheet {

    private static String[] List = {"Имя", "Фамилия", "Отчество", "Возраст", "Пол",
            "Дата Рождения", "ИНН", "Почтовый индекс", "Страна",
            "Область", "Город", "Улица", "Дом", "Квартира"};


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
            FileOutputStream out = new FileOutputStream(file);
            System.setErr(new PrintStream(new File("log.txt")));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("Файл создан:" + file.getAbsolutePath());
    }

    private static List<MainPojo> fillData(int size) {
        try {
            return getObject(size);
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("No connection");
            List<MainPojo> data = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                MainPojo pojo = new MainPojo();
                List<String> nameAndGender = FileDataProvider.readNameAndGender();
                String gender = nameAndGender.get(1);

                pojo.setName(nameAndGender.get(0));
                pojo.setGender(nameAndGender.get(1));
                pojo.setSurname(FileDataProvider.readSurname(gender));
                pojo.setPatronymic(FileDataProvider.readPatronymic(gender));
                pojo.setCountry(FileDataProvider.readCountry());
                pojo.setRegion(FileDataProvider.readRegion());
                pojo.setCity(FileDataProvider.readCity());
                pojo.setStreet(FileDataProvider.readStreet());
                pojo.setPostcode(FileDataProvider.readPostcode());
                pojo.setApartment(FileDataProvider.readApartment());
                pojo.setHouse(FileDataProvider.readHouse());
                pojo.setItn(FileDataProvider.readItn());
                data.add(pojo);
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
        row.createCell(5).setCellValue(mainPojo.getDob());
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
