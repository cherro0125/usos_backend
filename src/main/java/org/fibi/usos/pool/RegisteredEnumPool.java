package org.fibi.usos.pool;

import org.fibi.usos.annotation.RegisterEnum;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class RegisteredEnumPool {

    private static final Logger logger  = Logger.getLogger(RegisteredEnumPool.class.getName());
    private static Reflections reflections = new Reflections("org.fibi.usos");
    private static Set<Class<?>> annotatedEnums;

    @PostConstruct
    public void init(){
        logger.info("Init");
        annotatedEnums = reflections.getTypesAnnotatedWith(RegisterEnum.class);
        annotatedEnums.forEach(en -> {
            logger.info(String.format("Registered %s in pool.",en.getName()));
        });
    }

    public static Optional<Class<?>> getEnumByName(String enumName){
        return annotatedEnums.stream()
                .filter(c -> enumName.equals(c.getSimpleName()))
                .findAny();
    }
}
