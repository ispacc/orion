package io.ispacc.orion.admin.module.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.admin.module.file.entity.FileUploadInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件上传 总
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:17
 */
@Repository
public interface FileUploadInfoMapper extends BaseMapper<FileUploadInfo> {

    @Select("select fui.* from file_upload_info fui left join file_info fi on fui.file_info_id=fi.id where fi.create_user=#{userId} and fi.status=2")
    List<FileUploadInfo> getByIdUserId(@Param("userId") Long userId);
}
