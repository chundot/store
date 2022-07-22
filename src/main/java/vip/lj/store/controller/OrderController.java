package vip.lj.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
