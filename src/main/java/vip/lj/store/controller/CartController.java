package vip.lj.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import vip.lj.store.pojo.dto.CartAddDTO;
import vip.lj.store.pojo.dto.OrderAddDTO;
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
  @GetMapping("/get_by_cids")
  public Object getByCids(@RequestParam List<Long> cids) {
    if (cids.size() == 0)
      return ResUtils.br("参数为空");
    return ResUtils.ok(cartService.getByCartIds(cids));
  }

  @PostMapping("add_to_cart")
  public Object addToCart(@RequestHeader(value = "Authentication") String token, CartAddDTO dto) {

    var info = JwtUtils.parseJwt(token);
    var product = productService.getById(dto.getPid());
    cartService.create(product, info, dto.getAmount());

    return ResUtils.ok();
  }

  @PostMapping("del")
  public Object deleteFromCart(@RequestHeader(value = "Authentication") String token, OrderAddDTO dto) {
    var uid = JwtUtils.parseFromBearer(token);
    cartService.delete(uid, dto.getCids());
    return ResUtils.ok();
  }
}
