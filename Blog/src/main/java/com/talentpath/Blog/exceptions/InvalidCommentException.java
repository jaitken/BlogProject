package com.talentpath.Blog.exceptions;

public class InvalidCommentException extends Exception {

    public InvalidCommentException(String message){
        super(message);
    }

    public InvalidCommentException(String message, Throwable innerException){
        super(message, innerException);
    }
}
