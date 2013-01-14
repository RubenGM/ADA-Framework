package com.desandroid.ormlite_ada.model.entities;

import java.util.Date;

import com.desandroid.framework.ada.Entity;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "OrmLiteTable")
public class OrmLiteEntity extends Entity {
	
	@DatabaseField(columnName = "Name", dataType = DataType.STRING, canBeNull = false)
	private String name = null;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@DatabaseField(columnName = "Age", dataType = DataType.INTEGER_OBJ, canBeNull = false)
	private Integer age = null;
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }

	@DatabaseField(columnName = "BirthDay", dataType = DataType.DATE, canBeNull = false)
	private Date birthDay = null;
	public Date getBirthDay() { return birthDay; }
	public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }
}
