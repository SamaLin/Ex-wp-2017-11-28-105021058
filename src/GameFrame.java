import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame{
    private JPanel background=new JPanel(new BorderLayout(5,5));
//    private JLabel bkimg=new JLabel(new ImageIcon("background.jpg"));
    private JLabel character=new JLabel();
    private boolean charfaced=true;
//    private JPanel charter=new JPanel();
    private Container cp;
    private Timer alert;
    private Timer walkL;
    private Timer walkR;
    private Timer jump;
    private int t1Tmp=1;
//    private int t2Tmp=1;
    private boolean t1Flag=true;
    private ImageIcon charImg[]=new ImageIcon[15];
    //AlertR=0,1,2  AlertL=3,4,5 WalkL=6,7,8,9 WalkR=10,11,12,13 Jump=14;
    public GameFrame(){
        initComp();
    }
    private void initComp(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(200,200,700,500);
        this.setLayout(new BorderLayout(5,5));
        cp=this.getContentPane();
        background.setLayout(null);
        background.setBackground(new Color(255, 166, 85));
        cp.add(BorderLayout.CENTER,background);
        for(int i=0;i<3;i++){
            charImg[i]=new ImageIcon("Character/default/0/alert_"+i+".png");
        }
        for(int i=3;i<6;i++){
            charImg[i]=new ImageIcon("Character/default/0/alert0_"+(i-3)+".png");
        }
        for(int i=6;i<10;i++){
            charImg[i]=new ImageIcon("Character/default/0/walk1_"+(i-6)+".png");
        }
        for(int i=10;i<14;i++){
            charImg[i]=new ImageIcon("Character/default/0/walk0_"+(i-10)+".png");
        }
        charImg[14]=new ImageIcon("Character/default/0/jump_0.png");
        character.setIcon(charImg[0]);
        character.setBounds(350-61,400-79,61,79);
        background.add(character);
        alert=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charfaced==true) {
                    animal(0, 2);
                }else{
                    animal(3 ,5);
                }

            }
        });
        alert.start();
        walkL=new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setIcon(charImg[t1Tmp%4+6]);
                t1Tmp++;
                character.setLocation(character.getX()-7,character.getY());
            }
        });
        walkR=new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.setIcon(charImg[t1Tmp%4+10]);
                System.out.println(t1Tmp%4+10);
                t1Tmp++;
                character.setLocation(character.getX()+7,character.getY());
            }
        });
//        walkL.start();
        jump=new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(character.getY()>400-79) {
                    character.setIcon(charImg[14]);
                    character.setLocation(character.getX(),character.getY()+10);
                }else if(character.getY()<=400-79+90&&character.getY()!=400-79){
                    character.setLocation(character.getX(),character.getY()-10);
                }else if(character.getY()==400-79){
                    jump.stop();
                }

            }
        });

        this.addKeyListener(new KeyListener() {
            boolean keyflag=false;
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyType");
                System.out.println(e.getKeyChar());

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(keyflag==false){
                    if(key==65){
                        alert.stop();
                        walkL.start();
                    }else if(key == e.VK_ENTER){
                        System.out.println("keyPressed");
                        jump.start();
                        //jump
                    }else if(key==68){
                        alert.stop();
                        walkR.start();
                        //right
                    }else if(key==83){

                        //down
                    }else{
                        System.out.println(key);
                    }
                    keyflag=true;
                }
//                System.out.println(e.getKeyCode());
//                System.out.println("keyPressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if(key==65) {
                    charfaced=true;
                    alert.start();
                    walkL.stop();
                    keyflag=false;
                    System.out.println(e.getKeyCode());
                }else if(key == e.VK_ENTER) {

                }else if(key==68){
                    charfaced=false;
                    alert.start();
                    walkR.stop();
                    keyflag=false;
                    System.out.println(e.getKeyCode());
                }
//                System.out.println("keyReleased");
            }
        });
//        background.add(BorderLayout.CENTER,bkimg);
//        charter.setBackground(new Color(255, 172, 134));
//        charter.setOpaque(false);
//        cp.add(BorderLayout.CENTER,charter);


    }
    public void animal(int start,int end){
        if(t1Tmp>end||t1Tmp<start){
            t1Tmp=start+1;
        }
        if(t1Tmp==end||t1Tmp==start){
            t1Flag=!t1Flag;
        }
        if(t1Flag==true) {
            t1Tmp=t1Tmp+1;
//            System.out.println(t1Tmp);
            character.setIcon(charImg[t1Tmp % (end-start)+1+start]);

        }else{
            t1Tmp=t1Tmp-1;
//            System.out.println(t1Tmp);
            character.setIcon(charImg[t1Tmp % (end-start)+1+start]);

        }
    }
    public void  setJump(){
        jump.start();
    }
}
