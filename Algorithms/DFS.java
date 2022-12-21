/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class DFS {
    public static boolean searchPath(int[][] maze,int x,int y,List<Integer> path){
        if(maze[x][y]==9){
            //System.out.println("x:"+x+"y:"+y);
            return true;
        }
        if(maze[x][y]==0){
            maze[x][y]=2;
            
            int[] dx={1,0,-1,0};
            int[] dy={0,-1,0,1};
            
            for(int d=0;d<4;d++){
                int newx=x+dx[d];
                int newy=y+dy[d];
                
                if(newx>=0 && newy>=0 && newx<maze.length && newy<maze[0].length && searchPath(maze,newx,newy,path)){
                    System.out.println(newx+" "+newy);
                    path.add(newx);
                    path.add(newy);
                    return true;
                }
            }
      
        }
        return false;
    }
    public static void main(String[] args){
        DFS dfs=new DFS();
        int[][] maze = 
        {    
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
        List<Integer> path=new ArrayList<>();
        boolean reach=dfs.searchPath(maze,1,1,path);
        System.out.println(reach); 
    }
}
