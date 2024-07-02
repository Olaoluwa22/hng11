package com.hng.stageOne.exception;

import java.time.Instant;
import java.util.List;

public class ExceptionResponse<Generic> {
    private String message;
    private Integer status;
    private Instant timestamp;
    private List<Generic> data;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public List<Generic> getData() {
        return data;
    }
    public void setData(List<Generic> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}