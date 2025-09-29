import java.util.*;
import java.io.*;

class Time implements Comparable<Time>{
    int start;
    int end;

    public Time(int start, int end)
    {
        this.start=start;
        this.end= end;
    }

    @Override
    public int compareTo(Time o) {
        if(this.start != o.start) {
            return Integer.compare(this.start, o.start);
        }
        return Integer.compare(this.end,o.end);
    }

    @Override
    public String toString() {
        return start + " " +end;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());


        List <Time> time = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            String [] line = br.readLine().trim().split(" ");

            time.add(new Time(Integer.parseInt(line[0]),Integer.parseInt(line[1])));

        }
        Collections.sort(time);

        PriorityQueue<Integer> timetable=new PriorityQueue<>();

        for (Time t : time)
        {
            if(!timetable.isEmpty()&& t.start>=timetable.peek())
            {
                timetable.poll();
            }
            timetable.add(t.end);

        }
        System.out.println(timetable.size());



    }
}