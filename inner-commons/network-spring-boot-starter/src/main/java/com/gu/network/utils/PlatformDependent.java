/**
 * 代码贡献者：
 * lzc - 初始创建，2019-06-24
 */
package com.gu.network.utils;

import org.apache.commons.lang3.SystemUtils;

/**
 * 获取平台信息的相关工具
 */
public class PlatformDependent {

    /**
     *
     */
    public enum Platform {
        LINUX, BSD, WINDOWS
    }

    /**
     * 获取系统运行的操作系统
     *
     * @return
     */
    public static Platform getPlatform() {
        if (SystemUtils.IS_OS_LINUX) {
            return Platform.LINUX;
        }
        // maosx 是bsd的内核，直接判断为bsd
        if (SystemUtils.IS_OS_FREE_BSD || SystemUtils.IS_OS_NET_BSD || SystemUtils.IS_OS_OPEN_BSD
                || SystemUtils.IS_OS_MAC_OSX) {
            return Platform.BSD;
        }
        return Platform.WINDOWS;
    }

    /**
     * 获取系统的可用逻辑核数
     *
     * @return
     */
    public static int availableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
