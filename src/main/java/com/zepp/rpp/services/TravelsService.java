package com.zepp.rpp.services;

import com.zepp.rpp.domains.Travels;

import java.util.List;

public interface TravelsService {
    void save(Travels travels);
    List<Travels> findAll();
    Travels findOne(int travelsId);
    List<Travels> findByCountryAndPeriodNQ(String country, int lesser, int higher);
}
