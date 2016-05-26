package com.momo.momocsdn.threadutil.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singlton {
    private static Map<Object, Object> all = new ConcurrentHashMap(32);

    public Singlton() {
    }

    public static <T> T getInstance(Class<T> cls) {
        return (T) getInstance(cls, cls, (InsanceFactory) null);
    }

    public static <T> T getInstance(Class<T> superCls, Class<? extends T> objCls) {
        return (T) getInstance(superCls, objCls, (InsanceFactory) null);
    }

    public static <T> T getInstance(Class<T> objCls, InsanceFactory<T> instanceFactory) {
        return getInstance(objCls, objCls, instanceFactory);
    }

    public static <T> T getInstance(Class<T> superCls, Class<? extends T> objCls, InsanceFactory<T> instanceFactory) {
        Object obj = all.get(superCls);
        if (obj == null) {
            synchronized (superCls) {
                obj = all.get(superCls);
                if (obj == null) {
                    try {
                        if (instanceFactory != null) {
                            obj = instanceFactory.createInstance();
                        } else {
                            obj = objCls.newInstance();
                        }

                        all.put(superCls, obj);
                    } catch (Exception var6) {
                    }
                }
            }
        }

        return (T) obj;
    }

    public static synchronized <T> void setInstance(T obj) {
        assert obj != null;

        if (obj != null) {
            all.put(obj.getClass(), obj);
        }

    }

    public static synchronized <T> void removeInstance(Class<T> cls) {
        all.remove(cls);
    }

    public static synchronized <T> void setInstance(Class<? super T> superCls, T obj) {
        if (superCls != null && obj != null) {
            all.put(superCls, obj);
        } else {
            throw new AssertionError();
        }
    }

    public interface InsanceFactory<T> {
        T createInstance();
    }
}
