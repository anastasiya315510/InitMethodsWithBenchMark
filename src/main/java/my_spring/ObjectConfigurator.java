package my_spring;


import java.lang.reflect.InvocationTargetException;

public interface ObjectConfigurator {
    void configure(Object t) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
