package vip.lj.store.pojo.entity;
import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class ProductCategory extends BaseEntity {
  private long id;
  private long parentId;
  private String name;
  private long status;
  private long sortOrder;
  private long isParent;
}
