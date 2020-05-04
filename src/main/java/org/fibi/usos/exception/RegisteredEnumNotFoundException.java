package org.fibi.usos.exception;

public class RegisteredEnumNotFoundException extends Exception {
    public RegisteredEnumNotFoundException() {
        super("Provided enum was not found in registered enum pool.");
    }
}
