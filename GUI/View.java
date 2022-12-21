/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import Algorithms.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class View extends JFrame implements ActionListener,MouseListener{
    
   /* values = 0:not visited
            1: blocked wall
            2:visited
            9:target*/
    
    private int[][] maze = 
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
    private int[] target={8,11};
    private List<Integer> path=new ArrayList<>();
    //create buttons
    JButton submitButton;
    JButton cancelButton;
    JButton clearButton;

    public View(){
        //for title of gui window
        this.setTitle("Maze Solver");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //to center isself in the middle of the screen
        this.setLocationRelativeTo(null);
        
        submitButton=new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(120, 400, 80, 30);
        
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(230, 400, 80, 30);
        
        clearButton=new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(330, 400, 80, 30);
        
        this.addMouseListener(this);
        
        this.add(submitButton);
        this.add(clearButton);
        this.add(cancelButton);
        
        
        
        
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int rows=0;rows<maze.length;rows++){
            for(int cols=0;cols<maze[0].length;cols++){
                Color color;
                switch(maze[rows][cols]){
                    case 1:color=Color.BLACK; break;
                    case 9:color=Color.red; break;
                    default: color=Color.WHITE; break;
                }
            
                g.setColor(color);
                g.fillRect(40*cols, 40*rows, 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(40*cols, 40*rows, 40, 40);
            }
           
        }
        
        for(int p=0;p<path.size();p+=2){
            
            int pathX=path.get(p);
            int pathY=path.get(p+1);
            System.out.println(pathX+", "+pathY);
            g.setColor(Color.GREEN);
            g.fillRect(40*pathY, 40*pathX, 40, 40);
        }
    }
    
    public static void main(String[] args){
        View v=new View();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            System.out.println("submit");
            try{
                DFS.searchPath(maze, 1, 1, path);
                for(int i=0;i<path.size();i++){
                    System.out.println(path.get(i));
                }
                this.repaint();
            }
            catch(Exception ep){
             JOptionPane.showMessageDialog(null, ep.toString());

            }
        }
        if(e.getSource()==clearButton){
            path.clear();
            for(int rows=0;rows<maze.length;rows++){
            for(int cols=0;cols<maze[0].length;cols++){
                if(maze[rows][cols]==2){
                    maze[rows][cols]=0;
                }
            }
            }
            this.repaint();
        }
        
        if(e.getSource()==cancelButton){
            int flag=JOptionPane.showConfirmDialog(null, "ADo you want to quit", "Submit", JOptionPane.YES_NO_OPTION);
            if(flag==0){
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX()>=0 && e.getX()<=maze[0].length*40 && e.getY()>=0 && e.getY()<=40*maze.length)
        {
            int row=e.getY()/40;
            int col=e.getX()/40;
            
            if(maze[row][col]==1){
                return;
            }
            Graphics g=getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(40*target[1], 40*target[0], 40, 40);
            g.setColor(Color.red);
            g.fillRect(40*col, 40*row, 40, 40);
            maze[target[0]][target[1]]=0;
            maze[row][col]=9;
            target[0]=row;
            target[1]=col;
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
