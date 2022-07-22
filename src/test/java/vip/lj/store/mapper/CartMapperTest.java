package vip.lj.store.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.lj.store.pojo.entity.Cart;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
public class CartMapperTest {

  @Autowired
  CartMapper cartMapper;

  @Test
  void getByCartIds() {
    List<Long> ids = new ArrayList<>();
    ids.add(1L);
    ids.add(2L);
    ids.add(3L);
    log.info("{}", cartMapper.getByCartIds(ids));
    log.info("{}", cartMapper.getByUserId(4L));

  }

  @Test
  void addCart() {
    Cart cart = new Cart();
    cart.setUid(4L);
    cart.setPid(10000003L);
    cart.setNum(1L);
    cart.setPrice(100L);
    cartMapper.addCart(cart);
    log.info("{}",cart.getCid());
  }

  @Test
  void deleteCartByIds() {
    List<Long> ids = new ArrayList<>();
    ids.add(1L);
    ids.add(2L);
    ids.add(3L);
    cartMapper.deleteCartByIds(ids);
  }

  @Test
  void updateNumById(){
    cartMapper.updateNumById(5L,5L);
  }

}
