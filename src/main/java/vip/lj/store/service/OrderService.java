package vip.lj.store.service;

import vip.lj.store.pojo.dto.OrderAddDTO;

public interface OrderService {
  void createOrder(Long uid, OrderAddDTO dto);
}
