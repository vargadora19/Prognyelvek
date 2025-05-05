import scala.annotation.tailrec

def fibonacci(N: Int): Int = {
  @tailrec
  def fibonacci(utolso: Int, utolsoelotti: Int, N: Int): Int = {
    N match{
      case 0 => utolsoelotti
      case _ => fibonacci(utolso+utolsoelotti, utolso, N-1)
    }
  }
  fibonacci(1, 0, N)
}

def negyzetosszeg(N: Int): Int = {
  @tailrec
  def negyzetosszeg(N: Int, res: Int): Int = {
    N match{
      case 0=>res
      case n => negyzetosszeg(N-1, res + N*N)
    }
  }
  negyzetosszeg(N, 0)
}

def szorzas(szam1:Int, szam2:Int): Int = {
  @tailrec
  def szorzas(szam1:Int, szam2:Int, res:Int): Int = {
    szam1 match {
      case 0 => res
      case n => szorzas(szam1-1, szam2, res+szam2)
    }
  }
  szorzas(szam1, szam2, 0)
}

def szamjegyosszeg(n:Int): Int = {
  @tailrec
  def szamjegyosszeg(n:Int, res:Int): Int = {
    n match{
      case 0=>res
      case _ => szamjegyosszeg(n/10, res+(n%10))
    }
  }
  szamjegyosszeg(n, 0)
}

def haromszog(a: Double, b:Double, c: Double): Boolean = {
  if(a+b<=c || a+c<=b || b+c<=a){
    false
  }else{
    true
  }
}

def heron(a: Double, b:Double, c: Double): Double = {
  val s=(a+b+c)/2
  math.sqrt(s*(s-a)*(s-b)*(s-c))
}



object Szar extends App{
  println(fibonacci(5))
  println(negyzetosszeg(3))
  println(szorzas(-3, 5))
  println(szamjegyosszeg(6))
}
