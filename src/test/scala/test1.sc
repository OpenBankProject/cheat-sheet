import net.liftweb.common.Full
import net.liftweb.common.Failure

for
  {
  a <- Full(1)
  b <- Failure("Bad!!")
  c <- Full(3)
} yield
  c


val list = List(2,3,4,5)

val newList = list.map(x => x* 2)
val anotherNewList = list.filter(x => x%2 ==0)


//def someFunction(anotherFunction: T => (String, Boolean, List))


def sum(a: Int, b: Int):Int = {
  a + b
}
