package quoters.bpp;

import my_spring.InjectRandomIntAnnotationObjectConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor{

    InjectRandomIntAnnotationObjectConfigurator configurator = new InjectRandomIntAnnotationObjectConfigurator();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        try {
            configurator.configure(bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
