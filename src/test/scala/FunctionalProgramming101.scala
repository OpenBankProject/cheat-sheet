import org.scalatest.{FunSuite, Matchers}

class FunctionalProgramming101 extends FunSuite  with Matchers{
  
  
  //anonymous function computes Int * 2
  val someNumbers = List(3,4,6,7,8,93)
  val modifiedThereforNewListOfSomeNumbers = someNumbers.map(x => x * 2)
  
  modifiedThereforNewListOfSomeNumbers should be (List(6,8,12,14,16,186))
  
  val someNumbersFiltered = someNumbers.filter(x => x%2 == 0)
  someNumbersFiltered should be (List(4,6,8))
  
  
  //countervariables without mutable variables
  
  def multiplyBySum(number: Int, increment: Int, counter: Int): Int = {
    if (counter == 0) 0 else  {
    if (counter == 1) {number} else multiplyBySum((number + increment), increment, (counter -1))}
  }
  
  test("multiplyBySum"){
    multiplyBySum(3,3 ,3) should be (9)
  }
  
  
  //anonymous functions
  
  //x => x * 2 same as _ => *s 
  
  val list = List(2,3,4,5)
  
  val newList = list.map(x => x* 2)
  val anotherNewList = list.filter(x => x%2 ==0)
  
  
  
  
  

}
