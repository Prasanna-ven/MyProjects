object test{
def calc(i: Int, j: Int) = {
println("Addition : "+(i+j))
println("Subtraction : "+(j-i))
println("Multiplication : "+(i*j))
println("Division : "+(j/i))
}

def main(args : Array[String]): Unit = {
var a =10
val b = 15
var s = Array("Hello World!","This is my first scala code", "I am learning quick!")

println(s(0))
println("a : "+a)
println("b : "+b)
calc(a,b)
println(s(1))
println(s(2))
}
}
