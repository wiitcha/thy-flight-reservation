package com.iau.flight_management.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    private String name;
    private String city;
    private String iata_code;
    private String icao_code;
    private String country_code;
    private String lng; // Geo Longitude
    private String lat; // Geo Latitude
}
