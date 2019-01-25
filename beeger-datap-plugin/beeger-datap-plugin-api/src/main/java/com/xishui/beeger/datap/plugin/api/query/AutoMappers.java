package com.xishui.beeger.datap.plugin.api.query;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.plugin.api.annotation.ValueKey;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AutoMappers {
    private final Logger logger = LoggerFactory.getLogger(AutoMappers.class);

    public static AutoMappers newAuto() {
        return new AutoMappers();
    }

    public <T> T mapper(Map map, Class<T> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        if (null == fields || fields.length <= 0) {
            return null;
        }
        try {
            T obj = (T) clazz.newInstance();
            //new Instance
            for (final Field field : fields) {
                ValueKey valueKey = field.getAnnotation(ValueKey.class);
                if (null == valueKey || null == valueKey.key() || "".equals(valueKey.key())) {
                    continue;
                }
                if (!map.containsKey(valueKey.key())) {
                    continue;
                }
                Method method = null;
                try {
                    method = clazz.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1), field.getType());
                    if (null == method) {
                        continue;
                    }
                    if (!Modifier.isPublic(method.getModifiers()) || Modifier.isAbstract(method.getModifiers()) ||
                            Modifier.isStatic(method.getModifiers()) || Modifier.isFinal(method.getModifiers())) {
                        continue;
                    }
                    method.setAccessible(true);
                    //setValue
                    //date 特殊处理
                    if ("java.util.Date".equals(field.getType().getCanonicalName())) {
                        method.invoke(obj, new Date((Long) map.get(valueKey.key())));
                    } else {
                        method.invoke(obj, map.get(valueKey.key()));
                    }
                } catch (Exception e) {
                    StringBuilder builder = new StringBuilder();
                    if (null != method) {
                        builder.append("Method:").append(method.getName())
                                .append(" Type:").append(field.getType())
                                .append(" value:").append(map.get(valueKey.key()));
                    }
                    logger.error("AutoMappers Method Err,info:" + builder.toString(), e);
                }
            }
            return obj;
        } catch (Exception e) {
            logger.error("AutoMappers Err", e);
        }
        return null;
    }

    public static void main(String... args) {
        Map map = new HashMap();
        map.put("str_value", "str");
        map.put("int_value", 53);
        map.put("double_value", 33.22);
        map.put("float_value", 33.44f);
        map.put("bigdecimal_value", new BigDecimal(22.11));
        map.put("i_value", 12);
        map.put("d_value", 12.33);
        map.put("f_value", 12.32f);
        map.put("date_value", 1529510400000l);
        map.put("b_value", true);
        TestClazz testClazz = AutoMappers.newAuto().mapper(map, TestClazz.class);
        System.out.println(JSON.toJSONString(testClazz));
    }

    @Data
    public static class TestClazz {
        @ValueKey(key = "str_value")
        private String strValue;
        @ValueKey(key = "int_value")
        private Integer intValue;
        @ValueKey(key = "double_value")
        private Double doubValue;
        @ValueKey(key = "float_value")
        private Float flotValue;
        @ValueKey(key = "bigdecimal_value")
        private BigDecimal bigValue;
        @ValueKey(key = "i_value")
        private int iValue;
        @ValueKey(key = "d_value")
        private double dValue;
        @ValueKey(key = "f_value")
        private float fValue;
        @ValueKey(key = "date_value")
        private Date dateValue;
        @ValueKey(key = "b_value")
        private boolean bValue;
    }
}
