import scala.annotation.tailrec

def osszehasonlitas(ido1:(Int, Int, Int), ido2:(Int, Int, Int)): Boolean = {
  val mp1=ido1._1*3600+ido1._2*60+ido1._3
  val mp2=ido2._1*3600+ido2._2*60+ido2._3
  if(mp1>mp2){
    true
  }else{
    false
  }
}

def kozos(lista1:List[Char],lista2:List[Char]): List[Char] = {
  @tailrec
  def kozos(lista1:List[Char],lista2:List[Char], res:List[Char]): List[Char] = {
    (lista1,lista2) match{
      case (Nil,Nil) => res
      case (Nil, head::tail) => res
      case (head::tail,Nil) => res
      case (h::t,head::tail)=>
        if(h==head){
          kozos(t, tail, res:::List(head))
        }else{
          kozos(t, tail, res)
        }
    }
  }
  kozos(lista1,lista2, Nil)
}

object Valami{
  def main(args: Array[String]): Unit = {
    val ido1=(0, 1234, 0)
    val ido2=(21, 0, 0)
    println(osszehasonlitas(ido1, ido2))

    val lista1=List('a', 'b', 'c')
    val lista2=List('c', 'b')
    println(kozos(lista1, lista2))
  }
}