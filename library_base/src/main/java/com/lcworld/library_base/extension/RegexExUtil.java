package com.lcworld.library_base.extension;

/**
 * 扩展{@link com.blankj.utilcode.util.RegexUtils}
 */
public class RegexExUtil {

    //判断登录密码是正确
    public static boolean isLoginPassword(String psw) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        if (null == psw || "".equals(psw)) {
            return false;
        }

        return psw.matches(regex);
    }

}
