package com.iau.flight_management.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class PublicController {

    @GetMapping(value = {"/", "/login"})
    public String viewLoginPage() {
        return "login/loginPage";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://airlabs.co/api/v9/countries?api_key=fe63f32c-47c5-466e-8e16-0a14a30d6f75"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        JSONArray jsonArray = json.getJSONArray("response");

        List<String> countries = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            countries.add(name);
        }

        model.addAttribute("countries", countries);

        return "/login/register";
    }
}
