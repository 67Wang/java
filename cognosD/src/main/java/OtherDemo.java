import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OtherDemo {

    public static void main(String[] args) {
        List<String> lines = Arrays.asList("spring", "node", "mkyong");

        Optional<String> first = lines.stream().filter("node"::equals).findFirst();
        System.out.println(first.get());
    }
}
