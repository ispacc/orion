package io.ispacc.orion.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.admin.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台用户表(UmsAdmin)表数据库访问层
 *
 * @author ispacc
 * @since 2023-05-20 23:44:31
 */
@Repository
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UmsAdmin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UmsAdmin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UmsAdmin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UmsAdmin> entities);

}

