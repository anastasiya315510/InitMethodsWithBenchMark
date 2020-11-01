package my_spring;


public interface ProxyConfigurator {
    <T> Object wrapWithProxy(Object t, Class<T> type);
}
