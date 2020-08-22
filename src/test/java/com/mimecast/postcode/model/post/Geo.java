package com.mimecast.postcode.model.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geo {

    private List<Geolocation> geolocations;

    public List<Geolocation> getGeolocations() {
        return geolocations;
    }

    public void setGeolocations(List<Geolocation> geolocations) {
        this.geolocations = geolocations;
    }
}
