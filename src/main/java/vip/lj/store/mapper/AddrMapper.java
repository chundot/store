package vip.lj.store.mapper;

import org.springframework.stereotype.Repository;
import vip.lj.store.pojo.dto.AddressAddDTO;
import vip.lj.store.pojo.entity.Address;
import vip.lj.store.pojo.entity.DictDistrict;

import java.util.List;

@Repository
public interface AddrMapper {
    List<DictDistrict> getDByParent(String parent);
    void setNoDefaultByUId(Long uid);
    void setDefaultByAId(Long aid);
    void delByAId(Long aid);
    Address getByAId(Long aid);
    List<Address> getByUId(Long uid);
    void addAddress(AddressAddDTO dto);
}
