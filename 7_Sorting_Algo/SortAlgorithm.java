import java.util.*;

public class SortAlgorithm{

    static void quickSort(int[] arr, int first, int last) {
        if (first < last) {
            int pivot = divide(arr, first, last);
            quickSort(arr, first, pivot - 1);
            quickSort(arr, pivot + 1, last);
        }
    }

    static int divide(int[] arr, int first, int last) {

        int pivot = arr[last];

        int i = (first - 1);

        for (int j = first; j <= last - 1; j++) {

            if (arr[j] < pivot) {

                i++;
                interchange(arr, i, j);
            }
        }
        interchange(arr, i + 1, last);
        return (i + 1);
    }

    static void interchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    static void merge(int arr[], int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;

        int Left[] = new int[n1];
        int Right[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            Left[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            Right[j] = arr[middle + 1 + j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (Left[i] <= Right[j]) {
                arr[k] = Left[i];
                i++;
            } else {
                arr[k] = Right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = Left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = Right[j];
            j++;
            k++;
        }
    }

    static void sorting(int arr[], int left, int right) {
        
        if (left < right) {
            int middle = left + (right - left) / 2;

            sorting(arr, left, middle);
            sorting(arr, middle + 1, right);

            merge(arr, left, middle, right);

        }
    }

    static void printArray(int[] arr, int []arr1) {
        System.out.println("MergeSort           "+ "QuickSort");
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i] +"                    "+arr1[i]);

   
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Options: start or quit");
        while(sc.next().charAt(0)!='q'){
        System.out.println("Enter the size of array: ");
        int n = sc.nextInt();

        System.out.print("Choose ");
        System.out.println("1 to get ascending, ");
        System.out.println("-1 to get descending ");
        System.out.println("and 0  to get random ordered array");
        int order = sc.nextInt();
        if(n==0)
        System.out.println("The array is of zero size. It can't store anything.");
        if(n!=0)
        {   int arr[] = new int[n];
        
        //ascending
        if (order == 1) {
            Random r = new Random();
            arr[0] = r.nextInt();
            for (int i = 0; i < n; i++)
                arr[i] = arr[0]+i;
        }

        //descending
        if (order == -1) {
           
            Random rl = new Random();
            arr[0]= rl.nextInt();
            for (int i = 0; i < n; i++)
                arr[i] =  arr[0]-i ;
        }

        //random
        if (order == 0) 
        {
            Random rp =  new Random();
            for (int i = 0; i < n; i++)
                arr[i] =  rp.nextInt();
        }


        //double sum = 0 ;
        //double sum1 =0;
        //for(j=0;j<10;j++){
        int arr1[] = new int[n];
        for (int i = 0; i < arr1.length; i++)
            arr1[i] = arr[i];
        int arr2[] = new int[n];
        for (int i = 0; i < arr1.length; i++)
                arr2[i] = arr[i];
        
        

        double mp = System.currentTimeMillis();
        sorting(arr1, 0, n - 1);
        double mf = System.currentTimeMillis();
        double t = (mf-mp);
        //sum1 +=t;
        double qp = System.currentTimeMillis();
        quickSort(arr2, 0, n - 1);
        double qf = System.currentTimeMillis();
        double t1 = qf-qp;
        //sum+=t1;
        //}
        printArray(arr1, arr2);
       //sum=t1/10;
       //sum1=t/10;
    //    System.out.println("Time taken in milliseconds for mergesort : " + sum1);
    //    System.out.println("Time taken in milliseconds for quicksort: " + sum2);
        
        System.out.println("Time taken in milliseconds for mergesort for " +n +" inputs: " + t);
        System.out.println("Time taken in milliseconds for quicksort for "+ n + " inputs :" + t1);
        }

        System.out.println("Options: start or quit");
        }
        sc.close();
    }
}