package vip.lj.store.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.lj.store.mapper.*;
import vip.lj.store.pojo.dto.OrderAddDTO;
import vip.lj.store.pojo.entity.Order;
import vip.lj.store.pojo.vo.OrderVO;
import vip.lj.store.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
  CartMapper cartMapper;
  UserMapper userMapper;
  OrderMapper orderMapper;
  ProductMapper productMapper;
  AddrMapper addrMapper;

  @Autowired
  public OrderServiceImpl(OrderMapper orderMapper, UserMapper userMapper, ProductMapper productMapper, AddrMapper addrMapper, CartMapper cartMapper) {
    this.orderMapper = orderMapper;
    this.userMapper = userMapper;
    this.productMapper = productMapper;
    this.addrMapper = addrMapper;
    this.cartMapper = cartMapper;
  }

  @Override
  public void createOrder(Long uid, OrderAddDTO dto) {
    var username = userMapper.getById(uid).getUsername();
    var addr = addrMapper.getByAId(dto.getAid());
    // 订单物品
    var items = productMapper.getItemsByCartIds(dto.getCids());
    var price = 0;
    for (var item : items) {
      item.setCreatedUser(username);
      item.setCreatedTime(LocalDateTime.now());
      price += item.getNum() * item.getPrice();
    }
    // 订单
    var order = new Order();
    order.setUid(uid);
    order.setRecvName(addr.getReceiver());
    order.setRecvPhone(addr.getPhone());
    order.setRecvAddress(addr.getAddress());
    order.setRecvProvince(addr.getProvinceName());
    order.setRecvCity(addr.getCityName());
    order.setRecvArea(addr.getAreaName());
    order.setPrice(price);
    order.setOrderTime(LocalDateTime.now());
    order.setCreatedUser(username);
    order.setCreatedTime(LocalDateTime.now());
    order.setStatus(0);
    // 插入数据
    orderMapper.createOrder(order);
    var oid = order.getOid();
    log.info("{}", order);
    for (var item : items) {
      item.setOid(oid);
    }
    orderMapper.creatOrderItems(items);
    // 删除数据
    cartMapper.delByCIds(dto.getCids());
    order.setCreatedUser(username);
  }

  @Override
  public List<OrderVO> getOrdersByUId(Long id) {
    return orderMapper.getOrderById(id);
  }
}
