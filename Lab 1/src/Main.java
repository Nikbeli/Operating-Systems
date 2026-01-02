import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArgStack stack = new ArgStack(10);
        Kernel kernel = new Kernel(stack);
        kernel.ShowAll();

        //stack.Push(new Arg("1", "Integer", 5));
        stack.Push(new Arg("Hello", "String", 10));
        kernel.Call(11);

        stack.Push(new Arg("Copy", "Integer", 1));
        // stack.Push(new Arg("Black", "", 0));
        stack.Push(new Arg("Out", "String", 123));
        kernel.Call(46);


        //kernel.Call(100);
        /*stack.Push(new Arg("1","Integer", 1));
        stack.Push(new Arg("Lab", "Datetime", 10));
        kernel.Call(72);*/
    }
}
