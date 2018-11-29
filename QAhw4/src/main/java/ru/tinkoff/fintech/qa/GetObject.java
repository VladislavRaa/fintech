package ru.tinkoff.fintech.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GetObject {
    private final static ObjectMapper objectMapper = new ObjectMapper() {
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
    };

    public static List<MainPojo> getObject(int size) throws UnirestException {
        Unirest.setObjectMapper(objectMapper);
        ArrayList<MainPojo> data = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            HttpResponse<JsonPojo> response =
                    Unirest.get("https://randus.org/api.php").asObject(JsonPojo.class);
            JsonPojo jdata = response.getBody();
            MainPojo pojo = new MainPojo();
            pojo.setName(jdata.getLname());
            pojo.setSurname(jdata.getFname());
            pojo.setPatronymic(jdata.getPatronymic());
            pojo.setGender(jdata.getGender());
            pojo.setPostcode(parseInt(jdata.getPostcode()));
            pojo.setCity(jdata.getCity());
            pojo.setStreet(jdata.getStreet());
            pojo.setHouse(jdata.getHouse());
            pojo.setApartment(jdata.getApartment());
            pojo.setDob(jdata.getDate());
            pojo.setAge(jdata.getDate());
            pojo.setRegion(FileDataProvider.readRegion());
            pojo.setCountry(FileDataProvider.readCountry());
            pojo.setItn(FileDataProvider.readItn());
            data.add(pojo);
        }
        return data;
    }
}
