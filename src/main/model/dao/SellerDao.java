/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.dao;

import java.util.List;
import main.model.entities.Seller;

/**
 *
 * @author evand
 */
public interface SellerDao {
    void insert(Seller obj);

    void updatet(Seller obj);

    void deleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll(Seller obj);
}
