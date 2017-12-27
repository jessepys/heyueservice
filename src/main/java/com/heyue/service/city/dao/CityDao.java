package com.heyue.service.city.dao;

import com.heyue.service.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends JpaRepository<City, Long> {

	@Query("select o from City o where  o.delStatus = false order by o.shortName asc")
    List<City> findAllCity();
	
	@Query
	List<City> findByName(String cityName);

	@Query
	List<City> findByShortName(String shortName);

	@Query("select o from City o where  o.delStatus = false and province = ?1 ")
	List<City> findAllCityByProvinceName(String name);
}