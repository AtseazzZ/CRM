package com.tt.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<Void>> handleBusiness(BusinessException e) {
        log.warn("业务异常: {}", e.getMessage());
        HttpStatus httpStatus = resolveHttpStatus(e.getCode());
        return ResponseEntity.status(httpStatus).body(Result.fail(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<Void> handleAccessDenied(AccessDeniedException e) {
        log.warn("权限不足: {}", e.getMessage());
        return Result.fail(403, "权限不足");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleException(Exception e) {
        log.error("系统异常", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.fail(500, "系统异常: " + e.getMessage()));
    }

    /** 业务码 → HTTP 状态码映射（404→NOT_FOUND, 403→FORBIDDEN, 401→UNAUTHORIZED） */
    private HttpStatus resolveHttpStatus(int code) {
        switch (code) {
            case 401: return HttpStatus.UNAUTHORIZED;
            case 403: return HttpStatus.FORBIDDEN;
            case 404: return HttpStatus.NOT_FOUND;
            case 400: return HttpStatus.BAD_REQUEST;
            default:  return HttpStatus.OK;
        }
    }
}
