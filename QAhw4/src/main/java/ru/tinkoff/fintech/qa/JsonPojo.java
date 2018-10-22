package ru.tinkoff.fintech.qa;
public class JsonPojo {
    private final String lname;
    private final String fname;
    private final String patronymic;
    private final String gender;
    private final String date;
    private final String postcode;
    private final String city;
    private final String street;
    private final int house;
    private final int apartment;
    private final String phone;
    private final String login;
    private final String password;
    private final String color;
    private final String userpic;

    public JsonPojo(String lname, String fname, String patronymic, String gender, String date, String postcode, String city, String street, int house, int apartment, String phone, String login, String password, String color, String userpic) {
        this.lname = lname;
        this.fname = fname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.date = date;
        this.postcode = postcode;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.color = color;
        this.userpic = userpic;
    }


    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }

    public String getPhone() {
        return phone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getColor() {
        return color;
    }

    public String getUserpic() {
        return userpic;
    }
}
