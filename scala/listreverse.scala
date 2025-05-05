import scala.annotation.tailrec

def listreverse(lista: List[Int]): List[Int] = {
  @tailrec
  def listreverse(lista: List[Int], res:List[Int]): List[Int] = {
    lista match{
      case Nil => res
      case head::tail => listreverse(tail, head::res)
    }
  }
  listreverse(lista, Nil)
}

def positiv(lista: List[Int]): List[Int] = {
  @tailrec
  def positiv(lista: List[Int], res: List[Int]): List[Int] = {
    lista match{
      case Nil => res
      case head::tail =>
        if(head>0){
          positiv(tail, res:::List(head))
        } else{
          positiv(tail, res)
        }
    }
  }
  positiv(lista, Nil)
}

def atlag(lista: List[Double]): Option[Double] = {
  if(lista==Nil){
    None
  } else{
    @tailrec
    def atlag(lista: List[Double], szam: Double, darab: Int): Option[Double] = {
      lista match {
        case Nil => Some(szam / darab)
        case head :: tail => atlag(tail, szam + head, darab + 1)
      }
    }
    atlag(lista, 0, 0)
  }
}

case class Komplex(valos: Double, i:Double){
  def osszead(komplex1: Komplex, komplex2: Komplex): Komplex = {
    val komplexszam=Komplex(komplex1.valos+komplex2.valos, komplex1.i+komplex2.i)
    komplexszam
  }
  def szoroz(komplex1: Komplex, komplex2: Komplex): Komplex = {
    val komplexszam=Komplex(komplex1.valos*komplex2.valos-komplex1.i*komplex2.i, komplex1.valos*komplex2.i+komplex2.valos*komplex1.i)
    komplexszam
  }
}

object Masik extends App{

  def osszeadogat(komplex1: Komplex, komplex2: Komplex): Komplex = {
    val sokadikkomplexszam = Komplex(komplex1.valos + komplex2.valos, komplex1.i + komplex2.i)
    sokadikkomplexszam
  }

  def szorozgat(komplex1: Komplex, komplex2: Komplex): Komplex = {
    val masikkomplex = Komplex(komplex1.valos * komplex2.valos - komplex1.i * komplex2.i, komplex1.valos * komplex2.i + komplex2.valos * komplex1.i)
    masikkomplex
  }

  val lista=List(-3, 2, 3, -2, 4, 5)
  println(listreverse(lista))
  println(positiv(lista))
  val lista2=List(3.0, 4.0, 5.0)
  println(atlag(lista2))
  val k1=Komplex(3.0, 2.0)
  val k2 = Komplex(4.0, 3.0)
  println(osszeadogat(k1,k2))
  println(szorozgat(k1,k2))
}
