import java.util.*;
import java.util.stream.*;

class Solution {
    
    private int[] tree;
    private boolean[] visited;
    private int[][] computers;
    private int n;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        init(n, computers); 
        
        for(int i=0; i<n; i++) connect(i);
        
        Arrays.stream(tree).forEach(System.out::println);
        
        for(int i=0; i<n; i++){
            if(tree[i] < 0) answer++;
        }
        
        return answer;
    }
    
    private void connect(int x){
        for(int i=0; i<n; i++){
            if(computers[x][i]==1){
                union(x, i);
            }
        }
    }
    
    // 정점 x의 부모가 누구냐
    private int find(int x){
        if(tree[x] < 0) return x;
        return tree[x] = find(tree[x]); // x의 부모에서 찾음과 동시에, 갱신
    }
    
    private void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        
        // 부모가 같음 = 이미 같은 그룹
        if(xRoot == yRoot) return;
        
        // y의 트리가 더 높을 때
        if(tree[xRoot] > tree[yRoot]){
            // swap
            int tmp = yRoot;
            yRoot = xRoot;
            xRoot = tmp;
        }
        
        // x, y 트리의 높이가 같을 경우
        if(tree[xRoot] == tree[yRoot]){
            tree[xRoot]--;
        }
        
        // 높이가 너 낮은 쪽이, 높은 트리 밑으로 들어가야 함
        tree[yRoot] = xRoot;
    }
    
    private void init(int n, int[][] computers){
        this.n = n;
        this.tree = new int[n];
        this.computers = computers;
        
        Arrays.fill(this.tree, -1);
    }
}