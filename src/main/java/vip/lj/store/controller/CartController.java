package vip.lj.store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vip.lj.store.pojo.dto.CartAddDTO;

@RestController
@RequestMapping("/carts")
public class CartController {

  // 获取用户购物车列表
  // 返回 cid/image/title/realPrice/num
  @GetMapping()
  public Object get() {
    // not implemented
    return null;
  }

  // 返回 cid/image/title/realPrice/num
  // TODO where is cids?
  @GetMapping("/get_by_cids")
  public Object getByCids() {
    // not implemented
    return null;
  }

  @PostMapping("add_to_cart")
  public Object addToCart(CartAddDTO dto) {
    // not implemented
    return null;
  }

}
