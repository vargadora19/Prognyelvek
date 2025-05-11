import scala.annotation.tailrec

case class Colour(red:Int, green:Int, blue:Int){
  def vilagosit(ertek: Double): Colour = {
    val newred=math.min(255, math.round(red*ertek).toInt)
    val newgreen=math.min(255, math.round(green*ertek).toInt)
    val newblue=math.min(255, math.round(blue*ertek).toInt)
    Colour(newred, newgreen, newblue)
  }
}

def maxDouble(lista:List[Double]): Double = {
  if(lista.isEmpty){
    Double.NegativeInfinity
  }else{
    @tailrec
    def maxDouble(lista:List[Double], res: Double): Double = {
      lista match{
        case Nil => res
        case head::tail=>
          if(res<head){
            maxDouble(tail, head)
          }else{
            maxDouble(tail, res)
          }
      }
    }
    maxDouble(lista, Double.NegativeInfinity)
  }
}

object Huh{
  def main(args: Array[String]): Unit = {
    val szin=Colour(0, 250, 100)
    println(szin.vilagosit(1.2))

    val lista=List(1.2, 4.1, 3.2)
    println(maxDouble(lista))
  }
}
