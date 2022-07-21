package vip.lj.store.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import vip.lj.store.pojo.dto.CartInfoDTO;
import vip.lj.store.pojo.entity.Cart;

@Repository
public interface CartMapper {

  List<CartInfoDTO> getByUserId(Long uid);

  List<CartInfoDTO> getByCartIds(List<Long> cids);

  void addCart(Cart cart);
}
