package vip.lj.store.service;

import java.util.List;

import vip.lj.store.pojo.dto.CartInfoDTO;
import vip.lj.store.pojo.dto.UserTokenDTO;
import vip.lj.store.pojo.entity.Product;

public interface CartService {

  List<CartInfoDTO> getByUserId(Long uid);

  List<CartInfoDTO> getByCartIds(List<Long> cids);

  void create(Product product, UserTokenDTO info, Integer num);

  void deleteCartByIds(List<Long> cids);

  void updateNumById(Long cid,Long num);
}
