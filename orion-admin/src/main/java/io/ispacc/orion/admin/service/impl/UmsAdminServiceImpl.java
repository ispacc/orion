package io.ispacc.orion.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.dao.UmsAdminDao;
import io.ispacc.orion.admin.entity.UmsAdmin;
import io.ispacc.orion.admin.service.UmsAdminService;
import org.springframework.stereotype.Service;

/**
 * 后台用户表(UmsAdmin)表服务实现类
 *
 * @author ispacc
 * @since 2023-05-20 23:44:31
 */
@Service("umsAdminService")
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminDao, UmsAdmin> implements UmsAdminService {

}

