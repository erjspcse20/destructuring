package com.destruring.product.country.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.destruring.product.country.model.CountryModel;
import com.destruring.product.country.repository.CountryMasterRepository;

@RestController
@RequestMapping("cou/api")
public class CountryMasterController {
	
	
	@Autowired
	CountryMasterRepository countryMasterRepository;

	@GetMapping("/allCountry")
	public List<CountryModel> getAllNotes() {
		List<CountryModel> Countrys = new ArrayList();
		countryMasterRepository.findAll()
			.forEach(Countrys::add);
		return Countrys;
	}
	
	@RequestMapping("/country/{id}")
	public Optional<CountryModel> getCountry(@PathVariable Integer id) {
		//either you can give same name as parameter argument 
		//or put path variable name of request mapping to path
		//variable
		return countryMasterRepository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addCountry")
	public void addCountry(@RequestBody CountryModel country) {
		System.out.println("createdby"+country.getCreatedBy());
		System.out.println("active"+country.getIsActive());
		System.out.println("description"+country.getDescription());
		System.out.println("name"+country.getName());
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        Date currentdate=new Date();

        country.setUuid(randomUUIDString);
        country.setName(country.getName());
        country.setDescription(country.getDescription());
        country.setCreatedAt(currentdate);
        country.setIsDeleted(false);
        country.setIsActive(country.getIsActive());
        country.setCreatedBy(country.getCreatedBy());
		countryMasterRepository.save(country);
		
	}

	@RequestMapping(method=RequestMethod.POST, value="/updatecountry/{id}")
	public void updateCountry(Integer id, CountryModel country) {
		countryMasterRepository.save(country);
		
	}

	@RequestMapping(method=RequestMethod.POST, value="/deletecountry/{id}")
	public void deleteCountry(Integer id, CountryModel country) {
		countryMasterRepository.save(country);
	}

}
