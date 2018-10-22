package ru.tinkoff.fintech.qa;
import java.util.Calendar;

public class MainPojo {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String gender;
    private Calendar dob;
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

    public MainPojo(String name, String surname, String patronymic, int age, String gender, Calendar dob, Long itn, int postcode, String country, String region, String city, String street, int house, int apartment) {
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

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    Calendar getDob() {
        return dob;
    }

    void setDob(Calendar dob) {
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
}

