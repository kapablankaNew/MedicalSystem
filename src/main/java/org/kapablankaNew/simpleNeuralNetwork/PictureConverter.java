package org.kapablankaNew.simpleNeuralNetwork;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class PictureConverter {
    private final double boundary = 128;

    private int type;

    private int width;

    private int height;

    private final int groupSize = 5;

    public PictureConverter(){
        width = 0;
        height = 0;
    }

    public List<Double> convert(String path) throws IOException {
        List<Double> result = new ArrayList<>();

        File file = new File(path);

        BufferedImage image = ImageIO.read(file);

        width = image.getWidth();
        height = image.getHeight();
        for (int i = 0; i < width; i+= groupSize){
            for (int j = 0; j < height; j+= groupSize){
                Color color = getAverageColor(i, j, image);
                result.add(Brightness(color));
            }
        }

        int ch_1 = width%groupSize == 0 ? 0 : 1;
        int ch_2 = height%groupSize == 0 ? 0 : 1;
        width = width/groupSize + ch_1;
        height = height/groupSize + ch_2;
        type = image.getType();

        return result;
    }

    private Color getAverageColor(int x, int y, BufferedImage image){
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int ch = 0;
        for (int i = x; i < x + groupSize && i < width; i++){
            for (int j = y; j < y + groupSize && j < height; j++){
                Color color = new Color(image.getRGB(i, j));
                sumR += color.getRed();
                sumG += color.getGreen();
                sumB += color.getBlue();
                ch++;
            }
        }
        int R = sumR/ch;
        int G = sumG/ch;
        int B = sumB/ch;
        Color color = new Color(R, G, B);
        return color;
    }

    private double Brightness(Color color){
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();
        return 0.299*R + 0.587*G + 0.114*B;
    }

    public void save (String path, List<Double> pixels) throws IOException {
        File file = new File(path);
        BufferedImage image = new BufferedImage(width, height, type);
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int R = pixels.get(i*height + j).intValue();// > boundary ? 255 : 0;
                int G = pixels.get(i*height + j).intValue();// > boundary ? 255 : 0;
                int B = pixels.get(i*height + j).intValue();// > boundary ? 255 : 0;
                Color color = new Color(R, G, B);
                image.setRGB(i, j, color.getRGB());
            }
        }
        ImageIO.write(image, "png", file);
    }
}
