package com.magic.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    private Long id;

    private String name;

    private Integer age;

    private Date bitrthday;

    private Double salary;

    private boolean isManager;

    public User() {
    }

    public User(Long id, String name, Integer age, Date bitrthday, Double salary, boolean isManager) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bitrthday = bitrthday;
        this.salary = salary;
        this.isManager = isManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getBitrthday() {
        return bitrthday;
    }

    public void setBitrthday(Date bitrthday) {
        this.bitrthday = bitrthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", bitrthday=").append(bitrthday);
        sb.append(", salary=").append(salary);
        sb.append(", isManager=").append(isManager);
        sb.append('}');
        return sb.toString();
    }
}
