/**
 * Kotlin solution to the HackerRank challenge Sherlock and Anagrams.
 * Given a string of lowercase letters, return the number of pairs of
 * substrings that are anagrams of each other. For example, if s = "mom",
 * the anagrammatic pairs are [m,m] and [mo,om], result = 2.
 * 2 <= s.length <= 100
 */
fun sherlockAndAnagrams(s:String):Int {
    var result=0
    val BASE_CODE = 'a'.code // HackerRank uses the deprecated .toInt()

    // Build a hash for a substring. If the hash for one substring
    // matches another, the two are anagrams. The code counts the
    // letters, then creates a hash in the form cn, where c is the
    // character and n is the number of occurrences. The hash for
    // "mom" is "m2o1". Parameters L and R are indices of the string
    // s. This prevents sending copies of substrings.
    fun score(L:Int, R:Int):String {
        val hash = StringBuilder("")
        val map = IntArray(26) // Auto initialized to 0

        // Map the quantity of each letter in the substring
        for (i in L..R) {
            map[s[i].code-BASE_CODE]++
        }
        // Create the hash. Skip the 0s.
        for (i in map.indices) {
            val n = map[i]
            if (n>0) {
                hash.append((i+BASE_CODE).toChar())
                hash.append(n.toString())
            }
        }
        return hash.toString()
    }

    // Frequency of each hash code
    val sMap = hashMapOf<String,Int>()

    // Create every substring permutation of the string.
    // Give each one a hash, put the hash into a hashMap,
    // and count every time you have a match.
    for (len in 1 until s.length) {
        val maxPos = s.lastIndex - len + 1
        for (L in 0..maxPos) {
            val R = L + len - 1
            val hash = score(L, R)
            val count = sMap.getOrElse(hash){0}
            sMap.put(hash, count+1)
            result += count
        }
    }
    return result
}

fun SherlockAndAnagramsDriver() {
    val s1 = "ifailuhkqq"   // 3
    val s2 = "kkkk"         // 10
    println(sherlockAndAnagrams(s1))
    println(sherlockAndAnagrams(s2))
}

fun main() {
    SherlockAndAnagramsDriver()
}