import java.util.List;

public class SystemCall {
    public int id;
    public String name;
    List<String> args;

    public SystemCall(int id, String name, List <String> args){
        this.id = id;
        this.name = name;
        this.args = args;
    }
}
