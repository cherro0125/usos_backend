package org.fibi.usos.exception.auth;

public class UserAlreadyExistsException  extends Exception{

    public UserAlreadyExistsException() {
        super("This user already exists.");
    }
}
