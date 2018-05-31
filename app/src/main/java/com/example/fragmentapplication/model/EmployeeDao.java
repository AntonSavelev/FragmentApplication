package com.example.fragmentapplication.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    List<Employee> getAll();

    @Query("SELECT * FROM employee WHERE id = :id")
    Employee getById(int id);

    @Insert
    void insert(Employee employee);

    @Insert
    void insert(List<Employee> employees);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);
}
