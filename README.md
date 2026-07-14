<p align="center">
  <img src="assets/demo.gif" alt="ASCII Player Demo">
</p>

# ASCII Player (Java)

A simple Java ASCII video player that converts MP4 frames into ASCII art and plays the original audio.

This project is an MVP created to explore video processing, JavaCV (FFmpeg), and console rendering in Java.

---

## Features

- Play MP4 videos
- Convert video frames into ASCII art
- Play synchronized audio
- Java 21
- Gradle build
- JavaCV (FFmpeg)

---

## Technologies

- Java 21
- Gradle
- JavaCV 1.5.13
- FFmpeg (via JavaCV)

---

## Project Structure

```
src/
└── main/
    ├── java/
    │   └── asciiplayer/
    │       ├── ascii/
    │       │   ├── AsciiConverter.java
    │       │   ├── AsciiFrame.java
    │       │   └── CharacterPalette.java
    │       ├── audio/
    │       │   └── AudioPlayer.java
    │       ├── player/
    │       │   └── Main.java
    │       ├── ui/
    │       │   ├── AsciiPanel.java
    │       │   └── AsciiWindow.java
    │       └── video/
    │           └── VideoReader.java
    └── resources/
```

---

## How It Works

1. Open an MP4 video using JavaCV.
2. Decode video frames.
3. Convert each frame into grayscale.
4. Map brightness values to ASCII characters.
5. Render ASCII frames inside a terminal window.
6. Decode and play the original audio simultaneously.

---

## Character Palette

The player currently includes multiple palettes:

- Classic
- Classic Invert
- Blocks
- Simple

New palettes can be added by extending `CharacterPalette`.

---

## Running

Run with Gradle:

```bash
./gradlew run
```

or on Windows:

```powershell
.\gradlew.bat run
```

---

## Requirements

- Java 21+
- Gradle (or Gradle Wrapper)
- UTF-8 terminal recommended

---

## Status

Current version: **MVP**

The basic player is fully functional:

- ✅ Video decoding
- ✅ ASCII rendering
- ✅ Audio playback

---