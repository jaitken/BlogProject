package com.talentpath.Blog.exceptions;

public class InvalidEntryException extends Exception{

    public InvalidEntryException(String message){
        super(message);
    }

    public InvalidEntryException(String message, Throwable innerException){
        super(message, innerException);
    }

}
