package com.company;
class Array {
    int length;
    public String[] a = new String[100];
    Array() {
        length = 0;
    }

    Array(int size) {
        length = size;
        for (int i = 0; i < length; i++) {
            a[i] = null;
        }
    }

    Array(String x[]) {
        length = x.length;
        for (int i = 0; i < x.length; i++) {
            a[i] = x[i];

        }
    }

   String []subArray(int start, int finish) {
        if(finish>length||start<0)
            System.out.print("Invalid Index\n");
       int count=0;
        String []arr3 = new String [finish-start+1];
        for (int i = start; i <= finish; i++) {
            arr3[count]=a[i];
            count++;
        }
        return (arr3);
    }

    void getArray() {
        for (int i = 0; i < length; i++) {
            System.out.print(a[i] + "\n");
        }
    }

    void isEmpty() {
        int count = 0;
        if (length == 0)
            System.out.println("This String is Empty");
        else if (length!=0) {
            for (int i = 0; i < length; i++) {
                if (a[i] != null)
                    count++;
            }
            if(count==0)
            System.out.println("This String is Empty");
            else System.out.println("This String is not Empty");
        }
    }

    int getLength() {
        return length;
    }

    void getElementAt(int i) {
        if (i >= length|| i<0) {
            System.out.print("Invalid Index\n");
        }
        System.out.println(a[i]);
    }

   int [] findIndex(String e) {
       int count=0;
        System.out.print("Indexes are ");
        for (int i = 0; i < length; i++) {
            if (e == a[i]) {
                count++;
            }
        }
       int []arr3 = new int [count];
        count=0;
       for (int i = 0; i < length; i++) {
           if (e == a[i]) {
               arr3[count]=i+1;
               count++;
           }
       }
        return (arr3);
}

    void addElement(String e) {
        length++;
        a[length - 1] = e;
    }

    void removeAt(String e) {
        for (int i = 0; i < length; i++) {
            if (a[i] == e) {
                for (int f = i; f < length; f++) {
                    a[f] = a[f + 1];
                }
                length--;
            }
        }
    }


    void addAt(int i, String e) {
        if (i<0) {
            System.out.print("Invalid Index\n");
        }
        length++;
        int l = length;
        for(int f =l-1;f>=i;f--)
        {
            a[l]=a[f];
            l--;

        }
        a[i] = e;

    }

    String []mergeArrays(String arr1[], String arr2[]) {
        int i = 0, j = 0, k = 0;
        int n1 = arr1.length;
        int  n2 = arr2.length;
        String []arr3 = new String [n1+n2];
        while (i < n1 && j < n2) {
            if (arr1[i].compareTo(arr2[j]) <0 )
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
        while (i < n1)
            arr3[k++] = arr1[i++];
        while (j < n2)
            arr3[k++] = arr2[j++];
        return (arr3);
    }
}
public class Main {

    public static void main(String[] args) {
        Array a = new Array();
        a.getArray();
        Array b = new Array(5);
        b.getArray();
        System.out.println(b.getLength());
        b.isEmpty();
        String []s= {"Blue","Green", "Red"};
        String []w = {"Tara","Ayesha","Mishel","Tara"};
        Array test = new Array(w);
        int [] number = test.findIndex("Tara");
        for(int index=0;index<number.length;index++)
        {System.out.print(number[index]+" ");
            }
        System.out.println();
        test.addAt(2,"Anika");
        test.addElement("Jannat");
        test.removeAt("Tara");

        String []n=test.subArray(2,3);
        Array p = new Array(n);
        p.getArray();
        test.getElementAt(2);
        test.getArray();
        Array c = new Array(s);
        String []t= {"Apple","Mango", "Orange"};
        Array d = new Array(t);
        String []m = d.mergeArrays(s,t);
        Array f =new Array (m);
        f.getArray();

    }
}
