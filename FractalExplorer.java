package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer extends JFrame {
    private int size;
    private JImageDisplay imageDisplay;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(400);
        fractalExplorer.creatAndShowGUI();
        fractalExplorer.drawFractal();
    }


    FractalExplorer(int size){
        this.size = size;
        range = new Rectangle2D.Double();
        fractal = new Mandelbrot();
        fractal.getInitialRange(range);
        imageDisplay = new JImageDisplay(size,size);
    }

    private void creatAndShowGUI(){
        JFrame frame = new JFrame("Fractal");
        JButton button = new JButton("Reset");
        ResetEvent resetEvent = new ResetEvent();
        MouseHandler mouseHandler = new MouseHandler();

        imageDisplay.addMouseListener(mouseHandler);
        button.addActionListener(resetEvent);

        imageDisplay.setLayout(new BorderLayout());

        frame.add(imageDisplay,BorderLayout.CENTER);
        frame.add(button,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                double xCoord = FractalGenerator.getCoord(range.x,range.x + range.width, size, i);
                double yCoord = FractalGenerator.getCoord(range.y,range.y + range.height, size, j);
                int iter = fractal.numIterations(xCoord,yCoord);
                if (iter == -1)imageDisplay.drawPixel(i,j,0);
                else {
                    float hue = 0.7f + (float)iter / 200f;
                    imageDisplay.drawPixel(i,j,Color.HSBtoRGB(hue,1f,1f));
                }
            }
        }
        imageDisplay.repaint();
    }

    private class ResetEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, size, y);
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

}
