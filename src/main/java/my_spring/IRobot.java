package my_spring;

import javax.annotation.PostConstruct;


public class IRobot {

    @InjectByType
    private Speaker speaker;
    @InjectByType
    private Cleaner cleaner;


    @PostConstruct
    public void init() {
        System.out.println(speaker.getClass().getName());
    }

    public void cleanRoom() {
        speaker.speak("I started...");
        cleaner.clean();
        speaker.speak("I finished...");



    }
}
