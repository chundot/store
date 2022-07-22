package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;
import vip.lj.store.pojo.entity.Order;
import vip.lj.store.pojo.entity.OrderItem;

import java.util.List;

@Repository
public interface OrderMapper {
  void createOrder(Order order);
  void creatOrderItems(List<OrderItem> items);
}
