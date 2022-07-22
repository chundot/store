package vip.lj.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vip.lj.store.pojo.dto.CartAddDTO;
import vip.lj.store.service.CartService;
import vip.lj.store.service.ProductService;
import vip.lj.store.util.JwtUtils;
import vip.lj.store.util.ResUtils;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
  CartService cartService;
  ProductService productService;

  @Autowired
  public CartController(CartService cartService, ProductService productService) {
    this.cartService = cartService;
    this.productService = productService;
  }

  // 获取用户购物车列表
  // 返回 cid/image/title/realPrice/num
  @GetMapping()
  public Object get(@RequestHeader(value = "Authentication") String token) {
    var info = JwtUtils.parseJwt(token);
    return ResUtils.ok(cartService.getByUserId(info.getId()));
  }

  // 返回 cid/image/title/realPrice/num
  // TODO where is cids?
  @GetMapping("/get_by_cids")
  public Object getByCids() {
    // not implemented
    return null;
  }

  @PostMapping("/add_to_cart")
  public Object addToCart(@RequestHeader(value = "Authentication") String token, CartAddDTO dto) {

    var info = JwtUtils.parseJwt(token);
    var product = productService.getById(dto.getPid());
    cartService.create(product, info, dto.getAmount());

    return ResUtils.ok();
  }

  @PostMapping("/remove_by_ids")
  public Object removeCartByIds(List<Long> ids){
    cartService.deleteCartByIds(ids);
    return ResUtils.ok();
  }

  @PostMapping("/update_num_by_id")
  public Object updateNumById(Long cid,Long num){
    cartService.updateNumById(cid,num);
    return ResUtils.ok();
  }
}
