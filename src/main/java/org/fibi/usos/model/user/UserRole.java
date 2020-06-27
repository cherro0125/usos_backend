package org.fibi.usos.model.user;

import org.fibi.usos.annotation.RegisterEnum;

@RegisterEnum
public enum UserRole {
    STUDENT(Names.STUDENT),
    LECTURER(Names.LECTURER),
    RECTOR(Names.RECTOR),
    DEAN(Names.DEAN),
    PORTER(Names.PORTER);

    private final String value;

    public class Names{
        public static final String STUDENT = "Student";
        public static final String LECTURER = "LECTURER";
        public static final String RECTOR = "RECTOR";
        public static final String DEAN = "DEAN";
        public static final String PORTER = "PORTER";
    }


    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
