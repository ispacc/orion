package io.ispacc.orion.example.system;

import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

        log.info("{}", SystemUtil.getHostInfo());
        log.info("{}", SystemUtil.getJvmSpecInfo());
        log.info("{}", SystemUtil.getJvmInfo());
        log.info("{}", SystemUtil.getJavaSpecInfo());
        log.info("{}", SystemUtil.getJavaInfo());
        log.info("{}", SystemUtil.getOsInfo());
        log.info("{}", SystemUtil.getUserInfo());
        log.info("{}", SystemUtil.getRuntimeInfo());


    }
}
