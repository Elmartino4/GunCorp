package github.elmartino4.guncorp.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import github.elmartino4.guncorp.config.UserConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSaveFile {
    String fileName;
    FileHandle save;
    public Connection conn;

    public DataSaveFile(String fileName) {
        this.fileName = fileName;
    }

    public void begin() throws IOException, SQLException {
        String fileDir = "./.gunCorp/" + UserConfig.prefs.getString("saveDir", "save/") + fileName;

        this.save = Gdx.files.external(fileDir);

        if (!save.exists()) {
            save.write(false);
        }

        conn = DriverManager.getConnection("jdbc:sqlite:" + this.save.file().getAbsolutePath());

        System.out.println("created conn");
    }

    public void save() throws SQLException {
        conn.close();
    }
}
