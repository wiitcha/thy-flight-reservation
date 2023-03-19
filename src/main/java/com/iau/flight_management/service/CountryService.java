package com.iau.flight_management.service;

import org.springframework.ui.Model;

import java.io.IOException;

public interface CountryService {

    void importCountries(Model model) throws IOException, InterruptedException;
}
