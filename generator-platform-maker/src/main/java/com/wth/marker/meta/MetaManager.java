package com.wth.marker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @Author: wth
 * @Create: 2024/2/29 - 22:40
 */
public class MetaManager {

    /**
     * volatile 保证内存可见性
     */
    public static volatile Meta meta;

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }


    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta metaInfo = JSONUtil.toBean(metaJson, Meta.class);
        System.out.println("metaInfo = " + metaInfo);
        // 校验和填充meta信息
        MetaValidator.doValidAndFill(metaInfo);
        return metaInfo;
    }
}
