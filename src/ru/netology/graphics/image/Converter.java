package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {
    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        //Скачиваем картинку и сохраняем в переменную
        BufferedImage img = ImageIO.read(new URL(url));
        try {

            //TODO преобразование изображения в оттенки серого
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    Color pix = new Color(img.getRGB(j, i));
                    int red = (int) (pix.getRed() * 0.299);
                    int green = (int) (pix.getGreen() * 0.587);
                    int blue = (int) (pix.getBlue() * 0.114);

                    Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
//                    newColor.getAlpha() получаем значение интенсивности пикселя, в зависимости от него мы меняем его на определенный символ

                    img.setRGB(j, i, newColor.getRGB());

                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public void setMaxWidth(int width) {

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {

    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}