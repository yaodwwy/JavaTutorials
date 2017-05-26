package api.common.util;

/**
 * Created by Adam on 2017/2/20.
 * 安全api范例
 * 在请求中拦截除登录以外的所有请求
 * * 登录是通过 appKey(id) 和 secret(密钥) 请求一个标识token
 *  token通过md5封装在sign中，请求时携带 appKey,timestamp,params,sign即可进行验证
 *
 * 在处理其它请求时都要验证以下信息：
 * 1、时间戳在合法范围内
 * 2、token在合法范围内
 * 3、sign签名通过
 * 4、params业务验证 其它
 */
public class RequestParams {

    /**
     * 是用户有效时间内使用的key
     */
    private String appKey;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 签名是通过 MD5( token + timestamp + params )计算出来的
     * 用于核对参数一致性
     */
    private String sign;

    public RequestParams() {
    }

    public RequestParams(String appKey, Long timestamp, String params, String sign) {
        this.appKey = appKey;
        this.timestamp = timestamp;
        this.params = params;
        this.sign = sign;
    }

    /**
     * 按过期时效计算是否过期 + 签名验证
     * @param expire 时间戳超时差
     * @param token token
     * @return
     */
    public boolean signVerify(Long expire,String token){
        boolean timeVerify = (System.currentTimeMillis() - Long.valueOf(this.timestamp)) < expire;
        if(!timeVerify){
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(token);
        stringBuilder.append(this.timestamp);
        stringBuilder.append(this.params);
        String encryption = MD5Utils.encryption(stringBuilder.toString());
        return encryption.equals(sign);
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
