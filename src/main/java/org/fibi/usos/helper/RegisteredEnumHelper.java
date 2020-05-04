package org.fibi.usos.helper;

import org.fibi.usos.exception.RegisteredEnumNotFoundException;
import org.fibi.usos.helper.reflection.ReflectionHelper;
import org.fibi.usos.pool.RegisteredEnumPool;

import java.util.Optional;
import java.util.Set;

public class RegisteredEnumHelper {
    public static Set<String> getAllFieldsName(String enumName) throws RegisteredEnumNotFoundException {
        Optional<Class<?>> enumClass = RegisteredEnumPool.getEnumByName(enumName);
        if (enumClass.isPresent()) {
            return ReflectionHelper.getAllFieldsNames(enumClass.get());
        } else {
            throw new RegisteredEnumNotFoundException();
        }
    }
}
