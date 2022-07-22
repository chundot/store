package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;
import vip.lj.store.pojo.entity.Order;
import vip.lj.store.pojo.entity.OrderItem;
import vip.lj.store.pojo.vo.OrderVO;

import java.util.List;

@Repository
public interface OrderMapper {
  Long createOrder(Order order);

  void creatOrderItems(List<OrderItem> items);

  List<OrderVO> getOrderById(Long id);
}
