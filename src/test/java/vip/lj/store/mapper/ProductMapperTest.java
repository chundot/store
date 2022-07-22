package vip.lj.store.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class ProductMapperTest {
  @Autowired
  ProductMapper productMapper;

  @Test
  void getById(){
    log.info("{}",productMapper.getById(10000039L));
  }

  @Test
  void getHot(){
    log.info("{}",productMapper.getHot());

  }
}
