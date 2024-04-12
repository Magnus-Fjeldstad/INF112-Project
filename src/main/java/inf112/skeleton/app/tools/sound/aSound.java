package inf112.skeleton.app.tools.sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class aSound {
    private Music sound;
    private String fileString;

    public aSound(String fileString) {
        this.fileString = fileString;
        sound = Gdx.audio.newMusic(Gdx.files.internal(fileString));
    }

    public String getFileName() {
        return fileString;
    }

    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public void dispose() {
        sound.dispose();
    }
}
