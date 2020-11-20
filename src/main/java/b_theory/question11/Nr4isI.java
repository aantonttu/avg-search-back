package b_theory.question11;

public class Nr4isI {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does I stand for in SOLID? Explain the principle.
    // I stands for Interface segregation principle and means that client should not depend on methods it does not use.
    // This principle splits large interface into small ones and this gives client opportunity to use and understand only those methods which are needed.
    //
    //todo B Give an example. Write actual or pseudo code.
}

// bad example
interface ShapeInterface {
    public float area();
    public float volume();
}

// good example
interface ShapeAreaInterface {
    public float area();
}

interface ShapeVolumeInterface {
    public float volume();
}

class Cuboid implements ShapeAreaInterface, ShapeVolumeInterface {

    @Override
    public float area() {
        return 0;
    }

    @Override
    public float volume() {
        return 0;
    }
}
