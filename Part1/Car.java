package Part1;

public class Car extends Part1.Vehicle {

    private String name;
    public Car(String s){
        super();
        this.name = s;
    }


    @Override
    public String getType() {
        return name;
    }
}
