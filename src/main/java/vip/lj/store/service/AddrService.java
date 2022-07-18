package vip.lj.store.service;

import vip.lj.store.pojo.dto.AddressAddDTO;
import vip.lj.store.pojo.entity.Address;
import vip.lj.store.pojo.entity.DictDistrict;

import java.util.List;

public interface AddrService {
    List<DictDistrict> getDByParent(String parent);
    void updateDefault(String token, Long aid);

    List<Address> getByUId(String token);

    void deleteByAId(Long aid);

    void addAddress(String token, AddressAddDTO dto);
}
