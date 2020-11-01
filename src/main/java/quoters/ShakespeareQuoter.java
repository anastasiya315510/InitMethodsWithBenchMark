package quoters;


import lombok.Data;
import my_spring.InjectRandomInt;


@Data
public class ShakespeareQuoter implements Quoter {
    private String message;
    @InjectRandomInt(min = 3, max = 5)
    private int repeat;

    @Override
    public void sayQuoter() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }

}
