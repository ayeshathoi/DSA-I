import java.util.Scanner;
class BinarySearchTree {
    class Node
    {
        int key;
        Node left, right;
 
        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
    Node root;
    BinarySearchTree()
    {
         root = null;
    }
    void insertItem(int key)
    {
         root = insertRec(root, key);
    }
    Node insertRec(Node root, int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }
    void printpreOrder(Node root)
    {
        if (root != null) {
            System.out.print(root.key+ " ");
            printpreOrder(root.left);
            printpreOrder(root.right);
        }
    }
    int getSize(Node root)
    {
        if (root==null)
        return 0;
        else
         {int lDepth = getSize(root.left);
         int rDepth = getSize(root.right);
        return lDepth+rDepth+1;
         }
    }
    int getHeight(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            int lDepth = getHeight(node.left);
            int rDepth = getHeight(node.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }
    public int getInOrderSuccessor(int data) {
        inSuc = null;
        Node node = searchItem(root, data);
        if(node==null)
        {
            return 0;
        }
        if (node.right != null) {
            return rightsmallest(node).key;
        } 
        else {
            nextGreaterEfficient(root,data);
            if(inSuc!=null)
            return inSuc.key;
            else return -1;
        }
    }
    public int getInOrderPredecessor(int data)
    {
        inSuc=null;
        Node node = searchItem(root,data);
        if(node==null)
        {
            return 0;
        }
        if (node.left!=null)
        return leftgreatest(node).key;
        else {
            nextSmallestEfficient(root, data);
            if(inSuc!=null)
            return inSuc.key;
            else return -1;
        
        }
    }
    public static void nextGreaterEfficient(Node node, int target)
    {
        if(node==null)
        {
            return;
        }
        if(node.key > target)
        {
            inSuc = node;
            nextGreaterEfficient(node.left, target);
        }else {
            nextGreaterEfficient(node.right, target);
        }
    }
public static void nextSmallestEfficient(Node node, int target)
{
if(node==null)
{
    return;
}
if(node.key < target)
{
    inSuc = node;
    nextSmallestEfficient(node.right, target);
}else {
    nextSmallestEfficient(node.left, target);
}
}
    public Node leftgreatest(Node node) {
        Node curr = node.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
 
    public Node rightsmallest(Node node) {
        Node curr = node.right;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
 
    public static Node inSuc = null;
    void printinOrder(Node root)
    {
        if (root != null) {
            printinOrder(root.left);
            System.out.print(root.key+" ");
            printinOrder(root.right);
        }
    }
    
    public int getItemDepth(Node root, int value)
    {
        Node cur = searchItem(root,value);
        if(cur==null)
        return -1;
        int lDepth = 0;
        while(root!=cur)
        {
            if(value<root.key)
            {
                root= root.left;
            
                lDepth++;
            }
            else 
            {root = root.right;
                lDepth++;
           }   
    }
    return lDepth;
    }

    public Node getMinItem(Node root)  
    {  
        while(root.left != null) {  
            root = root.left;  
        }  
        return root;  
    }  
    Node searchItem(Node root, int key)
{
    if (root==null || root.key==key)
        return root;

    if (root.key < key)
       return searchItem(root.right, key);

    return searchItem(root.left, key);
}
Node temp =null;
    Node deleteItem(Node root, int key) {
        Node cur =searchItem(root, key);
        if(cur==null)
        {System.out.println("This Item is not in the tree");
        return temp;
    }
        if (root == null)
          return root;
        if (key < root.key)
          root.left =deleteItem(root.left, key);
        else if (key > root.key)
          root.right = deleteItem(root.right, key);
        else {
          if (root.left == null)
            return root.right;
          else if (root.right == null)
            return root.left;
          root.key = minValue(root.right);
          root.right = deleteItem(root.right, root.key);
        }
    
        return root;
      }
      int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
          minv = root.left.key;
          root = root.left;
        }
        return minv;
      }
      public Node getMaxItem(Node root)  
      {  
          while(root.right != null) {  
              root = root.right;  
          }  
          return root;  
      } 
       
    // Driver Code
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter how many nodes you want to add : ");
        int input = sc.nextInt();
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("Please enter value os the nodes : ");
        for (int i = 0;i<input;i++)
        tree.insertItem(sc.nextInt());
        System.out.println("Instructions: \n"+
        "Press 0 to end.\n"+
        "Press 1 to insert.\n"+
        "Press 2 to search.\n"+
        "Press 3 to get Inorder Successor.\n"+
        "Press 4 to get Inorder Predeccor.\n"+
        "Press 5 to get ItemDepth.\n"+
        "Press 6 to get height\n"+
        "Press 7 for preOrder Arrangement\n"+
        "Press 8 for inOrder Arrangement\n"+
        "Press 9 to get size of the tree\n"+
        "Press 10 to get minItem of the tree\n"+
        "Press 11 to get maxItem of the tree\n"+
        "Press 12 to delete Item from the tree\n");
        while(true)
        {
        int f = sc.nextInt();
    
        if (f==1)
        {
            System.out.print("Please insert :");
            tree.insertItem(sc.nextInt());
        }
        if(f==2)
        {
            System.out.print("Please Search :");
            int p =sc.nextInt();
            Node s = tree.searchItem(tree.root,p);
            if(s!=null)
            System.out.println(s.key + " has found");
            if(s==null)
            System.out.println(p + " has not found");
        }
        if(f==3)
        {
            System.out.print("Get inOrder Successor of :");
            int succ = tree.getInOrderSuccessor(sc.nextInt());
            if(succ!=-1 && succ!=0)
            System.out.println(succ);
            if(succ==0)
            System.out.println("This item is not included in tree");
            if(succ==-1)
            System.out.println("No successor");
        }
        if(f==4)
        {
            System.out.print("Get inOrder Predeccessor of :");
            int pre = tree.getInOrderPredecessor(sc.nextInt());
            if(pre!=-1 && pre!=0)
            System.out.println(pre);
            if(pre==0)
            System.out.println("This item is not included in tree");
            if(pre==-1)
            System.out.println("No predecessor");
        }
        if(f==5)
        {
            System.out.print("Get Item depth :");
            int depth = tree.getItemDepth(tree.root,sc.nextInt());
            if(depth!=-1)
            System.out.println(depth);
            else System.out.println("This item is not in the tree");
        }
        if(f==6)
        {
            System.out.print("Get height of the tree : ");
            int h =tree.getHeight(tree.root);
            System.out.println(h);
        }
        if(f==7)
        {
            System.out.println("The preorder arrangement of tree: ");
            tree.printpreOrder(tree.root);
            System.out.print("\n");
        }
        if(f==8)
        {
            System.out.println("The inorder arrangement of tree: ");
            tree.printinOrder(tree.root);
            System.out.print("\n");
        }
        if(f==9)
        {
            System.out.print("The size of tree: ");
            int size = tree.getSize(tree.root);
            System.out.println(size);
        }
        if(f==10)
        {
            System.out.print("The maxItem of tree: ");
            Node size = tree.getMaxItem(tree.root);
            System.out.println(size.key);
        }
        if(f==11)
        {
            System.out.print("The minItem of tree: ");
            Node size = tree.getMinItem(tree.root);
            System.out.println(size.key);
        }
        if(f==12)
        {
            System.out.print("Delete the item : ");
            tree.root = tree.deleteItem(tree.root, sc.nextInt());
        }
        if(f==0)
        {
            System.out.println("The end");
            break;
        }
    }
    }
}
