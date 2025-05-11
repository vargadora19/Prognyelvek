

def lazy_lista(szamol:Int): LazyList[Int] = {
  if(szamol%3 == 2 && szamol%5==4){
    ((szamol*2)+3) #:: lazy_lista(szamol+1)
  }else if(szamol%3 == 2){
    (szamol*2) #:: lazy_lista(szamol+1)
  }else if(szamol%5==4){
    (szamol+3) #:: lazy_lista(szamol+1)
  }else{
    szamol #:: lazy_lista(szamol+1)
  }
}

def szur(lista: LazyList[Int], feltetel: Int => Boolean, mod1: Int => Unit, mod2: Int => Unit): Unit = {
  val elso = lista.find(feltetel)
  elso match{
    case None => ()
    case Some(valami) =>
      if(valami%2 == 0){
        mod1(valami)
      }else{
        mod2(valami)
      }
  }

}



object Teszter extends App{
  //println(lazy_lista(0).take(20).toList)

  val lazyNums: LazyList[Int] = LazyList.from(6)
  val feltetel = (x:Int) => if(x%5==0){
    true
  }else{
    false
  }
  val strategy1 = (x:Int) => println(x.toString + " strategy1")
  val strategy2 = (x:Int) => println(x.toString + " strategy2")
  szur(lazyNums,feltetel,strategy1,strategy2)

}

