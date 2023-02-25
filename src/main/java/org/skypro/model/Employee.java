package org.skypro.model;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;
	@Column(name = "first_name",nullable = false)
	private String firstName;

	@Column(name = "last_name",nullable = false)
	private String lastName;
	@Column(name = "gender",nullable = false)
	private String gender;
	@Column(name = "age")
	private int age;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City city;

	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, String gender, int age, City city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.city = city;
	}

	public Employee(String firstName, String lastName, String gender, int age, City city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.city = city;
	}

	public Employee(String firstName, String lastName, String gender, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return getId() == employee.getId() && getAge() == employee.getAge() && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(lastName, employee.lastName) && Objects.equals(getGender(), employee.getGender()) && Objects.equals(getCity(), employee.getCity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFirstName(), lastName, getGender(), getAge(), getCity());
	}

	@Override
	public String toString() {
		return String.format("id: %d, first_name: %s, last_name: %s, gender: %s, age: %d",
				id,firstName,lastName,gender,age);
	}
}
