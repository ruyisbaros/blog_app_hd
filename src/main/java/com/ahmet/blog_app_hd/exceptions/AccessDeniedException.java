package com.ahmet.blog_app_hd.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
