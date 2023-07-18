package io.ispacc.orion.admin.module.file.prop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-17 17:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "orion.file")
public class FileProperties {
    private Long configId;
}
