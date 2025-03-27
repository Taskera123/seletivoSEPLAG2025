package br.com.lucasaraujo.api.config;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}