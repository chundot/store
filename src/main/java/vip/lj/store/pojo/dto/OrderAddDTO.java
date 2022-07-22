package vip.lj.store.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderAddDTO {
  Long aid;
  List<Long> cids;
}
