package org.skypro.model;

import java.util.Objects;

public class City {

	private int cityId;
	private String cityName;


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
		return cityName +
				"c id = " + cityId;
	}
}
