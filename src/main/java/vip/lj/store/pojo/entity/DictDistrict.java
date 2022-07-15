package vip.lj.store.pojo.entity;

import lombok.Data;

@Data
public class DictDistrict {
  private long id;
  private String parent;
  private String code;
  private String name;
}
