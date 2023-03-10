package com.iau.flight_management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iau.flight_management.model.dto.Airport;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService{

    private final static String THY_IATA = "TK";
    private final static String THY_ICAO = "THY";


    @Override
    public List<Airport> getAirports() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://airlabs.co/api/v9/airports?_fields=name,iata_code,icao_code&api_key=fe63f32c-47c5-466e-8e16-0a14a30d6f75"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject json = new JSONObject(response.body());
        JSONArray jsonArray = json.getJSONArray("response");

        List<Airport> airports = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Airport airport = objectMapper.readValue(jsonObject.toString(), Airport.class); //The toString method is called on each JSONObject to convert it to a JSON string that can be parsed by the readValue method.
            airports.add(airport);
        }
        return airports;
    }
}
