package com.destruring.product.country.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.destruring.product.country.model.CountryModel;

public interface CountryMasterRepository extends JpaRepository<CountryModel, Integer>{

}
