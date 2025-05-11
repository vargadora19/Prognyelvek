import scala.annotation.tailrec

def kever(rgb1: (Int, Int, Int), rgb2: (Int, Int, Int)): Tuple = {
  val (red, green, blue)=rgb1
  val (red2, green2, blue2) =rgb2

  val reds=(red+red2)/2
  val greens = (green + green2) / 2
  val blues = (blue + blue2) / 2

  (reds, greens, blues)
}

def mergeList(lista1: List[Double], list: List[Double]): List[Double] = {
  @tailrec
  def mergeList(lista1: List[Double], list: List[Double], res:List[Double]): List[Double] = {
    (lista1,list) match {
      case (Nil, Nil) => res
      case (head :: tail, Nil) => mergeList(tail, Nil, res ::: List(lista1.head))
      case (Nil, head :: tail) => mergeList(Nil, tail, res ::: List(list.head))
      case (h :: t, head :: tail) => mergeList(t, tail, res ::: List(lista1.head):::List(list.head))
    }

  }
  mergeList(lista1, list, Nil)
}

object Jcy9zr{
  def main(args: Array[String]): Unit = {
    val egyes=(20, 30, 40)
    val kettes= (10, 20, 30)
    println(kever(egyes, kettes))

    val lista1=List(1.0, 3.0)
    val lista2=List(2.0, 4.0, 5.0)
    println(mergeList(lista1,lista2))
  }
}
