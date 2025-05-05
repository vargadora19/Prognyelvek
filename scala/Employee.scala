import scala.annotation.tailrec

trait Workable{
  def work(): Unit = {
    println("Working...")
  }
}

class Employee(name: String, salary:Int) extends Workable{
  def displayInfo(): Unit = {
    println(this.name+" "+this.salary)
    work()
  }
}

class Manager(name: String, salary: Int, val bonus: Int) extends Employee(name, salary) with Workable {
  override def displayInfo(): Unit = {
    println(this.name + " " + this.salary+" "+bonus)
    work()
  }
}

//object Company{
//  val lista=List()
//
//  def visszaad(index: Int): Employee = {
//    @tailrec
//    def visszaad(lista:List[Employee], index: Int): Employee = {
//      if (index==0){
//        lista.head
//      } else{
//        visszaad(lista.tail, index-1)
//      }
//    }
//    visszaad(this.lista, index)
//  }
//
//  def eltavolit(index:Int): Unit = {
//    @tailrec
//    def eltavolit(eredmeny:List[Employee], index:Int): Unit = {
//      if(index==0){
//        lista.tail
//      } else{
//        eltavolit()
//      }
//    }
//  }
//
//  def addEmployee(employee: Employee, index:Int): Unit = {
//    lista:::List(employee)
//  }
//  def getEmployeeInfo(index:Int): Employee = {
//    visszaad(index)
//  }
//  def fireEmployee(index:Int): Unit = {
//
//  }
//
//
//}


object Kiir extends App{
  val employee = new Employee("mano", 3)
  employee.displayInfo()
  val manager=new Manager("sanyi", 5, 8)
  manager.displayInfo()
}
