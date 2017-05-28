package com.caipiao.utils.http;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 *
 * @author liujie
 */
public class CloseableUtils {
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if(closeable != null)
                closeable.close();
            } catch (IOException e) {
                // 忽略此处的异常
            }
        }
    }
}
