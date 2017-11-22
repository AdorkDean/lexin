//    LotSale.java

package com.caipiao.utils;

import com.caipiao.entity.Bc_lotsale;
import com.caipiao.intface.Bc_lotsaleIntface;
import com.caipiao.intfaceImpl.LotsaleIntfaceImpl;

import java.util.HashMap;
import java.util.List;

public class LotSale {

    static Bc_lotsaleIntface dao = new LotsaleIntfaceImpl();
    static HashMap map = new HashMap();

    public LotSale() {
    }

    public static int getLotSale(String name) {
        int result = 1;
        if (map.containsKey(name)) {
            result = ((Integer) map.get(name)).intValue();
        } else {
            Bc_lotsale find = dao.find(name);
            if (find != null) {
                result = find.getLotsale_status();
                map.put(name, Integer.valueOf(result));
            }
        }
        return result;
    }

    public static boolean UpdateSale(String name, int status) {
        boolean update = dao.update(name, status);
        if (update) {
            map.remove(name);
            map.put(name, Integer.valueOf(status));
        }
        return update;
    }

    public static List finds() {
        return dao.finds();
    }

}
