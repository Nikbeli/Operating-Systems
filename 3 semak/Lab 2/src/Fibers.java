public class Fibers {
        private int size;
        private String[] stack;
        protected int head;

        public Fibers(int size) {
            this.size = size;
            stack = new String[size];
            head = -1;
        }

        public void Push(String param) {
            stack[++head] = param;
        }

        public String Pop() {
            return stack[head--];
        }
}
