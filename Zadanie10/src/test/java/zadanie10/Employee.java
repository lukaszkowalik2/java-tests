package zadanie10;

public class Employee extends Person {

  @ToString(priority = 10, order = Order.DESCENDING)
  public int employeeId = 999;
}
