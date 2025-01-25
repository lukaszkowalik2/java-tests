package zadanie10;

public class Person {

  public int height = 180;

  @ToString(priority = 5)
  public int age = 30;

  @ToString(priority = 7)
  public int salary = 5000;

  public static int population = 1000;
  protected int cos = 999;
}
