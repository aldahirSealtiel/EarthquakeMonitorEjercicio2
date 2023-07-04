package com.example.earthquake_monitor.api;

public class StatusWithDescription
{

    private final RequestStatus status;
    private final  String message;

    public RequestStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public StatusWithDescription(RequestStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
