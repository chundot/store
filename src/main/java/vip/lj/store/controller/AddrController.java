package vip.lj.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.lj.store.pojo.dto.AddressAddDTO;
import vip.lj.store.service.AddrService;
import vip.lj.store.util.ResUtils;

@Slf4j
@RestController
public class AddrController {
    AddrService service;

    @Autowired
    public AddrController(AddrService service) {
        this.service = service;
    }

    @GetMapping("/districts")
    public Object getDistricts(String parent) {
        return ResUtils.ok(service.getDByParent(parent));
    }

    @GetMapping("/addresses")
    public Object getAddr(@RequestHeader(value = "Authentication") String token) {
        return ResUtils.ok(service.getByUId(token));
    }

    @PostMapping("/addresses/{aid}/set_default")
    public Object setDefaultAddr(@RequestHeader(value = "Authentication") String token, @PathVariable Long aid) {
        service.updateDefault(token, aid);
        return ResUtils.ok();
    }
    @PostMapping("/addresses/{aid}/delete")
    public Object deleteAddress(@RequestHeader(value = "Authentication") String token, @PathVariable Long aid) {
        service.deleteByAId(aid);
        return ResUtils.ok();
    }
    @PostMapping("/addresses/addnew")
    public Object addAddress(@RequestHeader(value = "Authentication") String token, AddressAddDTO dto) {
        service.addAddress(token, dto);
        return ResUtils.ok();
    }
}
