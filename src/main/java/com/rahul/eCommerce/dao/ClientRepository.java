package com.rahul.eCommerce.dao;

import com.rahul.eCommerce.entities.Image;
import com.rahul.eCommerce.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Product,Integer> {
}
