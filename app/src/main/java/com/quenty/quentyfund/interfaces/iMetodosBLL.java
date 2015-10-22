package com.quenty.quentyfund.interfaces;

import java.util.List;

/**
 * Created by DavorLimachi on 10/21/15.
 */
public interface iMetodosBLL {
     long insert(Object o);
     long update(Object o);
     long delete(Object o);
     List<Object> lsObjects();
}
