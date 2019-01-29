package com.zepp.rpp.services;

import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.exceptions.NotFoundException;
import com.zepp.rpp.repositories.TravelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TravelsServiceImpl implements TravelsService{
    private final TravelsRepository travelsRepository;

    @Autowired
    public TravelsServiceImpl(TravelsRepository travelsRepository) {
        this.travelsRepository = travelsRepository;
    }

    @Override
    public void save(Travels travels) {
        log.debug("dodata je nova statistika!!!");
        travelsRepository.save(travels);
    }

    @Override
    public List<Travels> findAll() {
        log.debug("Travels Service findAll method!!!");
        List<Travels> travelsList = (List<Travels>) travelsRepository.findAll();
        return travelsList;
    }

    @Override
    public Travels findOne(int travelsId) {
        Optional<Travels> travels = travelsRepository.findById(travelsId);
        if(!travels.isPresent()){
            log.debug("Nije pronađena statistika sa id-jem " +travelsId);
            throw new NotFoundException("Nije pronađeno putovanje sa Id-ijem: " + travelsId);
        }
        return travels.get();
    }

    @Override
    public List<Travels> findByCountryAndPeriodNQ(String country, int lesser, int higher) {
        List<Travels> list = travelsRepository.findByCountryAndPeriodNQ(country, lesser, higher);
        return list;
    }


}
