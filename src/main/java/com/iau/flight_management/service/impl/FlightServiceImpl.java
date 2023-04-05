package com.iau.flight_management.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.entity.Flight;
import com.iau.flight_management.repository.FlightRepository;
import com.iau.flight_management.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private static final  String THY_IATA = "TK";
    private static final  String THY_ICAO = "THY";

    @Override
    public HashMap<String, String> extractSearchParameters(MultiValueMap<String, String> formData, Model model) throws IOException, InterruptedException, ParseException {
        HashMap<String, String> searchParameters = new HashMap<>();

        searchParameters.put("flightType", formData.getFirst("flight-type"));
        searchParameters.put("departureAirport", formData.getFirst("departure-airport"));
        searchParameters.put("departureCity", formData.getFirst("departure-airport-codes").split(",")[0]);
        searchParameters.put("departureAirportIataCode", formData.getFirst("departure-airport-codes").split(",")[1]);
        searchParameters.put("departureAirportIcaoCode", formData.getFirst("departure-airport-codes").split(",")[2]);
        searchParameters.put("arrivalAirport", formData.getFirst("arrival-airport"));
        searchParameters.put("arrivalCity", formData.getFirst("arrival-airport-codes").split(",")[0]);
        searchParameters.put("arrivalAirportIataCode", formData.getFirst("arrival-airport-codes").split(",")[1]);
        searchParameters.put("arrivalAirportIcaoCode", formData.getFirst("arrival-airport-codes").split(",")[2]);
        searchParameters.put("airlineIataCode", THY_IATA);
        searchParameters.put("airlineIcaoCode", THY_ICAO);
        searchParameters.put("departureDate", formatDate(formData.getFirst("departure-date")));
        if (formData.getFirst("flight-type").equals("round-trip") ) {
            searchParameters.put("returnDate", formatDate(formData.getFirst("return-date")));
        }
        searchParameters.put("passengers", formData.getFirst("passengers"));

        model.addAttribute("flights", getSearchedFlights(searchParameters));
        return searchParameters;
    }

    @Override
    public String generateFlightSearchAPIToken(HashMap<String, String> parameters) {
        return String.format(
                "https://airlabs.co/api/v9/schedules?dep_iata=%s&dep_icao=%s&arr_iata=%s&arr_icao=%s&airline_icao=%s&airline_iata=%s&api_key=c7e5b46c-cab6-4b7c-985e-eef74ed6caf4" +
                        "&_fields=flight_iata,dep_iata,dep_time,arr_iata,arr_time,duration,aircraft_icao",
                parameters.get("departureAirportIataCode"),
                parameters.get("departureAirportIcaoCode"),
                parameters.get("arrivalAirportIataCode"),
                parameters.get("arrivalAirportIcaoCode"),
                parameters.get("airlineIcaoCode"),
                parameters.get("airlineIataCode"));
    }

    @Override
    public List<FlightDTO> getSearchedFlights(HashMap<String, String> parameters) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(generateFlightSearchAPIToken(parameters)))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject json = new JSONObject(response.body());
        JSONArray jsonArray = json.getJSONArray("response");

        List<FlightDTO> flights = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            FlightDTO flight = objectMapper.readValue(jsonObject.toString(), FlightDTO.class);

            int price = Integer.parseInt(flight.getDuration())  * 13;
            flight.setPrice(price);
            flight.setId((long) (i + 1));
            flight.setDuration(convertDurationToHours(Integer.parseInt(flight.getDuration())));
            flights.add(flight);
        }
        return flights;
    }

    @Override
    public String convertDurationToHours(int duration) {
        int hours = duration / 60;
        int minutes = (duration - (hours * 60)) / 5;
        return String.format("%sh %sm", hours, minutes*5);
    }

    @Override
    public List<Flight> bookFlights(List<FlightDTO> flightDTOS) {
        List<Flight> flightList = new ArrayList<>();

        for (FlightDTO flightDTO : flightDTOS) {
            Flight flight = Flight.builder()
                    .flightIataCode(flightDTO.getFlightIataCode())
                    .departureAirport(flightDTO.getDepartureAirport())
                    .departureAirportIataCode(flightDTO.getDepartureAirportIataCode())
                    .departureCity(flightDTO.getDepartureCity())
                    .departureTime(flightDTO.getDepartureTime())
                    .arrivalAirport(flightDTO.getArrivalAirport())
                    .arrivalAirportIataCode(flightDTO.getArrivalAirportIataCode())
                    .arrivalCity(flightDTO.getArrivalCity())
                    .arrivalTime(flightDTO.getArrivalTime())
                    .duration(flightDTO.getDuration())
                    .price(flightDTO.getPrice())
                    .date(flightDTO.getDate())
                    .build();

            flightRepository.save(flight);
            flightList.add(flight);
        }
        return flightList;
    }

    @Override
    public String formatDate(String htmlDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = dateFormat.parse(htmlDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, MMMM d", Locale.ENGLISH);

        return outputFormat.format(date);
    }
}
