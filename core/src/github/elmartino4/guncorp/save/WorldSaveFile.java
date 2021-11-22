package github.elmartino4.guncorp.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import github.elmartino4.guncorp.config.UserConfig;
import org.json.JSONObject;

import java.io.*;

public class WorldSaveFile {
    String fileName;
    FileHandle save;
    public SNBTObject saveData = new SNBTObject();

    public WorldSaveFile(String fileName) {
        this.fileName = fileName;
    }


    public void begin() throws IOException, ClassNotFoundException {
        String fileDir = "./.gunCorp/" + UserConfig.prefs.getString("saveDir", "save/") + fileName;

        this.save = Gdx.files.external(fileDir);

        if (!save.exists()) return;
        if (save.read().available() == 0) return;

        ObjectInputStream ois = new ObjectInputStream(save.read());

        saveData = ((SNBTObject) ois.readObject());

        ois.close();
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(save.write(false));

        oos.writeObject(saveData);

        oos.close();
    }
}
