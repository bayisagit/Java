public class Employee2 {
   public String name;
   private double salary;
   public void setName(String empName){
      name = empName;
   }
   public void setSalary(double empSal){
      salary = empSal;
   }
   public void printEmp(){
      System.out.println("name  : " + name );
      System.out.println("salary :" + salary);
   }
   public static void main(String args[]){
    Employee2 empOne = new Employee2();
    Employee2 empTwo = new Employee2();
    empOne.setName("John");
    empTwo.setName("Max");
    empOne.setSalary(1000);
    empTwo.setSalary(2000);
    empOne.printEmp();
    empTwo.printEmp();
 }    
}
