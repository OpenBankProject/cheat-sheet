import org.scalatest.{FunSuite, Matchers}
import net.liftweb.common.{Box, Empty, Full, Failure}

import scala.util.Try
class OptionAndBox extends FunSuite  with Matchers {


  // Scala uses the Option class to represent values that might be missing. Here is some Option of type String
  case class Passenger(name: String, ticket: Option[String])
  
  test("declare some Option and read its values"){

    // Scala uses the Option class to represent values that might be missing. Here is some Option of type String
    case class Passenger(name: String, ticket: Option[String])
    
    //Option can be either Some( actual_value), or the None object, representing a missing value
    
    val hans: Passenger  =  Passenger(name = "Hans", ticket = Some("Verbundsfahrschein"))
    val a19: String         =hans.ticket.getOrElse("")
    val a20: Option[String] =hans.ticket.orElse(Some(""))
    val a: Option[String]   =hans.ticket.map(_.capitalize)
    
    val fred: Passenger = Passenger(name = "Fred", ticket = None)
    
    val hansTicket: Option[String] = hans.ticket 
    
    hansTicket should be (Some("Verbundsfahrschein"))
    
    //So we have to extract the real value here
    val realHansTicket = hans.ticket.get should be ("Verbundsfahrschein")  
   //This will break if the real value is missing. Here a default is set that will replace the missing value.
   val realFredTicket = fred.ticket.getOrElse("Schwarzfahrer")
    realFredTicket should be ("Schwarzfahrer")
  }
  
/*  test("Option: Please fix this test"){
    val karl: Passenger = Passenger(name = "Karl", ticket = ())
    val erich: Passenger = Passenger(name = "Erich", ticket = ())
    
    karl.ticket.getOrElse("Schwarzfahrer") should be ("Schuelerfahrkarte")
    //This will break
    erich.ticket.get should be (None)
    erich.ticket.isEmpty should be (true)

  }*/
  
  //Scala make use of Option for returning Collection values 
  
  val passengerList = List(Passenger("Horst", Some("Verbundfahrschein")), Passenger("Ulrich", None), Passenger("Hektor", Some("Bayernticket")))
  
  val schwarzfahrer: List[Passenger] = passengerList.filter(x => x.ticket == None)
  
  
  //liftweb Box aka "scala Option on steroids", can be Full(value), Empty or Failure
  
  def getCashInPurse(cash: Int): Box[Int] = {
    if (cash > 0) {Full(cash)}
    else if (cash == 0 ) {Empty}
    else  Failure("cash cannot be negative")
  }
  
  //lets Box some cash values
  val moneyHans: Box[Int] = getCashInPurse(10)
  val moneyKarl: Box[Int] = getCashInPurse(8)
  val moneyHorst: Box[Int] = getCashInPurse(0)
  val moneyUlrich: Box[Int] = getCashInPurse(-32)
  
  //and unbox again:
  
  def unboxCashInPurse(cash: Box[Int]): Int = {
    cash match {
      case Full(x) => x
      case Empty => 0
      case _ => 0
    }
  }
  

    
  // a Box for comprehension is a powerfull tool to deal with boxes
  val warChest: Box[Int] = for {
    x <- moneyHans
    y <- moneyKarl
  } yield {
    x + y
  }
  
  warChest should be (Full(18))
  
  val noChest: Box[Int] = for {
    x <- moneyHans
    y <- moneyUlrich
  } yield {
    x + y
  }
  
  noChest should be (Failure("cash cannot be negative"))
}
