//shai susana 208067950, yinon levy 312219512, ziv rahamim 311303622
package Part1;

public class Truck extends Part1.Vehicle {

    private String name;
    public Truck(String s){
        super();
        this.name = s;
    }

    @Override
    public String getType() {
        return name;
    }
}
