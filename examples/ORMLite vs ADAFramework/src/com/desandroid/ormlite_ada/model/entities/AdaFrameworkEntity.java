package com.desandroid.ormlite_ada.model.entities;

import java.util.Date;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "AdaFrameworkTable")
public class AdaFrameworkEntity extends Entity {
	
	@TableField(name = "Name", datatype = DATATYPE_STRING, required = true)
	private String name = null;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@TableField(name = "Age", datatype = DATATYPE_INTEGER, required = true)
	private Integer age = null;
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }

	@TableField(name = "BirthDay", datatype = DATATYPE_DATE, required = true)
	private Date birthDay = null;
	public Date getBirthDay() { return birthDay; }
	public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }
}
