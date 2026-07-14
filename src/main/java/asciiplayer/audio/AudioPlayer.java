package asciiplayer.audio;

import org.bytedeco.javacv.Frame;

import javax.sound.sampled.*;
import java.nio.ShortBuffer;

public class AudioPlayer implements AutoCloseable {

    private SourceDataLine line;
    private boolean initialized = false;

    public void play(Frame frame) {
        try {
            if (frame == null || frame.samples == null) {
                return;
            }

            if (!initialized) {
                init(frame.sampleRate, frame.audioChannels);
            }

            ShortBuffer buffer = (ShortBuffer) frame.samples[0];

            short[] samples = new short[buffer.remaining()];
            buffer.get(samples);

            byte[] pcm = new byte[samples.length * 2];

            int j = 0;
            for (short sample : samples) {
                pcm[j++] = (byte) (sample & 0xFF);
                pcm[j++] = (byte) ((sample >> 8) & 0xFF);
            }

            line.write(pcm, 0, pcm.length);

        } catch (Exception e) {
            throw new RuntimeException("Audio playback failed", e);
        }
    }

    private void init(int sampleRate, int channels) throws LineUnavailableException {

        AudioFormat format = new AudioFormat(
                sampleRate,
                16,
                channels,
                true,
                false
        );

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        initialized = true;
    }

    @Override
    public void close() {

        if (line != null) {
            line.drain();
            line.stop();
            line.close();
        }
    }
}
