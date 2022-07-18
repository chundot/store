package vip.lj.store.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressAddDTO {
    Long uid;
    String receiver;
    String provinceName, provinceCode, cityName, cityCode, areaName, areaCode;
    String zip, address;
    String phone, tel;
    String tag;
    Long isDefault;
    String createdUser;
    LocalDateTime createdTime;
}
