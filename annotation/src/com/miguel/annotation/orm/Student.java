package com.miguel.annotation.orm;

import com.miguel.annotation.orm.annotation.Field;
import com.miguel.annotation.orm.annotation.Table;

@Table("tb_student")
public class Student {

    @Field(columnName = "stu_id", type = "int", length = 10)
    private Integer id;
    @Field(columnName = "stu_name", type = "varchar", length = 10)
    private String name;
    @Field(columnName = "stu_age", type = "int", length = 3)
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
