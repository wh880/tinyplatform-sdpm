package org.tinygroup.sdpm.security.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 表单验证（包含验证码）过滤类
 *
 * @author Hulk
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    public static final String DEFAULT_MESSAGE_PARAM = "msg";
    protected static final Logger logger = LoggerFactory.getLogger(FormAuthenticationFilter.class);
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String messageParam = DEFAULT_MESSAGE_PARAM;

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = request.getRemoteHost();
        String captcha = getCaptcha(request);
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    public String getMessageParam() {
        return messageParam;
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request, ServletResponse response) {
        String message = "";
        if (e instanceof IncorrectCredentialsException
                || e instanceof UnknownAccountException) {
            message = "用户或密码错误, 请重试.";
        } else {
            logger.logMessage(LogLevel.ERROR, "系统出现点问题，请稍后再试！", e);
        }
        request.setAttribute(getFailureKeyAttribute(), e.getClass().getName());
        request.setAttribute(getMessageParam(), message);
        if (token != null) {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            request.setAttribute("username", usernamePasswordToken.getUsername());
        }
        return true;
    }

}