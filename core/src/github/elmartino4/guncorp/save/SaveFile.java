package github.elmartino4.guncorp.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import github.elmartino4.guncorp.config.UserConfig;
import org.json.JSONObject;

import java.io.*;

public class SaveFile {
    String fileName;
    FileHandle save;
    public JSONObject saveData = new JSONObject();

    public SaveFile(String fileName) {
        this.fileName = fileName;
    }

    public void begin() throws IOException, ClassNotFoundException {
        String fileDir = UserConfig.prefs.getString("saveDir", "./save/") + fileName;

        this.save = Gdx.files.internal(fileDir);

        if (!save.exists()) return;
        if (save.read().available() == 0) return;

        ObjectInputStream ois = new ObjectInputStream(save.read());

        saveData = (JSONObject) ois.readObject();

        ois.close();
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(save.write(false));

        oos.writeObject(saveData);

        oos.close();
    }
}
