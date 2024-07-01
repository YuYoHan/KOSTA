package config;

import java.lang.reflect.Constructor;

public class CreateInstance {
    public static Object creatInstance (Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Constructor<?> constructors = clazz.getConstructor();
        return constructors.newInstance();
    }
}
