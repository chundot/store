package vip.lj.store.pojo.vo;

import lombok.Data;
import vip.lj.store.pojo.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVO {
  Long oid, status, price;
  String recvName;
  LocalDateTime orderTime;
  List<OrderItem> items;
}
