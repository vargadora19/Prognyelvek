import scala.annotation.tailrec

case class Komplex2(valos:Double, i:Double){
  def szoroz(komplex: Komplex2): Komplex2 = {
    val komplexszam=Komplex2(this.valos*komplex.valos-this.i*komplex.i, this.valos*komplex.i+komplex.valos*this.i)
    komplexszam
  }
}

def insertion(lista: List[Int], elem:Int): List[Int] = {
  @tailrec
  def insertion(lista: List[Int], elem:Int, res:List[Int],szamoloszolga:Int): List[Int] = {
    lista match{
      case Nil=>res
      case head::tail=>
        if(szamoloszolga%3==0){
          insertion(tail, elem, res:::List(elem), szamoloszolga+1)
        } else{
          insertion(tail, elem, res:::List(head), szamoloszolga+1)
        }
    }
  }
  insertion(lista, elem, Nil, 1)
}




object JCY9ZR{
  def main(args: Array[String]): Unit = {
    val komplex=Komplex2(2.0, 3.0)
    println(komplex.szoroz(komplex))
    println(insertion(List(5, 4, 3, 2, 1, 4, 13, 3), 60000))
  }
}