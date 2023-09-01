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

        int columns = 8;
        int rows = 22;

        int[] pixels = new int[] {0x00000000, 0xff57a0cc, 0xff57a0cc, 0xff57a0cc, 0xff57a0cc, 0xff57a0cc, 0xff57a0cc, 0x00000000,
                0x00000000, 0xff57a0cc, 0xff948e04, 0xffc6e2ff, 0xffc6e2ff, 0xff948e04, 0xff57a0cc, 0x00000000,
                0x00000000, 0xff57a0cc, 0xffc6e2ff, 0xffc6e2ff, 0xffc6e2ff, 0xffc6e2ff, 0xff57a0cc, 0x00000000,
                0x00000000, 0x00000000, 0xffc6e2ff, 0xff000000, 0xff000000, 0xffc6e2ff, 0x00000000, 0x00000000,
                0x00000000, 0x00000000, 0xffc6e2ff, 0xffc6e2ff, 0xffc6e2ff, 0xffc6e2ff, 0x00000000, 0x00000000,
                0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852,
                0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0xffc6e2ff, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xff4e9852, 0xffc6e2ff,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000, 0x00000000, 0xff5e8bba, 0xff5e8bba, 0x00000000,
                0x00000000, 0xffffffff, 0xffffffff, 0x00000000, 0x00000000, 0xffffffff, 0xffffffff, 0x00000000,
                0x00000000, 0xff4e9852, 0xffffffff, 0x00000000, 0x00000000, 0xffffffff, 0xff4e9852, 0x00000000,
                0xffffffff, 0xffffffff, 0xffffffff, 0x00000000, 0x00000000, 0xffffffff, 0xffffffff, 0xffffffff};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");

        String name = scanner.nextLine();

        File file = new File("src/main/resources/" + name.toLowerCase() + ".sprite");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                int orgb = pixels[row * 8 + col];

                if (((orgb >> 24) & 0x000000ff) < 0xff) {
                    continue;
                }

                int rgb = orgb & 0x00ffffff;

                Color color = hexIntToColor(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                StringBuilder sb = new StringBuilder();

                sb.append(col).append(",").append(row).append(",").append(r).append(",").append(g).append(",").append(b).append("\n");

                writer.write(sb.toString());
            }
        }

        writer.close();
    }
}