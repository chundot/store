package vip.lj.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vip.lj.store.pojo.dto.OrderAddDTO;
import vip.lj.store.service.OrderService;
import vip.lj.store.util.JwtUtils;
import vip.lj.store.util.ResUtils;

@RestController
@RequestMapping("/orders")
public class OrderController {
  OrderService service;

  @Autowired
  public OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping("/create")
  public Object Create(@RequestHeader(value = "Authentication") String token, OrderAddDTO dto) {
    var id = JwtUtils.parseFromBearer(token);
    service.createOrder(id, dto);
    return ResUtils.ok();
  }
  @GetMapping
  public Object GetOrders(@RequestHeader(value = "Authentication") String token) {
    var id = JwtUtils.parseFromBearer(token);
    return ResUtils.ok(service.getOrdersByUId(id));
  }

}
