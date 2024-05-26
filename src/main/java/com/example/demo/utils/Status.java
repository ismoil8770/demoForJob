package com.example.demo.utils;

public enum Status {
  USER_NOT_FOUND(1001,"User not found"),
    OK(200,"OK");


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
