package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter();// Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
//        String url = "https://sun9-74.userapi.com/impg/Dwqjd7JegD3So4Ogq21lLkgEz7sMtelbCdk3Iw/hCDByw_8CXE.jpg?size=690x338&quality=96&sign=c70d0cb30aa45f97672d54a74465ff70&type=album";
////        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
