//swea 1251. 하나로
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static class island{ //섬의 위치 정보를 저장
        double x,y; //섬의 좌표 저장
        island(){};

        double distnace(island o){ //유클리드 공식을 이용한 두 점 사이의 거리를 구하는 메서드
            return Math.sqrt(Math.pow(this.x-o.x, 2)+Math.pow(this.y-o.y, 2));
        }
    }

    static class Edge implements Comparable<Edge> { //간선의 정보를 저장
        int from,to;
        double weight;

        Edge(int from,int to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) { //가중치를 이용하여 오름차 순 정렬
            // TODO Auto-generated method stub
            return Double.compare(this.weight, o.weight);
        }
        
    
        
    }

    static int V; //섬의 개수(정점의 개수)
    static double E; //환경 부담 세율
    static int[] parents; //각 정점의 부모 저장.
    static island[] islandList; //섬의 좌표 저장 배열
    // static ArrayList<Edge> edgeList; 
    static PriorityQueue<Edge> edgeList; //간선의 정보를 저장하는 큐
    static int[] visited = new int[2]; //조합을 위한 visited배열

    static void make(){ //부모 배열 초기화
        for(int i=0; i<V; i++){
            parents[i]=i;
        }
    }

    static int find(int a){ //정점 a의 최상위 부모(루트)를 찾음
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){ //두 정점 a와 b를 하나의 집합으로 합침.
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false; //이미 같은 집합이면 사이클 발생으로 false
        parents[bRoot] = aRoot; //다른 집합이라면 하나로 합치고 true
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc<=T; tc++){
            
            V = Integer.parseInt(br.readLine());
            parents = new int[V];
            make(); //부모 배열 초기화
            islandList = new island[V];
            for(int i=0; i<V; i++){ //섬 좌표 배열 초기화.
                islandList[i] = new island();
            }

            // 섬들의 x좌표와 y좌표를 각각 입력받음
            StringTokenizer stX = new StringTokenizer(br.readLine());
            StringTokenizer stY = new StringTokenizer(br.readLine());
            for(int i=0; i<V; i++){
                islandList[i].x = Integer.parseInt(stX.nextToken());
                islandList[i].y = Integer.parseInt(stY.nextToken());
            }
            
            E = Double.parseDouble(br.readLine());
            

            // edgeList = new ArrayList<>();
            edgeList = new PriorityQueue<>(); //모든 간선을 저장할 우선 순위 큐 생성

            comb(0, 0); //조합을 이용해 모든 섬들을 쌍으로 묶어 간선을 생성하고 큐에 추가.
            // Collections.sort(edgeList);
            int cnt=0; //선택 된 간선 수
            double total_weight=0; // 총 환경 부담금(MST의 총 가중치)
            int n = V-1; //MST 는 V-1개의 간선으로 구성.
            // for(Edge e : edgeList){
            //     if(union(e.from, e.to)){
            //         total_weight += e.weight;
            //         if(++cnt == n) break;
            //     }
            // }
            while (!edgeList.isEmpty()) {
                
                Edge now =edgeList.poll(); //가장 가중치가 낮은 간선을 꺼냄
                if(union(now.from,now.to)) { //두섬이 아직 연결되어 있지 않다면
                    total_weight += now.weight; //해당 간선을 선택하고 비용 누적
                    if(++cnt==n) break; //다 찾으면 종료
                }
                
            }
            
            System.out.printf("#%d %d\n",tc,Math.round(total_weight));

        }
    }

    static void comb(int cnt, int start){
        if(cnt == 2){ //2개의 섬이 선택 되었을때
            int from = visited[0];
            int to = visited[1];
            //두섬 사이의 거리
            double distance = islandList[from].distnace(islandList[to]);

            //두섬 사이의 비용
            double weight = Math.pow(distance, 2) *E;

            //간선 정보를 큐에 추가.
            edgeList.offer(new Edge(from, to, weight));
            return;
        }
        //재귀를 활용한 조합
        for(int i=start; i<V; i++){
            visited[cnt]=i;
            comb(cnt+1, i+1);
        }
    }
}
