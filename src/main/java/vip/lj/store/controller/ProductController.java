package vip.lj.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vip.lj.store.service.ProductService;
import vip.lj.store.util.ResUtils;

@RestController
@RequestMapping("/products")
public class ProductController {

  ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  //
  // 获取销商品列表
  // 返回 id/title/price/image
  //
  @GetMapping("/hot")
  public Object getHot() {
    return ResUtils.ok(productService.getHot());
  }

  //
  // 获取指定商品详情
  // 返回 title/sellPoint/price/image
  //
  @GetMapping("/{id}/details")
  public Object getDetail(@PathVariable("id") Long id) {
    return ResUtils.ok(productService.getById(id));
  }

}