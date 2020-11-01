package my_spring;

import lombok.Data;
import lombok.SneakyThrows;

import javax.management.*;
import java.lang.management.ManagementFactory;

@Data
public class BenchmarkToggle implements BenchmarkToggleMBean{
    private boolean enabled = true;

    @SneakyThrows
    public BenchmarkToggle() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(this, new ObjectName("myMbeans", "name", "benchmark"));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }
}
