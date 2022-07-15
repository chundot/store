package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class Role extends BaseEntity {
  private long rid;
  private String name;
}
