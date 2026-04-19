import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point>{
        int x,y;
        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public int compareTo(Point o) {
            return this.x-o.x;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];
        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x,y);

        }
        Arrays.sort(points);
        long res=0;
        int start = points[0].x;
        int end = points[0].y;
        for(int i=1; i<N; i++){
            if(points[i].x<=end){
                end = Math.max(end, points[i].y);
            }else{
                res+=(long)(end-start);
                start = points[i].x;
                end = points[i].y;
            }
        }
        res +=(long)(end-start);
        System.out.println(res);
    }
}
