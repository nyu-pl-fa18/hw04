package pl.hw04

object hw04 extends App {

  // Problem 4: Custom Control Constructs

  def until(b: => Boolean)(body: => Unit): Unit = {
    ???
  }
  
  // the following should print all values from 1 to 10
  var x = 0
  
  until (x == 10) {
    x = x + 1
    println(x)
  }
  
  // the following should not print anything
  until (x == 10) {
    x = x - 1
    println(x)
  }
}
