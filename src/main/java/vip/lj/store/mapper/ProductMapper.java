package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;

import vip.lj.store.pojo.entity.OrderItem;
import vip.lj.store.pojo.entity.Product;

import java.util.List;

@Repository
public interface ProductMapper {
  Product getById(Long id);

  List<Product> getHot();
  List<OrderItem> getItemsByCartIds(List<Long> cids);
}
