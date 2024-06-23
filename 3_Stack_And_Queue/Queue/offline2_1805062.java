class QNode {
    Character key;
    QNode next;
  
    public QNode(Character key)
    {
        this.key = key;
        this.next = null;
    }
}
class Queue <T>{
    QNode front, rear;
  
    public Queue()
    {
        this.front = this.rear = null;
    }
  
    void add(Character key)
    {
  
        QNode temp = new QNode(key);
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }
        this.rear.next = temp;
        this.rear = temp;
    }
    void remove()
    {
        if (this.front == null)
            return;
        this.front = this.front.next;
        if (this.front == null)
            this.rear = null;
    }
    boolean isEmpty()
    {
        return this.front==null;
    }
    Character peek()
    {
        return front.key;
    }
}
  
public class offline2_1805062 {
    final static int MAX_CHAR = 26;
    static void checkNonRepeating(String str)
    {
        int[] charCount = new int[MAX_CHAR];
  
        Queue<Character> q = new Queue<Character>();
        System.out.print("string_new : ");
  
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
  
            q.add(ch);
  
            charCount[ch - 'a']++;
            while (!q.isEmpty()) {
                if (charCount[q.peek() - 'a'] > 1)
                    q.remove();
                else {
                    System.out.print(q.peek());
                    break;
                }
            }
            if (q.isEmpty())
                System.out.print("#");
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        String str_old = "abcabc";
        checkNonRepeating(str_old);
    }
}