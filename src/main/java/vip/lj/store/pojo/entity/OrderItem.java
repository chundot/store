package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class OrderItem extends BaseEntity {
  private long id;
  private long oid;
  private long pid;
  private String title;
  private String image;
  private long price;
  private long num;
}
