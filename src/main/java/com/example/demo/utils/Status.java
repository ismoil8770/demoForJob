package com.example.demo.utils;

public enum Status {
  USER_NOT_FOUND(1001,"User not found"),
    OK(200,"OK"), EXIST_USER(1002,"Username or password exist" ), EXIST(1003,"Exist" ), NOT_FOUND(1004,"not found" ), INCORRECT(1005,"incorrect" );


   private final String message;
   private final Integer code;
    Status(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
