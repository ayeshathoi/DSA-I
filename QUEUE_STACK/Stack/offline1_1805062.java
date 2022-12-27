class Stack<T>
{
    class Node{
        Node next;
        T num;
    }
    Node head;
    public Stack ()
    {
         head = null;
    }

public T pop(){
    if (head == null) {
        System.out.println("Invalid Expression");
    }
    T value = head.num;
    head = head.next;
    return value;
    }
    public boolean isEmpty()
    {
     return head == null;
    }
 
    public T peek()
    {
        return head.num;
    }

    public void push( T value) {
    Node oldHead = head;
    head = new Node();
    head.num = value;
    head.next = oldHead;
    }
}
    
    public class offline1_1805062
    {
        public static int evaluate(String expression)
        {
            char[] tokens = expression.toCharArray();
            Stack<Integer> values = new
                    Stack<Integer>();
            Stack<Character> ops = new
                    Stack<Character>();
    
            for (int i = 0; i < tokens.length; i++)
            {
                if (tokens[i] == ' ')
                    continue;
                if (tokens[i] >= '0' &&
                        tokens[i] <= '9')
                {
                    StringBuffer sbuf = new
                            StringBuffer();
                    while (i < tokens.length &&
                            tokens[i] >= '0' &&
                            tokens[i] <= '9')
                        sbuf.append(tokens[i++]);
                    values.push(Integer.parseInt(sbuf.
                            toString()));
                    i--;
                }
    
                else if (tokens[i] == '(')
                    ops.push(tokens[i]);

                    else if (tokens[i] == ')')
                    {
                        int count=0;
                        int a = values.pop();
                        int c = a;
                        while(a!=0)
                        {
                            a=a/10;
                            count++;
                        }
                        while (ops.peek() != '(')
                        {
                            if(tokens[i-count-2]=='(' && tokens[i-count-1]=='-')
                            {
                                c = (operation(ops.pop(),c,0));
                            }
                            else {
                                c = (operation(ops.pop(),
                                    c,
                                    values.pop()));
                            }
                        }
                        values.push(c);
                        ops.pop();
                    }
                 
            
                else if (tokens[i] == '+' ||
                        tokens[i] == '-' ||
                        tokens[i] == '*' ||
                        tokens[i] == '/')
                {
                    while (!ops.isEmpty() &&
                        importance (tokens[i],
                                    ops.peek()))
                        values.push(operation(ops.pop(),
                                values.pop(),
                                values.pop()));
                    ops.push(tokens[i]);
                }
            }
            while (!ops.isEmpty())
                values.push(operation(ops.pop(),
                        values.pop(),
                        values.pop()));
    
            return values.pop();
        }
        
        public static boolean importance(
                char op1, char op2)
        {
            if (op2 == '(' || op2 == ')')
                return false;
            if ((op1 == '*' || op1 == '/') &&
                    (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }
        public static int operation(char op,
                                  int b, int a)
        {
            switch (op)
            {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0)
                        throw new
                                UnsupportedOperationException(
                                "Cannot divide by zero");
                    return a / b;
            }
            return 0;
        }
        public static void main(String[] args)
        {
            System.out.println(offline1_1805062.
                    evaluate("((-70))"));
            System.out.println(offline1_1805062.
                    evaluate("(9*3-(7*8+((-4)/2)))"));
            System.out.println(offline1_1805062.
                    evaluate("(9*3-(7*8+((4/2)))"));
        }
    }
