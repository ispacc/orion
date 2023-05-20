package io.ispacc.orion.admin.service.impl;

import io.ispacc.orion.admin.mapper.UmsMemberMapper;
import io.ispacc.orion.admin.model.UmsMember;
import io.ispacc.orion.admin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public List<UmsMember> test1() {

        return umsMemberMapper.selectList(null);
    }

}
