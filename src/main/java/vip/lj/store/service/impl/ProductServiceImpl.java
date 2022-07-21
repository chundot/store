package vip.lj.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.lj.store.mapper.ProductMapper;
import vip.lj.store.pojo.entity.Product;
import vip.lj.store.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductMapper mapper;
  @Autowired
  public ProductServiceImpl(ProductMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Product getById(Long id) {
    return mapper.getById(id);
  }

  @Override
  public List<Product> getHot() {
    return mapper.getHot();
  }

}
