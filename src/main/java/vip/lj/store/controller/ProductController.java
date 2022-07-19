package vip.lj.store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  //
  // 获取销商品列表
  // 返回 id/title/price/image
  //
  @GetMapping("/hot")
  public Object getHot() {
    // not implemented
    return null;
  }

  //
  // 获取指定商品详情
  // 返回 title/sellPoint/price/image
  //
  @GetMapping("/{id}/detail")
  public Object getDetail(@PathVariable("id") Long id) {
    // not implemented
    return null;
  }

}