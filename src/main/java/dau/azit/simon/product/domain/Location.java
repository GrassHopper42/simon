package dau.azit.simon.product.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    private String location;

    public Location() {}

    public Location(String location) {
        this.location = location;
    }

}
