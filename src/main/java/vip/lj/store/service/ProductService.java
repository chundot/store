package vip.lj.store.service;

import vip.lj.store.pojo.entity.Product;

public interface ProductService {

  public Product getById(Long id);

  public Product getHot();

}
