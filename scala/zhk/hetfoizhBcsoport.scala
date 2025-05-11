import scala.annotation.tailrec

case class Idotartam(ora: Int, perc: Int, mp: Int) {
  def hozzaad(mpk: Int): Idotartam = {
    val osszesmp = ora * 3600 + perc * 60 + mp + mpk
    val ujOra = (osszesmp / 3600) % 24
    val ujPerc = (osszesmp % 3600) / 60
    val ujMp = osszesmp % 60
    Idotartam(ujOra, ujPerc, ujMp)
  }
}

def minChar(lista: List[Char]): Char = {
  if (lista.isEmpty) {
    Char.MaxValue
  } else {
    @tailrec
    def minChar(lista: List[Char], res: Char): Char = {
      lista match {
        case Nil => res
        case head :: tail =>
          if (res > head) {
            minChar(tail, head)
          } else {
            minChar(tail, res)
          }
      }
    }
    minChar(lista, Char.MaxValue)
  }
}

object JCY9ZR extends App{
  val ido=Idotartam(0, 118, 59)
  println(ido.hozzaad(1))
  val lista=List('c', 'a', 'b')
  println(minChar(lista))
}
