public class ArgStack {
    private int size;
    private Arg[] stack;
    private int head;

    public ArgStack(int size){
        this.size = size;
        stack = new Arg[size];
        head = -1;
    }

    public void Push(Arg arg){
        stack[++head] = arg;
    }
    public boolean isempty(){
            return head < 0;
    }
    public Arg Pop() {
        return stack[head--];

        /*if(isempty()){
            System.out.println("Stack is empty");
            return null;
        }

        Arg res = stack[head];
        stack[head--] = null;
        return res;*/
    }
}
