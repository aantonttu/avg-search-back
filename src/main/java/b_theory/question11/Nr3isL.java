package b_theory.question11;


import java.util.ArrayList;
import java.util.List;

public class Nr3isL {
    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does L stand for in SOLID? Explain the principle.
    //todo B Give an example. Write actual or pseudo code.
    // A ->
    //  L - Liskov substitution principle
    // Substitutability is a principle in object-oriented programming stating that, in a computer program, if S is a subtype of T,
    // then objects of type T may be replaced with objects of type S without altering any of the desirable properties of the program.
    // B -> People creation example (lets play God role)
    // We have standard class of person creation and class of black person creation(niga)
    // Black_person is subtype of Person and all black_person objects can be replaced with whit.. just simple person objects.
    // example with pseudo code

//    private class Person {
//        private int height = random(50, 250);
//        private int beauty = random(0, 100);
//        private int iq = random(60, 200);
//
//        private String eyes_color = random('green', 'blue', 'brown');
//        private String hair_color = random('blond', 'black', 'brown');
//        private String skin_color = random('white, black, yelow');
//
//        private String name;
//
//        private void setName(String name) {
//            this.name = name;
//        }
//
//        private List<Object> get_person_properties() {
//            List<Object> properites = new ArrayList<>();
//            properites.addAll(this.height, this.beauty, this.iq, this.eyes_color, this.hair_color, this.skin_color, this.name);
//            return properites;
//        }
//    }
//
//    private class Black_Person {
//        private int height = random(200, 350);
//        private int beauty = random(0, 100);
//        private int iq = random(60, 200);
//
//        private String eyes_color = random('brown');
//        private String hair_color = random('black');
//        private String skin_color = random('black');
//
//        private String name;
//
//        private void setName(String name) {
//            this.name = name;
//        }
//
//        private List<Object> get_person_properties() {
//            List<Object> properites = new ArrayList<>();
//            properites.addAll(this.height, this.beauty, this.iq, this.eyes_color, this.hair_color, this.skin_color, this.name);
//            return properites;
//        }
//    }


}
