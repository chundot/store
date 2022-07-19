package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;

import vip.lj.store.pojo.entity.Product;

@Repository
public interface ProductMapper {

  public Product getById(Long id);

  public Product getHot();

}
