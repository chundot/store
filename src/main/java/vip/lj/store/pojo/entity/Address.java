package vip.lj.store.pojo.entity;

import lombok.Data;
import vip.lj.store.pojo.entity.common.BaseEntity;

@Data
public class Address extends BaseEntity {
  private long aid;
  private long uid;
  private String receiver;
  private String provinceName;
  private String provinceCode;
  private String cityName;
  private String cityCode;
  private String areaName;
  private String areaCode;
  private String zip;
  private String address;
  private String phone;
  private String tel;
  private String tag;
  private long isDefault;
}
