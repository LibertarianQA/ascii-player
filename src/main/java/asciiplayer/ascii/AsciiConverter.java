package asciiplayer.ascii;

import java.awt.image.BufferedImage;

public class AsciiConverter {

    private static final int X_STEP = 3;
    private static final int Y_STEP = 6;

    private static final String PALETTE = CharacterPalette.CLASSIC_INVERT;

    public AsciiFrame convert(BufferedImage image) {

        int width = (image.getWidth() + X_STEP - 1) / X_STEP;
        int height = (image.getHeight() + Y_STEP - 1) / Y_STEP;

        char[] chars = new char[width * height];

        int i = 0;

        for (int y = 0; y < image.getHeight(); y += Y_STEP) {

            for (int x = 0; x < image.getWidth(); x += X_STEP) {

                int rgb = image.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int brightness = (r + g + b) / 3;

                int index = (255 - brightness) * (PALETTE.length() - 1) / 255;

                chars[i++] = PALETTE.charAt(index);
            }
        }

        return new AsciiFrame(chars, width, height);
    }
}