package com.davada.domain.common.util;

import org.apache.commons.lang3.StringUtils;

public class AES256UtilHolder {

    private static ThreadLocal<AES256Util> instance = ThreadLocal.withInitial(AES256Util::new);

    public static synchronized AES256Util getInstance() {
        return instance.get();
    }

    public static String encrypt(String str) {
        //
        return instance.get().encrypt(str);
    }

    public static String decrypt(String str) {
        //
        if (StringUtils.isNotBlank(str)) {
            return instance.get().decrypt(str);
        } else {
            return StringUtils.EMPTY;
        }
    }
}
