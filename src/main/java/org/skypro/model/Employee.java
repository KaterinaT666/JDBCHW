package org.skypro.model;
import java.util.Objects;

public class Employee {

	private int id;
	private String first_name;
	private String last_name;
	private String gender;
	private int age;
	private City city;

	public Employee() {
	}

	public Employee(int id, String first_name, String last_name, String gender, int age, City city) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.age = age;
		this.city = city;
	}

	public Employee(String first_name, String last_name, String gender, int age, City city) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.age = age;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return getId() == employee.getId() && getAge() == employee.getAge() && Objects.equals(getFirst_name(), employee.getFirst_name()) && Objects.equals(getLast_name(), employee.getLast_name()) && Objects.equals(getGender(), employee.getGender()) && Objects.equals(getCity(), employee.getCity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFirst_name(), getLast_name(), getGender(), getAge(), getCity());
	}

	@Override
	public String toString() {
		return String.format("id: %d, first_name: %s, last_name: %s, gender: %s, age: %d",
				id,first_name,last_name,gender,age);
	}
}
