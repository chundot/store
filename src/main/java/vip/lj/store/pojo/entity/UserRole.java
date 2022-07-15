package vip.lj.store.pojo.entity;
import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class UserRole extends BaseEntity {
  private long id;
  private long uid;
  private long rid;
}
