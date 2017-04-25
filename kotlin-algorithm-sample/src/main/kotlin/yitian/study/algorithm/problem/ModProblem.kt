package yitian.study.algorithm.problem

/**
 * 一筐鸡蛋：
 * 1个1个拿，正好拿完。
 * 2个2个拿，还剩1个。
 * 3个3个拿，正好拿完。
 * 4个4个拿，还剩1个。
 * 5个5个拿，还差1个。
 * 6个6个拿，还剩3个。
 * 7个7个拿，正好拿完。
 * 8个8个拿，还剩1个。
 * 9个9个拿，正好拿完。
 * 问:筐里最少有几个鸡蛋？
 *
 */
class ModProblem

/**
 * 直接暴力穷举
 */
fun answer1() {
    var n = 0
    while (true) {
        if (n % 2 == 1 && n % 4 == 1 && n % 5 == 4 && n % 6 == 3 && n % 7 == 0 && n % 8 == 1 && n % 9 == 0) {
            break
        }
        n++
    }
    println(n)
}

/**
 * 改良版本
 */
fun answer2() {
    var n = 63
    var count = 0
    while (true) {
        count++
        if (n % 4 == 1 && n % 5 == 4 && n % 6 == 3 && n % 8 == 1) {
            break
        }
        n += 63 * 2
    }
    println("n=$n,count=$count")

}

/**
 * 更优化版本
 */
fun answer3() {
    var n = 63 * 3
    var count = 0
    while (true) {
        count++
        if (n % 8 == 1) {
            break
        }
        n += 630
    }
    println("n=$n,count=$count")
}

/**
 * 计算一个可以让所有余数都相等的数
 */
fun cal(): Int {
    //由题意推出来的除数和余数的结果
    val numbers = hashMapOf(
            2 to 1,
            3 to 0,
            4 to 1,
            5 to 4,
            6 to 3,
            7 to 0,
            8 to 1,
            9 to 0)
    var n = 0
    while (true) {
        n++
        for (k in numbers.keys) {
            val old = numbers[k]
            numbers[k] = (old!! + 1) % k
        }
        val set = numbers.values.toSet()
        if (set.size == 1) {
            break
        }
    }
    println("这个数是:$n")
    return n
}

/**
 * greatest common divisor
 * a和b的最大公约数
 */
tailrec fun gcd(a: Int, b: Int): Int {
    val c = if (a > b) a % b else b % a
    if (c == 0)
        return b
    else
        return gcd(b, c)
}


/**
 * lowest common multiple
 * a和b的最小公倍数
 */
fun lcm(a: Int, b: Int): Int {
    val c = gcd(a, b)
    return a * b / c
}

/**
 * 最后一种，通过把所有余数凑成相同的
 * 然后使用最小公倍数来计算
 */
fun answer4() {
    val n = cal()
    val lcmOfAll = (2..10).reduce(::lcm)
    println("结果是${lcmOfAll - n}")
}

fun main(args: Array<String>) {
    answer1()
    answer2()
    answer3()
    answer4()
}