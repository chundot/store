package vip.lj.store.controller.common;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vip.lj.store.pojo.dto.OrderAddDTO;

@RestController
@RequestMapping("/order")
public class OrderController {

  @PostMapping("/create")
  public Object Create(OrderAddDTO dto) {
    // not implemented
    return null;
  }

}
