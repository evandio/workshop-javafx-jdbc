/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.services;

import java.util.List;
import main.model.dao.DaoFactory;
import main.model.dao.SellerDao;
import main.model.entities.Seller;

/**
 *
 * @author evand
 */
public class SellerService {
    
    private SellerDao dao = DaoFactory.createSellerDao();
    
    public List<Seller> findAll() {
        return dao.findAll();
    }
    
    public void saveOrUpdatet(Seller obj) {
        if (obj.getId() == null) {
            dao.insert(obj);
        } else {
            dao.updatet(obj);
        }
    }
    
    public void remove(Seller obj) {
        dao.deleteById(obj.getId());
    }
    
}
