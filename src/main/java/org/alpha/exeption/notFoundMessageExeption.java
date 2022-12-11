package org.alpha.exeption;

public class notFoundMessageExeption extends Exception {

    private static final Long serialVersionUID = 1L;

    public notFoundMessageExeption(String message) {
        super(message); //heritage of my Exception super gets Excepetion contructor
        //and than 
    }
}

    