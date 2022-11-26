package com.example.simple_crud.dtos;

import com.example.simple_crud.models.Company;
import com.example.simple_crud.models.embeddables.Address;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CompanyDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Valid
    private Address address;

    public CompanyDto() {}

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company of() {
        return of(new Company());
    }
    public Company of(Company company) {
        company.setId(id);
        company.setAddress(address);
        company.setName(name);
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDto that = (CompanyDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CompanyDto{name='" + name + "'" + (id != null ? ", id=" + id : ", id=null");
    }
}
