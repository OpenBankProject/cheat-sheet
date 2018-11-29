def function2(f: (Double, Double) => Double) = {
  f(1,2) 
}


def func1(x: Double, y: Double) = {
  x+y
}


function2(func1)