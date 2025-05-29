//package odev4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Game extends JFrame implements ActionListener, KeyListener{

    char move='.';
    OurCharacter efe;

    int index=0;

    boolean devam=true;

    boolean hamle=true;
    ArrayList<Integer> xler = new ArrayList<>();
    ArrayList<Integer> yler = new ArrayList<>();

    //int monsterX;
    //int monsterY;

    public Game()
    {
        efe = new OurCharacter(250,250);

        JPanel panel = new JPanel();
        panel.setSize(500,500);
        add(panel);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);

        setVisible(true);
    }



    public static void main(String[] args) {

        int number_of_monsters = Integer.parseInt(args[0]);
        System.out.println(number_of_monsters);
        Game m = new Game();

        Game.Monster [] monsters = new Game.Monster[number_of_monsters];

        Random r = new Random();

        for(int i=0;i<number_of_monsters;i++)
        {
            monsters[i] = m.new Monster(Math.abs(r.nextInt()%500),Math.abs(r.nextInt()%500));
        }

        for(int i=0;i<number_of_monsters;i++)
            monsters[i].start();

        try {
            for(int i=0;i<number_of_monsters;i++)
                monsters[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);


        for (int i = 0; i < xler.size(); i++) {

            //System.out.println(xler.get(i));
            //System.out.println(yler.get(i));

            //System.out.println(efe.getOurX() + " efe");
            //System.out.println(efe.getOurY() + " efe");
            if(Math.abs(xler.get(i)- efe.getOurX())<=20 && Math.abs(yler.get(i)- efe.getOurY())<=20)
            {
                devam=false;
            }

        }

        if(devam) {

            if (move == ' ') {
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
            }
            if (move == '.') {
                move = ' ';
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
            }
            if (move == 'w' || move == 'W') {
                move = ' ';
                if (efe.getOurY() > 30) {
                    efe.setOurY(efe.getOurY() - 10);
                }
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
                hamle = false;
            }
            if (move == 's' || move == 'S') {
                move = ' ';
                if (efe.getOurY() < 470) {
                    efe.setOurY(efe.getOurY() + 10);
                }
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
                hamle = false;
            }
            if (move == 'a' || move == 'A') {
                move = ' ';
                if (efe.getOurX() > 10) {
                    efe.setOurX(efe.getOurX() - 10);
                }
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
                hamle = false;
            }
            if (move == 'd' || move == 'D') {
                move = ' ';
                if (efe.getOurX() < 470) {
                    efe.setOurX(efe.getOurX() + 10);
                }
                g.setColor(Color.green);
                g.fillRect(efe.getOurX(), efe.getOurY(), 20, 20);
                hamle = false;
            }


            for (int i = 0; i < xler.size(); i++) {
                g.setColor(Color.blue);
                g.fillRect(xler.get(i), yler.get(i), 20, 20);
            }


            hamle = true;
        }
        else{
            g.setColor(Color.green);
            g.fillRect(efe.getOurX(), efe.getOurY(), 20,20);
            g.setColor(Color.blue);
            for (int i = 0; i < xler.size(); i++) {

                g.fillRect(xler.get(i),yler.get(i),20,20);

            }
            System.exit(0);
        }

        }


    @Override
    public void keyTyped(KeyEvent e) {

        //move=e.getKeyChar();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        move=e.getKeyChar();
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
    class Monster extends Thread{

        private int x;
        private int y;

        public Monster(int x,int y)
        {
            this.x=x;
            this.y=y;

        }


        /*public void start()
        {
            super.start();
        }*/


        @Override
        public void run() {

            xler.add(x);
            yler.add(y);

            while(devam) {
                repaint();
                    double durum = Math.random();

                    if((efe.getOurX()> x) && durum<=0.5)
                    {
                       xler.set(xler.indexOf(x),x+10);
                       setX(x+10);
                    }
                    else if((efe.getOurX()<x) && durum<=0.5)
                    {
                        xler.set(xler.indexOf(x),x-10);
                        setX(x-10);
                    }
                    else if((efe.getOurY()>y) && durum > 0.5)
                    {
                        yler.set(yler.indexOf(y), y+10);
                        setY(y+10);
                    }
                    else if((efe.getOurY()<y) && durum > 0.5)
                    {
                        yler.set(yler.indexOf(y), y-10);
                        setY(y-10);
                    }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                }
                //repaint();

            }



        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    class OurCharacter{

        private int OurX;
        private int OurY;

        public OurCharacter(int ourX, int ourY)
        {
            this.OurX=ourX;
            this.OurY=ourY;
        }

        public int getOurX() {
            return OurX;
        }

        public int getOurY() {
            return OurY;
        }

        public void setOurX(int ourX) {
            OurX = ourX;
        }

        public void setOurY(int ourY) {
            OurY = ourY;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //repaint();
    }
}
