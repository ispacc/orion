package io.ispacc.orion.admin.service.impl;

import io.ispacc.orion.admin.dao.UmsAdminDao;
import io.ispacc.orion.admin.entity.UmsAdmin;
import io.ispacc.orion.admin.mapper.UmsMemberMapper;
import io.ispacc.orion.admin.model.UmsMember;
import io.ispacc.orion.admin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private UmsAdminDao umsAdminDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<UmsMember> test1() {

        return umsMemberMapper.selectList(null);
    }

    @Override
    public List<UmsAdmin> test2() {
        List<UmsAdmin> umsAdmins = umsAdminDao.selectList(null);
        redisTemplate.opsForValue().set("list", umsAdmins);
        return umsAdmins;
    }

}
