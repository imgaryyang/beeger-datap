package com.xishui.beeger.datap.plugin.agg;

import com.xishui.beeger.datap.plugin.agg.convert.MapToList;

public class Convert {

    public static <T,M>MapToList<T,M> mapToList(){
        return MapToList.newMapToList();
    }
}
