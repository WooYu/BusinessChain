package com.lcworld.library_base.http;

/**
 * 约定异常 这个具体规则需要与服务端或者领导商讨定义
 */
public interface ErrorConvention {
    /**
     * 未知错误
     */
    int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    int HTTP_ERROR = 1003;

    /**
     * 证书出错
     */
    int SSL_ERROR = 1005;

    /**
     * 连接超时
     */
    int TIMEOUT_ERROR = 1006;

    /**
     * 请求参数异常
     */
    int PARAMS_ERROR = 1007;
    /**
     * tooken认证失败
     */
    int AUTH_ERROR = 1008;
}
