package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

import java.time.LocalDateTime;

@Data
public class Order extends BaseEntity {
  private long oid;
  private long uid;
  private String recvName;
  private String recvPhone;
  private String recvProvince;
  private String recvCity;
  private String recvArea;
  private String recvAddress;
  private long price;
  private LocalDateTime orderTime;
  private LocalDateTime payTime;
  private long status;
}
