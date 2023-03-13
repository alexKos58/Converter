package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    /**
     *
     * @param color - интенсивность цвета пикселя
     * @return символ подходящий для замены
     */
    @Override
    public char convert(int color) {
        char[] arr = {'#', '$', '@', '%', '*', '+', '-', '\''};
        return arr[(color/31) - 1];//возвращаем элемент подходящий для замены
    }
}
