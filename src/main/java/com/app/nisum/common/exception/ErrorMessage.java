package com.app.nisum.common.exception;

public class ErrorMessage {
    public static final String BAD_CREDENTIAL = "The username or password is incorrect";
    public static final String ACCOUNT_STATUS = "The account is locked";
    public static final String ACCESS_DENIED = "You are not authorized to access this resource";
    public static final String SIGNATURE = "The JWT signature is invalid";
    public static final String EXPIRED_JWT = "The JWT token has expired";
    public static final String NOT_CHECK = "Unknown internal server error";
    public static final String NOT_FOUND_USER = "User not found";
    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String INVALID_EMAIL = "Invalid email";
    public static final String EXIST_EMAIL = "The email already registered";
}
