package com.cmos.ha.cmms.manage.example.bean;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
public class Example {

	@NotEmpty
	private String name;
	@Range(min = 18, max = 80)
	private int age;
	@Past
	private Date birthday;
	
	public Example() {
		
	}

	public Example(String name, int age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
