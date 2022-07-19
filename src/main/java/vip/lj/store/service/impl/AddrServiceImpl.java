package vip.lj.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.lj.store.mapper.AddrMapper;
import vip.lj.store.pojo.dto.AddressAddDTO;
import vip.lj.store.pojo.entity.Address;
import vip.lj.store.pojo.entity.DictDistrict;
import vip.lj.store.service.AddrService;
import vip.lj.store.util.JwtUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {
    AddrMapper mapper;
    @Autowired
    public AddrServiceImpl(AddrMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<DictDistrict> getDByParent(String parent) {
        return mapper.getDByParent(parent);
    }

    @Override
    public void updateDefault(String token, Long aid) {
        var uid = JwtUtils.parseFromBearer(token);
        mapper.setNoDefaultByUId(uid);
        mapper.setDefaultByAId(aid);
    }

    @Override
    public List<Address> getByUId(String token) {
        return mapper.getByUId(JwtUtils.parseFromBearer(token));
    }

    @Override
    public void deleteByAId(Long aid) {
        mapper.delByAId(aid);
    }

    @Override
    public void addAddress(String token, AddressAddDTO dto) {
        var info = JwtUtils.parseJwt(token);
        dto.setUid(info.getId());
        dto.setCreatedUser(info.getUsername());
        dto.setCreatedTime(LocalDateTime.now());
        mapper.addAddress(dto);
    }
}
