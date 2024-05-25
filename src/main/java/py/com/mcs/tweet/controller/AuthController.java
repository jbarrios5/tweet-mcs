package py.com.mcs.tweet.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.mcs.tweet.bean.auth.req.AuthPostReqData;
import py.com.mcs.tweet.bean.auth.resp.AuthPostResData;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.exceptions.TweetException;
import py.com.mcs.tweet.service.auth.AuthService;

import javax.validation.Valid;

@RequestMapping("auth/${version}")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "authenticate user", description = "Authenticate user by password and document")
    public AuthPostResData login(
            @RequestHeader(value = TweetConstant.API_KEY, required = true) String apiKey,
            @RequestHeader(value = TweetConstant.CHANNEL) String channel,
            @RequestBody @Valid AuthPostReqData req) throws TweetException {
        return authService.login(req.getData());
    }

    @PostMapping("/verify")
    @Operation(summary = "verify jwt is valid", description = "Verify jwt is expires")
    public Boolean isJWTValid(
            @RequestHeader(value = TweetConstant.API_KEY, required = true) String apiKey,
            @RequestHeader(value = TweetConstant.CHANNEL) String channel,
            @RequestHeader(value = TweetConstant.ACCESS_TOKEN, required = true) String accessToken) throws TweetException {
        return authService.isJWTValid(accessToken);
    }
}
