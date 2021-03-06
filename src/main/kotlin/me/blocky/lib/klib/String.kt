package me.blocky.lib.klib

import org.apache.commons.text.WordUtils

/**
 * Replaces all occurrences of each [Pair]'s [Pair.first] from [pairs] with the respective [Pair.second] in the calling string and returns it.
 */
fun String.replace(vararg pairs: Pair<String, String>): String
{
  return pairs.fold(this, { result, (oldValue, newValue) -> result.replace(oldValue, newValue) })
}

/**
 * Removes any specified [suffixes] ***in order*** from the calling string and returns it.
 *
 * The order of the specified suffixes matter as removing the first suffix changes the string and thus the next suffix that would match to be removed.
 */
fun String.removeSuffixes(vararg suffixes: String): String
{
  return suffixes.fold(this, { result, value -> result.removeSuffix(value) })
}

/**
 * Removes any specified [prefixes] ***in order*** from the calling string and returns it.
 *
 * The order of the specified prefixes matter as removing the first prefix changes the string and thus the next prefix that would match to be removed.
 */
fun String.removePrefixes(vararg prefixes: String): String
{
  return prefixes.fold(this, { result, value -> result.removePrefix(value) })
}

/**
 * Returns true if the calling string matches any of the following: "true", "false", "t", "f"; returns false otherwise.
 *
 * [ignoreCase] makes the above matches ignore the case when comparing to the calling string.
 */
fun String.representsBoolean(ignoreCase: Boolean = true) = equals("true", ignoreCase) ||
                                                           equals("false", ignoreCase) ||
                                                           equals("t", ignoreCase) ||
                                                           equals("f", ignoreCase)

/**
 * Returns true if the calling String starts with [prefix] **and** ends with [suffix]; returns false otherwise.
 *
 * [ignoreCase] makes the above comparisons ignore the case when comparing to the calling string.
 */
fun String.isSurroundedBy(prefix: String, suffix: String = prefix, ignoreCase: Boolean = false): Boolean
{
  return this.startsWith(prefix, ignoreCase) && this.endsWith(suffix, ignoreCase)
}

/**
 * Returns a String that is capitalized using [WordUtils.capitalizeFully] with [this] as the str parameter and
 * [wordDelimiters] as the delimiters argument.
 *
 * The [delimiterMapping] parameter can be used to replace delimiters with new delimiter. If it is empty, no
 * replacements take place. The delimiterMapping can contain keys that do not necessarily have to be present in the
 * wordDelimiters parameter.
 */
fun String.toCamelCase(
    vararg wordDelimiters: Char = charArrayOf(' ', '_'),
    delimiterMapping: Map<Char, String> = emptyMap()
): String = WordUtils.capitalizeFully(this, *wordDelimiters).modifyIf(delimiterMapping.isNotEmpty()) {
  it.replace(*delimiterMapping.entries.map { (k, v) -> k.toString() to v }.toTypedArray())
}

/**
 * Calls the [toCamelCase] method with the [wordDelimiters] parameter and a map that maps each Char in wordDelimiters
 * to the [newDelimiter].
 */
fun String.toCamelCase(
    vararg wordDelimiters: Char = charArrayOf(' ', '_'),
    newDelimiter: String = ""
): String = toCamelCase(wordDelimiters = wordDelimiters, wordDelimiters.map { it to newDelimiter }.toMap())
