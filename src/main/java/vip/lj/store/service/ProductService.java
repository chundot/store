package vip.lj.store.service;

import vip.lj.store.pojo.entity.Product;

import java.util.List;

public interface ProductService {

  Product getById(Long id);

  List<Product> getHot();

}
