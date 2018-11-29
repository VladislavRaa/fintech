package ru.tinkoff.fintech.qa;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainPojo {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String gender;
    private String dob;
    private Long itn;
    private int postcode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int house;
    private int apartment;

    public MainPojo() {
    }

    public MainPojo(String name, String surname, String patronymic,
                    int age, String gender, String dob, Long itn, int postcode, String country, String region, String city, String street, int house, int apartment) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
        this.dob = dob;
        this.itn = itn;
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    String getPatronymic() {
        return patronymic;
    }

    void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setAge(String age){
        this.age = ageFromBirthday(age);
    }

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    String getDob() {
        return dob;
    }

    void setDob(String dob) {
        this.dob = dob;
    }

    Long getItn() {
        return itn;
    }

    void setItn(Long itn) {
        this.itn = itn;
    }

    int getPostcode() {
        return postcode;
    }

    void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    String getRegion() {
        return region;
    }

    void setRegion(String region) {
        this.region = region;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    int getHouse() {
        return house;
    }

    void setHouse(int house) {
        this.house = house;
    }

    int getApartment() {
        return apartment;
    }

    void setApartment(int apartment) {
        this.apartment = apartment;
    }

    private int ageFromBirthday(String birthday) {
        // 3 февраля 1977 -> today - 3 февраля 1977
        String[] listOfBirthday = birthday.split(" ");
        Integer day = Integer.parseInt(listOfBirthday[0]);
        Integer year = Integer.parseInt(listOfBirthday[2]);
        Integer month = monthsFromString(listOfBirthday[1]);
        Date dob = new Date(year - 1900, month, day);
        return (getDateDiff(new Date(),dob,TimeUnit.DAYS)/365) ;
    }

    private int monthsFromString(String month){
        switch (month) {
            case "января" : return 1;
            case "февраля" : return 2;
            case "марта" : return 3;
            case "апреля" : return 4;
            case "мая" : return 5;
            case "июня" : return 6;
            case "июля" : return 7;
            case "августа" : return 8;
            case "сентября" : return 9;
            case "октября" : return 10;
            case "ноября" : return 11;
            case "декабря" : return 12;
        }
        return -1;
    }
    private static int getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date1.getTime() - date2.getTime();
        return (int)timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}

