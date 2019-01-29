package com.zepp.rpp.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Travels {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Travels_id")
    private int Travels_id;

    @Column(name = "Period")
    private int Period;
    @Column(name = "Passenger_type")
    private String Passenger_type;
    @Column(name = "Direction")
    private String Direction;
    @Column(name = "Country")
    private String Country;
    @Column(name = "Counter")
    private int Counter;
    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnore
    private User user;

    public int getTravels_id() {
        return Travels_id;
    }

    public void setTravels_id(int travels_id) {
        Travels_id = travels_id;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public String getPassenger_type() {
        return Passenger_type;
    }

    public void setPassenger_type(String passenger_type) {
        Passenger_type = passenger_type;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
