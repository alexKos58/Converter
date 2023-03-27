package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private double ratio;
    private int maxWidth;
    private int maxHeight;

    /**
     * Метод для конвертации изображения в текстовую графику
     *
     * @param url урл изображения
     * @return Строка символов(сконвертированное изображение)
     * @throws IOException
     * @throws BadImageSizeException
     */
    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        //Скачиваем картинку и сохраняем в переменную
        BufferedImage img = ImageIO.read(new URL(url));
        int currentWidth = img.getWidth();
        int currentHeight = img.getHeight();
        int currentRatio = (currentWidth / currentHeight);
        if (currentWidth < currentHeight) {
            currentRatio = (currentHeight / currentWidth);
        }


        //Проверка на максимальное соотношение сторон
        if (currentRatio > ratio) {
            throw new BadImageSizeException(currentRatio, ratio);
        }
        int newWidth = currentWidth;
        int newHeight = currentHeight;
        //проверка на макс высоту
        if (currentWidth > maxWidth) {
            newWidth = maxWidth;
            newHeight = (maxWidth / currentRatio);
        } else if (currentHeight > maxHeight || newHeight > maxHeight) {
            newHeight = maxHeight;
            newWidth = (newHeight * currentRatio);
        }


        ColorSchema schema = new ColorSchema();
        StringBuilder result = new StringBuilder();
        try {
            Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
            BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D graphics = bwImg.createGraphics();
            graphics.drawImage(scaledImage, 0, 0, null);
            WritableRaster bwRaster = bwImg.getRaster();


            for (int i = 0; i < newHeight; i++) {
                for (int j = 0; j < newWidth; j++) {
                    int color = bwRaster.getPixel(j, i, new int[3])[0];
                    char c = schema.convert(color); //создали переменную в которой хранится новый символ после замены
                    result.append(c);
                }
                result.append("\n");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при конвертации");
        }

        return result.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.ratio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {


    }
}
