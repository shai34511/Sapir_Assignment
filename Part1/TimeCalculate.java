//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
package Part1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculate {
    static TimeCalculate time = new TimeCalculate();
    static double temp2;
    static int count = 0;
    public Double castDouble() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  //variable initialization
        LocalDateTime now = LocalDateTime.now();
        String time = (dtf.format(now));

        double timeFromString;
        String temp = "";
        for (int i = 0; i < time.length(); i++) {       // adding the time of every thread in order to sum it eventually

            if (time.charAt(i) != ':') {
                temp += time.charAt(i);
            }
        }
        timeFromString = Double.parseDouble(temp);
        if (count > 0){
            if (timeFromString > (temp2+30) ){    // a condition that prevent the time from being corrupted
                timeFromString -= 41;
            }
        }
        temp2 = timeFromString;
        count++;
        return timeFromString;


    }
}
