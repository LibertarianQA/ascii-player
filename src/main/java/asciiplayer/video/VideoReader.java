package asciiplayer.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.ffmpeg.global.avutil;
import java.nio.file.Path;

public class VideoReader {

    private final Path videoPath;
    private FFmpegFrameGrabber grabber;

    public VideoReader(Path videoPath) {
        this.videoPath = videoPath;
    }

    public void open() throws Exception {

        grabber = new FFmpegFrameGrabber(videoPath.toString());

        // ---------- VIDEO ----------
        grabber.setPixelFormat(avutil.AV_PIX_FMT_BGR24);

        // ---------- AUDIO ----------
        grabber.setAudioChannels(2);
        grabber.setSampleFormat(avutil.AV_SAMPLE_FMT_S16);

        grabber.start();
    }

    public Frame grabFrame() throws FFmpegFrameGrabber.Exception {
        return grabber.grab();
    }

    public int getSampleRate() {
        return grabber.getSampleRate();
    }

    public int getAudioChannels() {
        return grabber.getAudioChannels();
    }

    public void close() throws Exception {
        if (grabber != null) {
            grabber.stop();
            grabber.close();
        }
    }
}