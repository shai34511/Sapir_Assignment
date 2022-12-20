//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
package Part1;

import java.io.FileWriter;
import java.io.IOException;

public class VehicleLogger {




    public static void write2File(String s) throws IOException {
        FileWriter fw;
        fw = new FileWriter("log.txt", true);
        fw.write(s);
        fw.close();
    }

    public VehicleLogger() throws IOException {

    }
}
