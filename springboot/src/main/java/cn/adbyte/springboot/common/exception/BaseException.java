package cn.adbyte.springboot.common.exception;

import cn.adbyte.springboot.common.constant.SysConstant;
import cn.adbyte.springboot.common.entity.ReturnMessage;
import com.alibaba.fastjson.JSON;

/**
 * Created by Adam.yao on 2017/10/25.
 */
public class BaseException extends RuntimeException {

    public BaseException(ErrorCode errorCode) {
        super(createFriendlyErrMsg(errorCode.des, errorCode.code));
    }

    public BaseException(String errorMsg) {
        super(createFriendlyErrMsg(errorMsg, SysConstant.FAILED));
    }

    public BaseException(String errorMsg, Number errorCode) {
        super(createFriendlyErrMsg(errorMsg, errorCode));
    }

    private static String createFriendlyErrMsg(String msgBody, Number errorCode) {
        ReturnMessage<String> returnMessage = ReturnMessage.message(SysConstant.FAILED, errorCode.toString(), msgBody);
        return JSON.toJSONString(returnMessage);
    }
}