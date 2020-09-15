package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//KeyLıstener= Tuşlara action gönderme
//ActionListener = Eylemin ara birimidir.
public class Play extends JPanel implements KeyListener, ActionListener {



    private int[] snake_X_Length=new int[650];
    private int[] snake_Y_Length=new int[650];

    private boolean left=false;
    private boolean right=false;
    private boolean down=false;
    private boolean up=false;


    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    private ImageIcon snakeimage;

    private int [] enemy_X_Pos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private int [] enemy_Y_Pos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525};
    private ImageIcon enemy;
    private Random random=new Random();
    private int X_Pos=random.nextInt(enemy_X_Pos.length);
    private int Y_Pos=random.nextInt(enemy_Y_Pos.length);
    private int moves,score,score2=0;
    private int stop=moves;


    private int lengthofsnake = 3;
    private int ust_sinir=525;
    private int alt_sinir=25;
    private Timer timer;//saniyede başlattığı eylem dalgası
    private int delay=100;


    public Play(){

        addKeyListener(this);
        setFocusable(true);//
        setFocusTraversalKeysEnabled(false);//shift shift+tab tuşalarının kullanıma açık kapalı olması
        timer=new Timer(delay,this);
        timer.start();



    }




    public void paint (Graphics g){



        if(moves==0)// Snake Başlama noktası
        {
            snake_X_Length[2]=50;
            snake_X_Length[1]=75;
            snake_X_Length[0]=100;

            snake_Y_Length[2]=50;
            snake_Y_Length[1]=50;
            snake_Y_Length[0]=50;

        }
        //çerçevenin dışının rengi
        g.setColor(Color.white);
        g.drawRect(25,25,630,530);
        //Çerçeve içinin rengi
        g.setColor(Color.darkGray);
        g.fillRect(26,26,629,529);

        g.setColor(Color.red);
        g.drawString("Scores = "+score,590,40);


        rightmouth=new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this,g,snake_X_Length[0],snake_Y_Length[0]);


        for (int a=0;a<lengthofsnake;a++)
        {
            if(a==0&&right)
            {
                rightmouth=new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this,g,snake_X_Length[a],snake_Y_Length[a]);

            }
            if(a==0&&left)
            {
                leftmouth=new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this,g,snake_X_Length[a],snake_Y_Length[a]);

            }
            if(a==0&&down)
            {
                downmouth=new ImageIcon("downmouth.png");
                downmouth.paintIcon(this,g,snake_X_Length[a],snake_Y_Length[a]);

            }
            if(a==0&&up)
            {
                upmouth=new ImageIcon("upmouth.png");
                upmouth.paintIcon(this,g,snake_X_Length[a],snake_Y_Length[a]);

            }
            if(a!=0)
            {
                snakeimage=new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snake_X_Length[a],snake_Y_Length[a]);

            }
        }

        enemy=new ImageIcon("enemy.png");
        if(enemy_X_Pos[X_Pos]==snake_X_Length[0]&&enemy_Y_Pos[Y_Pos]==snake_Y_Length[0])
        {
            lengthofsnake++;

            X_Pos=random.nextInt(enemy_X_Pos.length);
            Y_Pos=random.nextInt(enemy_Y_Pos.length);
            score++;
            score2++;
            System.out.println("Score"+score2);

        }
        enemy.paintIcon(this,g,enemy_X_Pos[X_Pos],enemy_Y_Pos[Y_Pos]);

        if(score==100)
        {
            right=false;
            left=false;
            up=false;
            down=false;
            g.setFont(new Font( "SansSerif", Font.PLAIN, 50 ));
            g.drawString("KAZANDINIZ",190,312);
        }
        //KENDİNE ÇARPINCA HATA OLUŞCAK
        for(int b=1;b<lengthofsnake;b++)
        {
            if(snake_X_Length[b]==snake_X_Length[0]&& snake_Y_Length[b]==snake_Y_Length[0])
            {
                right=false;
                left=false;
                up=false;
                down=false;

                g.setFont(new Font( "SansSerif", Font.PLAIN, 50));
                g.drawString("Game Over",150,312);

            }


        }








        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(right)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snake_Y_Length[r+1]=snake_Y_Length[r];
            }
            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snake_X_Length[r]=snake_X_Length[r]+alt_sinir;

                }
                else
                {
                    snake_X_Length[r]=snake_X_Length[r-1];
                }
                /*if(snake_X_Length[r]>625)//duvar açık hali
                {
                    snake_X_Length[r]=alt_sinir;
                }*/
                if(snake_X_Length[r]==625)
                {

                    right=false;
                    left=false;
                    up=false;
                    down=false;

                }

            }
            repaint();//

        }
        if(left)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snake_Y_Length[r+1]=snake_Y_Length[r];
            }
            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snake_X_Length[r]=snake_X_Length[r]-alt_sinir;

                }
                else
                {
                    snake_X_Length[r]=snake_X_Length[r-1];

                }
                /*if(snake_X_Length[r]<alt_sinir) duvar açık hali
                {
                    snake_X_Length[r]=625;

                }*/
                if(snake_X_Length[r]<35)
                {
                    right=false;
                    left=false;
                    up=false;
                    down=false;
                }

            }
            repaint();

        }
        if(up)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snake_X_Length[r+1]=snake_X_Length[r];
            }
            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snake_Y_Length[r]=snake_Y_Length[r]-alt_sinir;

                }
                else
                {
                    snake_Y_Length[r]=snake_Y_Length[r-1];

                }
                if(snake_Y_Length[r]<alt_sinir)
                {
                    snake_Y_Length[r]=ust_sinir;
                }
            }
            repaint();
        }
        if(down)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snake_X_Length[r+1]=snake_X_Length[r];
            }
            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snake_Y_Length[r]=snake_Y_Length[r]+alt_sinir;
                }
                else
                {
                    snake_Y_Length[r]=snake_Y_Length[r-1];
                }
                if(snake_Y_Length[r]>ust_sinir)
                {
                    snake_Y_Length[r]=alt_sinir;
                }
            }
            repaint();

        }






    }

    @Override
    public void keyTyped(KeyEvent e) {

    }//Anahtar girildiğinde çağrılır(basılı tuş)

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;
            right = true;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;
            left = true;
            if(!right)
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;
            up = true;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            moves++;
            down = true;
            if(!up)
            {
                down = true;
            }
            else
            {
                up = true;
                down = false;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_A)
        {
            if(up==true||down==true||right==true||left==true)
            {
                moves=stop;
                score=0;
                lengthofsnake=3;
                repaint();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_F)
        {
            try {
                Thread.sleep(4000,2);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }//Tuşa basıldğında çağrılır
    @Override
    public void keyReleased(KeyEvent e) {

    }//Anahtar bırakıldığında çağrılır

    public void bolum()
    {

    }



}
