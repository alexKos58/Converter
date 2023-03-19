package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private final BufferedImage img;
    private double ratio;

    public Converter(BufferedImage img) {
        this.img = img;
    }

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
        double currentWidth = img.getWidth();
        double currentHeight = img.getHeight();
        double currentRatio = (currentWidth / currentHeight);

        //Проверка на максимальное соотношение сторон
        if(currentRatio > ratio){
            throw new BadImageSizeException(currentWidth, currentHeight);
        }

        ColorSchema schema = new ColorSchema();
        StringBuilder result = new StringBuilder();
        final double k_red = 0.299;
        final double k_green = 0.587;
        final double k_blue = 0.114;
        try {
            //TODO преобразование изображения в оттенки серого
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    Color pix = new Color(img.getRGB(j, i));
                    int red = (int) (pix.getRed() * k_red);
                    int green = (int) (pix.getGreen() * k_green);
                    int blue = (int) (pix.getBlue() * k_blue);

                    char c = schema.convert(red + green + blue); //создали переменную в которой хранится новый символ после замены
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

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {
        ratio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
