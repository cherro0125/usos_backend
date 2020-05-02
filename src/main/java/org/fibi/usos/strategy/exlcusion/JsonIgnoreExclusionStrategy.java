package org.fibi.usos.strategy.exlcusion;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import net.minidev.json.annotate.JsonIgnore;

public class JsonIgnoreExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        if(f.getAnnotation(JsonIgnore.class) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
