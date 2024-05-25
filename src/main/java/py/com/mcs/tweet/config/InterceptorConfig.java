package py.com.mcs.tweet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.exceptions.TweetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Configuration
public class InterceptorConfig implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Value("${tweet.apiKey}")
    private String apiKey;

    @Value("${tweet.channel}")
    private String kytechChannel;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preflight CORS
        if (request.getMethod().equals(HttpMethod.OPTIONS.name()))
            return true;
        if (request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/v3/api-docs") || request.getRequestURI().contains("/swagger-ui/"))
            return true;

        if (!kytechChannel.equals(request.getHeader(TweetConstant.CHANNEL))) {
            logger.warn(TweetConstant.LOG_FORMATT, "no-log-id", "Canal incorrecto!", request.getHeader(TweetConstant.CHANNEL));
            throw new TweetException("Solicitud no autorizada", HttpStatus.BAD_REQUEST);
        }

        if (!apiKey.equals(request.getHeader(TweetConstant.API_KEY))) {
            logger.warn(TweetConstant.LOG_FORMATT, "no-log-id", "ApiKey incorrecto!", request.getHeader(TweetConstant.API_KEY));
            throw new TweetException("Solicitud no autorizada", HttpStatus.BAD_REQUEST);
        }
        String traceId = UUID.randomUUID().toString();
        TraceContextHolder.setLogId(traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
