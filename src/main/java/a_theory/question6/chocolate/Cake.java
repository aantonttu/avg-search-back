package a_theory.question6.chocolate;

import java.util.List;

public class Cake {

    public enum Sweetness {
        MEDIUM,
        SWEET;
    }

    public enum Size {
        BIG,
        SMALL;
    }

    private Long id;
    private List<String> ingredients;
    private List<String> toppings;
    private Sweetness sweetness;
    private Size size;


    public Cake(Size size, Sweetness sweetness) {
        this.size = size;
        this.sweetness = sweetness;
    }
}
