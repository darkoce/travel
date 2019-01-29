package com.zepp.rpp.repositories;

import com.zepp.rpp.domains.Travels;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelsRepository extends CrudRepository<Travels, Integer>{
    @Query(value = "SELECT * FROM travels t WHERE t.Country=:country AND t.Period>:lesser AND t.Period<:higher ", nativeQuery = true)
    List<Travels> findByCountryAndPeriodNQ(@Param("country") String country, @Param("lesser") int lesser, @Param("higher") int higher);
}
