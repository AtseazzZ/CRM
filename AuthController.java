package com.tt.controller;

import com.tt.common.Result;
import com.tt.security.SecurityUtils;
import com.tt.service.AuthService;
import com.tt.vo.LoginRequest;
import com.tt.vo.LoginResponse;
import com.tt.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        return Result.ok(authService.login(request));
    }

    @GetMapping("/info")
    public Result<UserInfoVO> info() {
        Long userId = SecurityUtils.getCurrentUserId();
        return Result.ok(authService.getUserInfo(userId));
    }
}
