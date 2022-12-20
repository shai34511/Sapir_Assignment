//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
package Part1;

import java.util.ArrayList;
public class VehicleArray <T> {

    private ArrayList<T> arr;

    public VehicleArray() {
        arr = new ArrayList<T>();
    }

    public int getSize(){
        return this.arr.size();
    }
    public void add2End(T v) {
        arr.add(v);
    }

    public T removeFirst(T v) {
        return arr.remove(0);
    }

    public void deleteByParameter(T v) {
        arr.remove(v);
    }

    @Override
    public String toString() {
        return "VehicleArray{" +
                "arr=" + arr +
                '}';
    }
}