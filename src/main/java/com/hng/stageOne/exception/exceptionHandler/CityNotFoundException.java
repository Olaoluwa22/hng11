package com.hng.stageOne.exception.exceptionHandler;
    public class CityNotFoundException extends RuntimeException{
        public CityNotFoundException(String message){
            super(message);
        }
    }