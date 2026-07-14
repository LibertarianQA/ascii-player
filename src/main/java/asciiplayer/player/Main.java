package asciiplayer.player;

import asciiplayer.ascii.AsciiConverter;
import asciiplayer.ascii.AsciiFrame;
import asciiplayer.audio.AudioPlayer;
import asciiplayer.ui.AsciiWindow;
import asciiplayer.video.VideoReader;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {

        Path path = Path.of(
                "src", "main", "resources", "videos",
                "badapple.mp4"
        );

        VideoReader reader = new VideoReader(path);
        reader.open();

        AudioPlayer audioPlayer = new AudioPlayer();

        Java2DFrameConverter frameConverter = new Java2DFrameConverter();
        AsciiConverter asciiConverter = new AsciiConverter();

        AsciiWindow window = new AsciiWindow();

        try {

            Frame frame;

            while (window.isOpen() && (frame = reader.grabFrame()) != null) {

                // ---------- AUDIO ----------
                if (frame.samples != null) {
                    audioPlayer.play(frame);
                }

                // ---------- VIDEO ----------
                if (frame.image != null) {

                    BufferedImage image = frameConverter.convert(frame);

                    AsciiFrame asciiFrame = asciiConverter.convert(image);

                    window.render(asciiFrame);

                    Thread.sleep(33);
                }
            }

        } finally {

            window.close();
            audioPlayer.close();
            reader.close();
        }
    }
}