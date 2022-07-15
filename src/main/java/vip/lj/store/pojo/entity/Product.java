package vip.lj.store.pojo.entity;
import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class Product extends BaseEntity {
  private long id;
  private long categoryId;
  private String itemType;
  private String title;
  private String sellPoint;
  private long price;
  private long num;
  private String image;
  private long status;
  private long priority;
}
