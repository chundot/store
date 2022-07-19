package vip.lj.store.service;

import java.util.List;

import vip.lj.store.pojo.dto.CartInfoDTO;
import vip.lj.store.pojo.dto.UserTokenDTO;
import vip.lj.store.pojo.entity.Product;

public interface CartService {

  public List<CartInfoDTO> getByUserId(Long uid);

  public List<CartInfoDTO> getByCartIds(List<Long> cids);

  public void create(Product product, UserTokenDTO info, Integer num);
}
