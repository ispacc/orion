package io.ispacc.orion.admin.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-12 08:37
 */
public class FileConstant {
    @AllArgsConstructor
    @Getter
    public enum FileInfoStatus {
        NORMAL(1, "正常"),
        IN_TRANSIT(2, "传输中");

        private final Integer status;
        private final String statusName;
    }
}
