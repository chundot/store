package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class Permission extends BaseEntity {
  private long pid;
  private String name;
  private String value;
}
