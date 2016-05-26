package com.fairy.redis.validator;

import com.fairy.redis.exception.ValidatorException;
import com.fairy.redis.util.Constants;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 16-4-29.
 */
@Component
public class FormatValidator {

    private static final String REGEX_transTime = "\\d{8} \\d{6}";
    private static final String REGEX_transId = "\\d{18}";
    private static final String REGEX_MAX_INT = "\\d{1,9}";

    public void common(Map<String, Object> value) throws ValidatorException {
        String channel = (String) value.get("channel");
        String errorMsg = "";
        if (channel == null || channel.trim().length() == 0) {
            errorMsg = "channel:渠道代码不允许为空;";
        }
        String transTime = (String) value.get("transTime");
        if (!Pattern.matches(REGEX_transTime, transTime == null ? "" : transTime.trim())) {
            errorMsg += "traTime:格式错误;";
        }
        String transCode = (String) value.get("transCode");
        if (transCode == null || transCode.trim().length() == 0) {
            errorMsg += "transCode:格式错误;";
        }
        String transId = (String) value.get("transId");
        if (!Pattern.matches(REGEX_transId, transId == null ? "" : transId.trim())) {
            errorMsg += "transId:格式错误;";
        }
        String reqId = (String) value.get("reqId");
        if (reqId == null || reqId.trim().length() == 0) {
            errorMsg += "reqId:格式错误;";
        }
        String key = (String) value.get("key");
        if (key == null || key.trim().length() == 0) {
            errorMsg += "key:格式错误;";
        }

        if (!errorMsg.equals("")) {
            throw new ValidatorException(Constants.ERROR_DATA_FORMAT_CODE, errorMsg);
        }
    }

    public void save(Map<String, Object> value) throws ValidatorException {
        this.common(value);
        String errorMsg = "";
        String expire = (String) value.get("expire");
        if (expire != null && expire.trim().length() != 0) {
            if (!Pattern.matches(REGEX_MAX_INT, expire.trim())) {
                errorMsg += "expire:格式错误;";
            }
        }
        Object data = value.get("data");
        if (data == null) {
            errorMsg += "data:不允许为空;";
        }

        if (!errorMsg.equals("")) {
            throw new ValidatorException(Constants.ERROR_DATA_FORMAT_CODE, errorMsg);
        }
    }

    public void query(Map<String, Object> value) throws ValidatorException {
        this.common(value);
    }

    public void expire(Map<String, Object> value) throws ValidatorException {
        this.common(value);
        String errorMsg = "";
        String expire = (String) value.get("expire");
        if (expire != null && expire.trim().length() != 0) {
            if (!Pattern.matches(REGEX_MAX_INT, expire.trim())) {
                errorMsg += "expire:格式错误;";
            }
        }else {
            errorMsg += "expire:格式错误;";
            throw new ValidatorException(Constants.ERROR_DATA_FORMAT_CODE, errorMsg);
        }
        if (!errorMsg.equals("")) {
            throw new ValidatorException(Constants.ERROR_DATA_FORMAT_CODE, errorMsg);
        }
    }

    public void rename(Map<String, Object> value) throws ValidatorException {
        this.common(value);
        String errorMsg = "";
        String newKey = (String) value.get("newKey");
        if (newKey == null || newKey.trim().length() == 0) {
            errorMsg += "newKey:格式错误;";
        }
        if (!errorMsg.equals("")) {
            throw new ValidatorException(Constants.ERROR_DATA_FORMAT_CODE, errorMsg);
        }
    }

    public static void main(String [] args) {
        System.out.println(Integer.MAX_VALUE);
    }

}
