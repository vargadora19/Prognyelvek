import scala.annotation.tailrec

def beszuras[T](lista: List[T], elem:T): List[T] = {
  @tailrec
  def beszuras[T](lista: List[T], elem:T, eredmeny:List[T], szamol:Int): List[T] = {
    lista match{
      case Nil=>eredmeny
      case head::tail=>
        if(szamol%2==0){
          beszuras(tail, elem, eredmeny:::List(head):::List(elem), szamol+1)
        } else{
          beszuras(tail, elem, eredmeny:::List(head), szamol+1)
        }
    }
  }
  beszuras(lista,elem, Nil, 1)
}

def prime(prim:Int): Boolean = {
  @tailrec
  def prime(prim:Int, csokken:Int): Boolean = {
    if(prim==0 || prim==1){
      false
    } else if(prim==2){
      true
    } else{
      if(prim%csokken==0){
        false
      } else if(csokken*csokken>prim){
        true
      } else{
        prime(prim, csokken+1)
      }
    }
  }
  prime(prim, 2)
//    csokken match{
//      case 0|1=>true
//      case _ =>
//        if(prim%csokken==0){
//          false
//        }else{
//          prime(prim, csokken-1)
//        }
//    }
//  }
//  if(prim<=1){
//    false
//  }else{
//    prime(prim, prim-1)
//  }
}

@tailrec
def forall2(funct: Int=>Boolean, lista:List[Int]): Boolean = {
  lista match{
    case Nil=>true
    case head::tail=>
      if(funct(head)){
        forall2(funct, tail)
      }else{
        false
      }
  }
}

def hany(szoveg:String): Map[Char, Int] = {
  @tailrec
  def hany(szoveg:String, map: Map[Char, Int]): Map[Char, Int] = {
    if (szoveg.isEmpty) {
      map
    } else {
      val eleje = szoveg.charAt(0)
      val tobbi = szoveg.substring(1)

      val uj = map.updated(eleje, map.getOrElse(eleje, 0) + 1)
      hany(tobbi, uj)
    }

  }
  hany(szoveg, Map())
}

object Test extends App{
  val lista=List(1, "kerek",4, 5.32, "hawaii")
  println(beszuras(lista, 51414))
  println(prime(4))
  val lista2=List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  println(lista2.filter(prime))

  val lista3=List(2, 4, 6, 10, 0)
  val paros=(x:Int)=>
    if(x%2==0){
      true
    }else{
      false
    }
  println(forall2(paros, lista3))

  val szoveg="hol van a hutoben tejem?"
  println(hany(szoveg))
}
