package com.example.disertatie_v4.models.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Embeddable
public class Address {

    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "must be alphanumerical")
    private String street;

    @Pattern(regexp = "^[0-9]{6}$")
    private String postcode;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String city;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String country;

    public Address() {
    }

    public Address(String street, String postcode, String city, String country) {
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street)
                && Objects.equals(postcode, address.postcode)
                && Objects.equals(city, address.city)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postcode, city, country);
    }

    @Override
    public String toString() {
        return new StringBuilder("Address{").append("street=")
                .append(street)
                .append(", postcode=")
                .append(postcode)
                .append(", city=")
                .append(city)
                .append(", country=")
                .append(country)
                .append("}").toString();
    }
}
