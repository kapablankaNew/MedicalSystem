package org.kapablankaNew.simpleNeuralNetwork;

import org.jetbrains.annotations.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class PictureConverter {
    private int boundary = 128;

    private int type;

    private int width;

    private int height;

    private final int newWidth;

    private final int newHeight;

    private int groupWidth;

    private int groupHeight;

    private boolean isGrayScale;

    public PictureConverter(int width, int height) {
        this.width = 0;
        this.height = 0;
        groupWidth = 1;
        groupHeight = 1;
        newWidth = width;
        newHeight = height;
        isGrayScale = true;
    }

    public PictureConverter(int width, int height, int boundary) {
        this(width, height);
        isGrayScale = false;
        this.boundary = boundary;
    }

    public List<Double> convertImageToListOfSignals(String path) throws IOException {
        List<Double> result = new ArrayList<>();

        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        width = image.getWidth();
        height = image.getHeight();

        if (width < newWidth || height < newHeight){
            image = zoom(image);
            width = image.getWidth();
            height = image.getHeight();
        }

        if (width > newWidth){
            image = reduceHorizontally(image);
            width = image.getWidth();
        }

        if (height > newHeight){
            image = reduceVertically(image);
            height = image.getHeight();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                result.add(Brightness(color));
            }
        }

        type = image.getType();

        return result;
    }

    private BufferedImage reduceHorizontally(@NotNull BufferedImage image) {
        BufferedImage newImage = new BufferedImage(newWidth, height, image.getType());
        groupHeight = 1;
        int step = image.getWidth() % newWidth;
        for (int y = 0; y < height; y++) {
            groupWidth = image.getWidth() / newWidth;
            int counter = 0;
            for (int x = 0; x < width; x += groupWidth) {
                if (counter == newWidth - step) {
                    groupWidth++;
                }
                Color color = getAverageColor(x, y, image);
                newImage.setRGB(counter, y, color.getRGB());
                counter++;
            }
        }
        return newImage;
    }

    private BufferedImage reduceVertically(@NotNull BufferedImage image) {
        BufferedImage newImage = new BufferedImage(width, newHeight, image.getType());
        groupWidth = 1;
        int step = image.getHeight() % newHeight;
        for (int x = 0; x < width; x++) {
            groupHeight = image.getHeight() / newHeight;
            int counter = 0;
            for (int y = 0; y < height; y += groupHeight) {
                if (counter == newHeight - step) {
                    groupHeight++;
                }
                Color color = getAverageColor(x, y, image);
                newImage.setRGB(x, counter, color.getRGB());
                counter++;
            }
        }
        return newImage;
    }

    private BufferedImage zoom(@NotNull BufferedImage image) {
        BufferedImage newImage = new BufferedImage(Math.max(width, newWidth), Math.max(height, newHeight), image.getType());
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
        return newImage;
    }

    private Color getAverageColor(int x, int y, BufferedImage image) {
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int ch = 0;
        for (int i = x; i < x + groupWidth && i < width; i++) {
            for (int j = y; j < y + groupHeight && j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                sumR += color.getRed();
                sumG += color.getGreen();
                sumB += color.getBlue();
                ch++;
            }
        }
        int R = sumR / ch;
        int G = sumG / ch;
        int B = sumB / ch;
        Color color = new Color(R, G, B);
        return color;
    }

    private double Brightness(Color color) {
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();
        return 0.299 * R + 0.587 * G + 0.114 * B;
    }

    public void save(String path, List<Double> pixels) throws IOException {
        File file = new File(path);
        BufferedImage image = new BufferedImage(width, height, type);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int R, G, B;
                if (isGrayScale) {
                    R = pixels.get(i * height + j).intValue();
                    G = pixels.get(i * height + j).intValue();
                    B = pixels.get(i * height + j).intValue();
                } else {
                    R = pixels.get(i * height + j).intValue() > boundary ? 255 : 0;
                    G = pixels.get(i * height + j).intValue() > boundary ? 255 : 0;
                    B = pixels.get(i * height + j).intValue() > boundary ? 255 : 0;
                }

                Color color = new Color(R, G, B);
                image.setRGB(i, j, color.getRGB());
            }
        }
        ImageIO.write(image, "png", file);
    }
}
