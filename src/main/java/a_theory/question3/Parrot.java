package a_theory.question3;

abstract class Animal{

   public abstract void fly();
}

public class Parrot extends Animal{

   public void fly() {
       System.out.println("Flying");
   }

   public static void main(String[] args){
	Animal obj = new Parrot();
	obj.fly();
   }
}