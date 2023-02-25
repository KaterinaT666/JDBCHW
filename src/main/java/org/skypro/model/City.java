package org.skypro.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city")

public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "city_id")
	private int cityId;
	@Column(name = "city_name",nullable = false)
	private String cityName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
	private List<Employee> employees;

	public City() {
	}

	public City(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return getCityId() == city.getCityId() && Objects.equals(getCityName(), city.getCityName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCityId(), getCityName());
	}

	@Override
	public String toString() {
		return cityId +", cityName = " + cityName;
	}
}
