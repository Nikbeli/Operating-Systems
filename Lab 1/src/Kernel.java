import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Kernel {
    ArgStack stack;

    // список вызовов
    List<SystemCall> SystemCalls = Arrays.asList(
            new SystemCall(7, "Open file", Arrays.asList("String", "String")),
            new SystemCall(11, "Save file", Arrays.asList("Integer", "String")),
            new SystemCall(46, "Print Sheet", Arrays.asList("Integer", "Color", "String")),
            new SystemCall(72, "Delete file", Arrays.asList("String", "Datetime")),
            new SystemCall(98, "Pause process", Arrays.asList("Float"))
    );

    //конструктор с необходимым стеком
    public Kernel(ArgStack stack) {this.stack = stack; }

    // вызов
    public void Call(int id) {
        for (SystemCall sc : SystemCalls) {
            // перебираем список вызовов
            if (sc.id == id) {
                // ищем нужный вызов по id
                System.out.println("========================================");
                System.out.println("Making a call(" + id + ")");

                for (int i = sc.args.size() - 1; i >= 0; i--) {
                    // с конца проверяем типы
                    if(stack.isempty()) {
                        System.out.println("ERROR! INVALID ARGUMENTS. NO PARAMETRS");
                        break;
                    } else {
                        Arg element = stack.Pop();
                        if (element.type != sc.args.get(i)) {
                            System.out.println("ERROR! INVALID ARGUMENTS");
                            return;
                        }
                    }
                }

                System.out.println("COOL! CALL (ID: " + id + ") COMPLETED SUCCESSFULLY!");
                return;
            }
        }
    }

    public void ShowAll(){
        System.out.println("List of system calls:");
        for(SystemCall sc: SystemCalls) {
            //перебираем список вызовов
            System.out.println("====================");
            System.out.println("ID:" + sc.id + "\nNAME:"
                    + sc.name + "\nList of arguments" + sc.args);
        }

        System.out.println("====================");
    }
}
