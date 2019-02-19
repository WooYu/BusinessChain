package com.lcworld.library_base.http;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.KLog;
import okhttp3.ResponseBody;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.HttpException;

import java.net.ConnectException;


/**
 * Created by goldze on 2017/5/11.
 */
public class RequestExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int LOGINAGAIN = 1;
    private static final int PARAMSERROR = 500;

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        KLog.e(e);

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ErrorConvention.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "操作未授权";
                    break;
                case FORBIDDEN:
                    ex = new ResponseThrowable(e, ErrorConvention.AUTH_ERROR);
                    ex.message = "请求被拒绝";
                    break;
                case NOT_FOUND:
                    ex.message = "资源不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "服务器执行超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器不可用";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }

            try {
                ResponseBody responseBody = httpException.response().errorBody();
                if (null == responseBody) {
                    return ex;
                }

                String bodyStr = responseBody.string();
                if (null != bodyStr && bodyStr.contains("message")) {
                    JSONObject jsonObject = new JSONObject(bodyStr);
                    ex.message = jsonObject.getString("message");
                    return ex;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new ResponseThrowable(e, ErrorConvention.PARSE_ERROR);
            ex.message = "解析错误";
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, ErrorConvention.NETWORD_ERROR);
            ex.message = "连接失败";
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponseThrowable(e, ErrorConvention.SSL_ERROR);
            ex.message = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, ErrorConvention.TIMEOUT_ERROR);
            ex.message = "连接超时";
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ResponseThrowable(e, ErrorConvention.TIMEOUT_ERROR);
            ex.message = "主机地址未知";
        } else if (e instanceof RequestParamsException) {
            RequestParamsException paramsException = (RequestParamsException) e;
            switch (paramsException.getCode()) {
                case LOGINAGAIN:
                    ex = new ResponseThrowable(e, ErrorConvention.AUTH_ERROR);
                    ex.message = paramsException.getMessage();
                    break;
                case PARAMSERROR:
                    ex = new ResponseThrowable(e, ErrorConvention.PARAMS_ERROR);
                    ex.message = paramsException.getMessage();
                    break;
                default:
                    ex = new ResponseThrowable(e, ErrorConvention.UNKNOWN);
                    ex.message = "网络错误";
                    break;
            }
        } else {
            ex = new ResponseThrowable(e, ErrorConvention.UNKNOWN);
            ex.message = "未知错误";
        }

        return ex;
    }


}

