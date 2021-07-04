/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.dao.imp;

import java.util.List;
import main.model.dao.DepartmentDao;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public class DepartmentJDBC implements DepartmentDao{

    @Override
    public void insert(Department obj) {
        
    }

    @Override
    public void updatet(Department obj) {
        
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public Department findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> findAll(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
