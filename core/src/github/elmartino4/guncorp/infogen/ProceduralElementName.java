package github.elmartino4.guncorp.infogen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import github.elmartino4.guncorp.map.SafeElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

public class ProceduralElementName {
    OpenSimplexNoise noise;
    FileHandle nounsFile;
    List<Integer> lines = new ArrayList<>();
    Map<SafeElement, String> cache = new HashMap<>();

    public ProceduralElementName(long seed) {
        noise = new OpenSimplexNoise(seed);
        nounsFile = Gdx.files.internal("nounlist.txt");
        InputStream stream = nounsFile.read();
        int r;
        int pos = 0;
        try {
            while ((r = stream.read()) != -1) {
                char ch = (char) r;

                if (ch == '\n')
                    lines.add(pos);

                pos++;
            }
        } catch (Exception e) {

        }

        System.out.println("lines = " + lines.size());

        System.out.println("chars = " + pos);
    }

    public String getName(int M, int N) {
        int index = (int) ((noise.eval(M, N)/2D + 0.5D) * (double)(lines.size() - 2));

        Scanner scanner = new Scanner(nounsFile.reader());

        for (int i = 0; i < index; i++) {
            scanner.nextLine();
        }

        String str = scanner.nextLine();

        str = str.replaceAll("[aeiouyh]*$", "");

        str += "ium";

        System.out.println("str = " + str);

        return str;
    }
}
