package github.elmartino4.guncorp.save;

import com.badlogic.gdx.Gdx;
import github.elmartino4.guncorp.config.UserConfig;
import org.json.JSONObject;

import java.io.*;

public class SaveFile {
    String fileName;
    public JSONObject saveData;

    public SaveFile(String fileName) {
        this.fileName = fileName;
    }

    public void begin() throws IOException, ClassNotFoundException {
        String fileDir = UserConfig.prefs.getString("saveDir", "./save/") + fileName;

        File save = new File(fileDir);

        save.getParentFile().mkdirs();

        if (!save.exists()) {
            save.createNewFile();
        } else {
            FileInputStream fis = new FileInputStream(save);

            ObjectInputStream ois = new ObjectInputStream(fis);

            saveData = (JSONObject) ois.readObject();

            ois.close();
            fis.close();
        }
    }

    public void save() throws IOException {
        File save = new File(UserConfig.prefs.getString("saveDir", "./") + fileName);
        save.delete();
        save.createNewFile();

        FileOutputStream fos = new FileOutputStream(save);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(saveData);

        oos.close();
        fos.close();
    }
}
