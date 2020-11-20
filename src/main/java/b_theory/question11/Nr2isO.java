package b_theory.question11;

public class Nr2isO {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does O stand for in SOLID? Explain the principle. Answer: O stands for open-closed principle, it means /
    // that entities should be open for extension and closed for modification.
    //todo B Give an example. Write actual or pseudo code.
}
//pseudo code
class VolumeCalc{
    private float volume() {
        //returns volume
        return 0;
    }
}
class CubeVolCalc{
    double x;
    double y;
    double z;
    //calculates cube volume
    public  double volume(){
        return x*y*z;
    }
}
class SphereVolCalc{
    double r;
    //calculates cube volume
    public double volume(){
        return 4/3*(3.14*r*r*r);
    }
}
// Other figures can be added to calculate volume