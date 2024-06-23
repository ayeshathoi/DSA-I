import java.util.*;
import static java.lang.Math.abs;
import static java.lang.StrictMath.sqrt;
import java.io.FileNotFoundException;
import java.io.File;

class Pair<T,V>{
    T first;
    V second;

    public Pair(T a, V b) {
        first = a;
        second = b;
    }

    static<T,V> Pair<T,V> with(T a, V b){
        return new Pair<>(a,b);
    }
}
class Closest{
    private static final double INT_MAX = 2e9;

    static class Point{
        Pair<Integer,Integer> points;
        int num;
    }
    Pair<Integer,Integer> nearest,secondnearest;
    double close,secondclose;
    void changeValue(double near,double secondnear,Pair <Integer,Integer> xpoint,Pair<Integer,Integer> ypoint){
        close=near;
        secondclose=secondnear;
        nearest=xpoint;
        secondnearest=ypoint;
    }
    double secondMinimum(double x,double y){
        return (x<y) ? x:y;
    }
    Closest minimum(Closest x,Closest y){
        return (x.close<y.close)? x:y;
    }
    Closest secondMinimum(Closest x,Closest y){
        return (x.secondclose<y.secondclose)?x:y;
    }
    Closest max(Closest x,Closest y){
        return (x.close>y.close)?x:y;
    }
    double maximumsecond(double x,double y){
        return (x>y)? x:y;
    }

    double squre(double x){return x*x;}
    double distance(Pair<Integer,Integer> x,Pair<Integer,Integer> y){
        return sqrt((squre(x.first-y.first)+squre(x.second-y.second)));
    }
    Closest stripFunctionClosest(ArrayList<Point>strip,Closest Closest){
        int n=strip.size();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n && abs(strip.get(i).points.second-strip.get(j).points.second)<=Closest.secondclose;j++)
            {
                double s=distance(strip.get(i).points,strip.get(j).points);
                if(s<=Closest.close)
                {
                    Closest.secondclose=Closest.close;
                    Closest.close=s;
                    Closest.secondnearest=Closest.nearest;
                    Closest.nearest=Pair.with(strip.get(i).num, strip.get(j).num);

                }
                else if(s<= Closest.secondclose){
                    Closest.secondclose=s;
                    Closest.secondnearest=Pair.with(strip.get(i).num, strip.get(j).num);
                }
            }
        }
        return Closest;
    }
    Closest BruteForceAlgorithm(Point X[],int n){
        double nearestdistance=INT_MAX;
        double nearestSecondDistance=INT_MAX;
        Pair<Integer,Integer> best=null,second=null;
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++){
                double dist=distance(X[i].points,X[j].points);
                if(dist<=nearestdistance)
                {
                    nearestSecondDistance=nearestdistance;
                    second=best;
                    nearestdistance=dist;
                    best=Pair.with(X[i].num,X[j].num);

                }
                else if(dist<=nearestSecondDistance){
                    nearestSecondDistance=dist;
                    second=Pair.with(X[i].num,X[j].num);
                }
            }
        Closest coordinate = new Closest();
        coordinate.changeValue(nearestdistance,nearestSecondDistance,best,second);
        return coordinate;
    }
    Closest closest(Point X[],Point Y[],int n){
        if(n<=3)
            return BruteForceAlgorithm(X,n);
        int middle=n/2;
        var middlePoint=X[middle].points;
        Point []leftY=new Point[middle+1];
        Point []rightY=new Point [n-middle];
        int Left=0,Right=0;
        for(int i=0;i<n;i++){
            if(Y[i].points.first<middlePoint.first || Y[i].points.first.intValue() == middlePoint.first.intValue() && Y[i].points.second<=middlePoint.second)
                leftY[Left++]=Y[i];
            else
                rightY[Right++]=Y[i];
        }

        Point []middleX = new Point[n-middle-1];

        for(int i = 0;i<middleX.length;i++)
        {
            middleX[i] = X[middle+i+1];
        }


        Closest distanceLeft=closest(X,leftY,middle+1);
        Closest distanceRight=closest(middleX,rightY,n-middle-1);
        Closest d=minimum(distanceLeft,distanceRight);
        Closest used=max(distanceLeft,distanceRight);
        used.secondnearest=used.nearest;
        used.secondclose=used.close;
        Closest secondNearestDistance=secondMinimum(used,secondMinimum(distanceLeft,distanceRight));
        Closest Closest = new Closest();
        Closest.changeValue(d.close,secondNearestDistance.secondclose,d.nearest,secondNearestDistance.secondnearest);
        ArrayList<Point> strip = new ArrayList<>();

        for(int i=0;i<n;i++)
            if(abs(Y[i].points.first-middlePoint.first)<=secondNearestDistance.secondclose)
                strip.add(Y[i]);
        return stripFunctionClosest(strip,Closest);

    }
    static class comY implements Comparator<Point> {
        public int compare(Point x, Point y) {
            return x.points.second - y.points.second;
        }
    }
    static class comX implements Comparator<Point> {
        public int compare(Point x, Point y) {
            return x.points.first < y.points.first || x.points.first.intValue() == y.points.first.intValue() && x.points.second < y.points.second ? -1 : 1;
        }
    }
    public static void main(String[] args) {
        File file = new File("sample.txt");
        try {
            Scanner sc = new Scanner (file);
            int n = sc.nextInt();
            Point []X= new Point [n];
            Point []Y = new Point[n];
            for(int i=0;i<n;i++){
                int x,y;
                x = sc.nextInt();
                y=sc.nextInt();
                X[i] = new Point();
                Y[i] = new Point();
                X[i].points=Pair.with(x,y);
                Y[i].points=Pair.with(x,y);
                X[i].num=Y[i].num=i;
            }
            Arrays.sort(X,new comX());
            Arrays.sort(Y,new comY());
            Closest Closest=new Closest().closest(X,Y,n);
            int firstPoint=Math.min(Closest.secondnearest.second,Closest.secondnearest.first);
            int secondpoint=Math.max(Closest.secondnearest.second,Closest.secondnearest.first);
            System.out.println(firstPoint+ " "+secondpoint);
            System. out. println(String. format("%.4f", Closest.secondclose));
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception occured");
        }
       


    }
}