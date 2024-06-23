import java.util.*;
import java.io.*;

class dice {

	public static long findWays(int []faces, int num_dice, int sum)
	{

		int mod = 1000000007;
	long[][] possibleways = new long[num_dice+1][sum+1];

	for(int j = 1; j <= faces[0] && j <= sum; j++)
				possibleways[1][j] = 1;

	for(int i = 2; i <= num_dice;i ++){
        int face_num = faces[i-1];
				for(int j = 1; j <= sum; j++){
					for(int k = 1; k < j && k <= face_num; k++)
						possibleways[i][j] = (possibleways[i][j] +possibleways[i-1][j-k])%mod;
				}
		}
		return possibleways[num_dice][sum];
	}

	public static void main (String[] args) {
		File file = new File("test.txt");
        try {
        Scanner sc = new Scanner (file);
        int numDice = sc.nextInt();
        int[] faces = new int[numDice];

        int sum = sc.nextInt();

        for(int i=0;i<numDice;i++)
            faces[i]=sc.nextInt();

        System.out.println(findWays(faces, numDice, sum));

        sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Exception occured");
        }
		
	}
}