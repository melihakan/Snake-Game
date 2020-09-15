package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {


        JFrame obj=new JFrame();
        Play play= new Play();

        obj.setBounds(400,85,700,625);
        obj.setBackground(Color.black);
        obj.setResizable(false);// Pencereyi yeniden boyutlandırma
        obj.setVisible(true);// Pencereyi açma
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Pencereyi kapatınca koduda durdurur
        obj.add(play);

    }
}
