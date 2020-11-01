package quoters;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Data
public class TalkingRobotImpl implements TalkingRobot{

    private ArrayList<Quoter> quoter;

    @Override
    @PostConstruct
    public void talk() {
        quoter.forEach(Quoter::sayQuoter);
    }


}
