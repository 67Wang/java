import java.util.Map;
import java.util.Properties;

public class TestDemo {

    public static void main(String[] args) {
        System.out.println("get properties start  =================================================================");
        Properties properties = System.getProperties();
        properties.entrySet().forEach(items -> System.out.println("key:"+items.getKey()+"---value:"+items.getValue()));
        System.out.println("get env start  =================================================================");
        Map<String, String> getenv = System.getenv();
        getenv.forEach((key,value)-> System.out.println("key:"+key+"---value:"+value));
    }
}
