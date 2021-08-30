package com.example.linkpreview.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
