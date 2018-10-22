package ru.tinkoff.fintech.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ru.tinkoff.fintech.qa.CreateSheet.*;

public class GetObject {
    public static void getObject(List<MainPojo> data, int size) throws UnirestException {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        for (int i = 0; i < size; ++i) {
            HttpResponse<JsonPojo> response =
                    Unirest.get("https://randus.org/api.php").asObject(JsonPojo.class);
            JsonPojo jdata = response.getBody();

            data.get(i).setName(jdata.getLname());
            data.get(i).setSurname(jdata.getFname());
            data.get(i).setPatronymic(jdata.getPatronymic());
            data.get(i).setGender(jdata.getGender());
            data.get(i).setPostcode(parseInt(jdata.getPostcode()));
            data.get(i).setCity(jdata.getCity());
            data.get(i).setStreet(jdata.getStreet());
            data.get(i).setHouse(jdata.getHouse());
            data.get(i).setAge(jdata.getApartment());

            write_country(data.get(i));
            write_region(data.get(i));
            write_itn(data.get(i));
            write_dob(data.get(i));
            write_age(data.get(i));
        }
    }
}
