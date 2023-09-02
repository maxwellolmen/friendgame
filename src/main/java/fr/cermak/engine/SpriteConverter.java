package fr.cermak.engine;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SpriteConverter {

    public static Color hexIntToColor(int hexColorValue) {
        int blue = (hexColorValue >> 16) & 0xFF;
        int green = (hexColorValue >> 8) & 0xFF;
        int red = hexColorValue & 0xFF;

        return new Color(red, green, blue);
    }

    public static void main(String[] args) throws Exception {

        int columns = 14;
        int rows = 6;

        int[] pixels = new int[] {0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0x00000000,
                0x00000000, 0x00000000, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff000000, 0xff1d2436, 0xff000000, 0x00000000,
                0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xffffffff, 0xffffffff, 0xff000000, 0xffffffff, 0xffffffff,
                0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0xff1d2436, 0x00000000,
                0xff1d2436, 0x00000000, 0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0x00000000,
                0x00000000, 0x00000000, 0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0xff1d2436, 0x00000000, 0xff1d2436, 0x00000000};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");

        String name = scanner.nextLine();

        File file = new File("src/main/resources/sprites/" + name.toLowerCase() + ".sprite");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                int orgb = pixels[row * columns + col];

                if (orgb == 0x00000000) {
                    continue;
                }

                int rgb = orgb & 0x00ffffff;

                Color color = hexIntToColor(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                writer.write(col + "," + row + "," + r + "," + g + "," + b + "\n");
            }
        }

        writer.close();
    }
}