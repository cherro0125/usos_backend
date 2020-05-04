package org.fibi.usos.helper.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionHelper {
    public static Set<String> getAllFieldsNames(Class<?> typeClass){
        return Arrays.stream(typeClass.getFields()).map(Field::getName).collect(Collectors.toSet());
    }
}