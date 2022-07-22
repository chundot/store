package vip.lj.store.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.lj.store.mapper.CartMapper;
import vip.lj.store.pojo.dto.CartInfoDTO;
import vip.lj.store.pojo.dto.CartModDTO;
import vip.lj.store.pojo.dto.UserTokenDTO;
import vip.lj.store.pojo.entity.Cart;
import vip.lj.store.pojo.entity.Product;
import vip.lj.store.service.CartService;

@Service
public class CartServiceImpl implements CartService {
  CartMapper mapper;

  @Autowired
  public CartServiceImpl(CartMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public List<CartInfoDTO> getByUserId(Long uid) {
    return mapper.getByUserId(uid);
  }

  @Override
  public List<CartInfoDTO> getByCartIds(List<Long> cids) {
    return mapper.getByCartIds(cids);
  }

  @Override
  public void create(Product product, UserTokenDTO info, Integer num) {
    var cart = new Cart();
    cart.setUid(info.getId());
    cart.setPid(product.getId());
    cart.setNum(num);
    cart.setPrice(product.getPrice());
    cart.setCreatedUser(info.getUsername());
    cart.setCreatedTime(LocalDateTime.now());
    mapper.addCart(cart);
  }

  @Override
  public void delete(Long uid, List<Long> cids) {
    mapper.delByCIds(cids);
  }

  @Override
  public void modByDTO(CartModDTO dto) {
    mapper.modById(dto);
  }

}
