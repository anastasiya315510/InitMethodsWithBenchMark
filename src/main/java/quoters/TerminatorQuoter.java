package quoters;


import lombok.Data;


import java.util.List;
@Data
public class TerminatorQuoter implements Quoter {
    List<String> messages;
    @Override
    public void sayQuoter() {
    messages.forEach(System.out::println);
    }


}
