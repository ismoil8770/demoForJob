package com.example.demo.model;

import com.example.demo.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResModel {
    private String message;
    private Integer statusCode;
    private Object data;

    public ResModel(Status status) {
        this.message = status.getMessage();
        this.statusCode = status.getCode();
    }
    public ResModel(Status status, Object data) {
        this.message = status.getMessage();
        this.statusCode = status.getCode();
        this.data = data;
    }

}
