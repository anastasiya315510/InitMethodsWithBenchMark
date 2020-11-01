package my_spring;

import org.springframework.cglib.proxy.Enhancer;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;


public class BenchmarkProxyConfigurator implements ProxyConfigurator {

    private BenchmarkToggle benchmarkToggle = new BenchmarkToggle();

    public BenchmarkProxyConfigurator() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
    }

    @Override
    public <T> Object wrapWithProxy(Object t, Class<T> type) {

        if (type.isAnnotationPresent(Benchmark.class)|| Arrays.stream(type.getMethods()).anyMatch(method -> method.isAnnotationPresent(Benchmark.class))) {
            if (type.getInterfaces().length == 0) {
                return Enhancer.create(type, (org.springframework.cglib.proxy.InvocationHandler) (o, method, args) -> getInvocationHandlerLogic(t, type, method, args));
            }
            return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), (proxy, method, args) -> getInvocationHandlerLogic(t, type, method, args));
        }
        return t;
    }

    private <T> Object getInvocationHandlerLogic(Object t, Class<T> type, Method method, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method classMethod = type.getMethod(method.getName(), method.getParameterTypes());
        if (benchmarkToggle.isEnabled() && (type.isAnnotationPresent(Benchmark.class) || classMethod.isAnnotationPresent(Benchmark.class))) {
            System.out.println("*********BENCHMARK for method " + method.getName() + " started");
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.println(end - start);
            System.out.println("*********BENCHMARK for method " + method.getName() + " finished");
            return retVal;
        } else {
            return method.invoke(t, args);
        }
    }
}
