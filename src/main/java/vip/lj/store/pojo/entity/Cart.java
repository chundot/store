package vip.lj.store.pojo.entity;


import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class Cart extends BaseEntity {
  private long cid;
  private long uid;
  private long pid;
  private long num;
  private long price;
}
