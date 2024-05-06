package Part1;

public class MiniBus extends Part1.Vehicle {

    private String name;
    public MiniBus(String s){
        super();
        this.name = s;
    }

    @Override
    public String getType() {
        return name;
    }
}
