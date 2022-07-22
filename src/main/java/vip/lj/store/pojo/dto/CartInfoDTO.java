package vip.lj.store.pojo.dto;

import lombok.Data;

@Data
public class CartInfoDTO {
  private long cid;
  private String image;
  private String title;
  private long realPrice;
  private long num;
}
