import java.util.stream.*;
import java.util.*;

class Solution {
    // 두 개의 단어 begin , target
    // 단어의 집합 words
    
    // begin 에서 target 으로 변하는 가장 짧은 변환 과정을 찾아야 함
        // 1. 한 번에 한 개의 알파벳만 바꿀 수 있음
        // 2. words에 있는 단어로만 변환할 수 있음
    
    // begin 에서 갈 수 있는 다음 단어들의 연결을 찾아야 함
    // 그리고 몇 번 만에 갈 수 있는지 세면 됨 DFS or BFS
    
    private String begin, target;
    private List<String> words;
    private int[][] graph;
    private boolean[] visited;
    private boolean isContains;
    
    public int solution(String begin, String target, String[] words) {
        init(begin, target, words);
        
        if(!this.isContains) return 0;
        
        System.out.println(String.format("init: %s", this.words.toString()));
        
        findGraph();
        
        for(int i =0; i<graph.length; i++){
            for(int j=0; j<graph[0].length; j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
        
        int answer = bfs(0);
        
        return answer;
    }
    
    private int bfs(int x){
        int minDistance = Integer.MAX_VALUE;
        int length = this.words.size();
        Deque<int[]> q = new ArrayDeque<>();
        visit(x, 0, q);
        
        while(!q.isEmpty()){
            int[] current = q.poll();
            int currentX = current[0];
            int currentDist = current[1];
            
            for(int i=0; i<length; i++){
                if(i == currentX || graph[currentX][i] != 1 || visited[i]) continue;
                
                // currentX 에서 다음 i로 갈 수 있으면
                visit(i, currentDist+1, q);
                
                // 다음이 target 이면 갱신
                if(i == length-1){
                    minDistance = Math.min(minDistance, currentDist+1);
                }
            }
        }
        
        // 결과 반환
        return minDistance == Integer.MAX_VALUE ? 0 : minDistance;
    }
    
    private void visit(int x, int dist, Deque<int[]> q){
        q.offer(new int[]{x, dist});
        visited[x] = true;
    }
    
    private boolean isValid(int from, int to){
        int length = this.words.size();
        return 0 <= from && from < length
                && 0 <= to && to < length;
    }
    
    // 인접 그래프 제작
    private void findGraph(){
        // 철자 차이가 1이면 인접해있는 것으로 봄  
        int length = this.words.size();
        
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if(i==j) {
                    graph[i][j] = 1;
                    continue;
                }
                
                String a = this.words.get(i);
                String b = this.words.get(j);
                
                // a , b 의 철자 차이 계산
                if(isAdj(a, b)){
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
    }
    
    // 두 word 가 인접한지 판단
    private boolean isAdj(String a, String b){
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        
        int cnt = 0;
        
        for(int i=0; i<aChars.length; i++){
            if(aChars[i] != bChars[i]) cnt++;
        }
        
        return cnt == 1;
    }
    
    private void init(String begin, String target, String[] words){
        this.isContains = Arrays.stream(words).toList().contains(target);
        this.begin = begin;
        this.target = target;
        this.words = new ArrayList<>();
        
        this.words.add(begin); // 시작 단어 추가
        
        for(String s: words) this.words.add(s); // 나머지 단어 차례로 추가
        
        // 타깃이 포함되면
        if(this.isContains) {
            this.words.remove(target);
            this.words.add(target); // 타깃은 맨 마지막에   
        }
        
        this.graph = new int[this.words.size()][this.words.size()];
        this.visited = new boolean[this.words.size()];
    }
}