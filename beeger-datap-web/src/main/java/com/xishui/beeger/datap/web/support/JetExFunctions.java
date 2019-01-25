package com.xishui.beeger.datap.web.support;


import jetbrick.template.JetAnnotations;
import jetbrick.template.runtime.InterpretContext;
import jetbrick.template.web.JetWebContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Jetx的扩展函数
 */
@JetAnnotations.Functions
public class JetExFunctions {

    /**
     * @param text
     * @param url
     * @return
     */
    public static String link(String text, String url) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }

    /**
     * @param contextPath
     * @return
     */
    public static String url(String contextPath) {
        InterpretContext ctx = InterpretContext.current();
        String webRootPath = (String) ctx.getValueStack().getValue(JetWebContext.WEBROOT_PATH);
        return webRootPath + contextPath;
    }

    /**
     * 输出当前日期格式化 eg:${today("yyyy-MM-dd")} -> 2014-02-12
     *
     * @param format
     * @return
     */
    public static String today(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 用户菜单展示
     *
     * @return
     */
    public static List<Menu> menu() {
        MenuRepository r = SpringContextHolder.getBean(MenuRepository.class);
        List<Menu> menus = r.loadAllMenus();
        List<Menu> resMenus = new ArrayList<Menu>();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
//        UserEntity userEntity = SessionUser.INSTANCE.getCurrentUser();
//        DepType depType = DepType.depType(Integer.valueOf(userEntity.getDepId()));
//        DataType[] dataTypes = depType.getDataTypes();
//        if(null ==dataTypes || dataTypes.length <= 0 || userEntity.getLevel() == RoleType.MANAGER.getRoleLevel()){
//            return menus;
//        }
        for (final Menu menu : menus) {
//            if (!menu.isCheckDep() || CollectionUtils.isEmpty(menu.getSub_menus())) {
//                resMenus.add(menu);
//                continue;
//            }
            List<Menu> subMenus = new ArrayList<Menu>();
            Menu newMenu =new Menu();
            BeanUtils.copyProperties(menu,newMenu);
            for (Menu subMenu : menu.getSub_menus()) {
//                if (!subMenu.isCheckDep()) {
//                    subMenus.add(subMenu);
//                    continue;
//                }
//                for(final DataType dataType : dataTypes){
//                    if(dataType.getDataName().equals(subMenu.getName())){
//                        subMenus.add(subMenu);
//                        break;
//                    }
//                }
                subMenus.add(subMenu);
            }
            newMenu.setSub_menus(subMenus);
            resMenus.add(newMenu);
        }
        return resMenus;
    }
    public static String config(String key) {
        return "";
    }

    /**
     * 用户菜单展示
     *
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * HTML转义
     *
     * @return
     */
    public static String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml4(str);
    }

    /**
     * Base64编码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String base64Encode(String str) throws UnsupportedEncodingException {
        if (isEmpty(str)) {
            return "";
        }
        return Base64Utils.encodeToString(str.getBytes("UTF-8"));
    }

    /**
     * Base64解码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String base64Decode(String str) throws UnsupportedEncodingException {
        if (isEmpty(str)) {
            return "";
        }
        return new String(Base64Utils.decodeFromString(str), "UTF-8");
    }

    /**
     * 输出日期格式化
     * <p>
     * eg:${dateToString(new Date())} ->20161123142533
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "date is null";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 时间戳转日期
     * eg : 1481017143387 -> 2016-12-06 17:24:00
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(Long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), "yyyy-MM-dd HH:mm:ss");
    }


    public static String substr(String str, int start, int end) {
        if (str == null) {
            return "[substr jet func]str is null";
        }
        if (str.length() - 1 < end) {
            return str;
        }
        return str.substring(start, end) + "...";
    }

    public static boolean listIsEmpty(List<Object> list) {
        return list.isEmpty();
    }

    public static boolean isContaionOf(List<Object> list, Object item) {
        for (Object obj : list) {
            if (obj.equals(item)) {
                return true;
            }
        }
        return false;
    }

}
