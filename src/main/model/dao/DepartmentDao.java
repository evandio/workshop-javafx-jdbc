/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.dao;

import java.util.List;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public interface DepartmentDao {

    void insert(Department obj);

    void updatet(Department obj);

    void deleteById(Integer id);

    Department findById(Integer id);

    List<Department> findAll(Department obj);

}
