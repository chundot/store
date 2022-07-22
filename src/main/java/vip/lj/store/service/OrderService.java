package vip.lj.store.service;

import vip.lj.store.pojo.dto.OrderAddDTO;
import vip.lj.store.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {
  void createOrder(Long uid, OrderAddDTO dto);
  List<OrderVO> getOrdersByUId(Long id);
}
