package asciiplayer.ascii;

public final class AsciiFrame {

    private final char[] chars;
    private final int width;
    private final int height;

    public AsciiFrame(char[] chars, int width, int height) {
        this.chars = chars;
        this.width = width;
        this.height = height;
    }

    public char[] getChars() {
        return chars;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}