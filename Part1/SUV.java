package Part1;

public class SUV extends Part1.Vehicle {

    private String name;
    public SUV(String s){
        super();
        this.name = s;
    }

    @Override
    public String getType() {
        return name;
    }
}
