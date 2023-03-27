package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {
    private char[] arr = {'#', '$', '@', '%', '*', '+', '-', '\''};

    /**
     * @param color - интенсивность цвета пикселя
     * @return символ подходящий для замены
     */
    @Override
    public char convert(int color) {
        final int count = 256 / arr.length;
        return arr[(color / count)];//возвращаем элемент подходящий для замены
    }
}
