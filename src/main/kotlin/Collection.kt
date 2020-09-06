import java.util.*

/**
 * Returns the top [amount] elements of this [Stack] without popping.
 */
inline fun <reified T> Stack<T>.peek(amount: Int): Array<T> = when
{
  amount > this.size -> throw IllegalArgumentException("Amount exceeds size of stack.")
  else               -> this.takeLast(amount).toTypedArray()
}

/**
 * Pops the top [amount] elements of this [Stack] and returns them as an [Array].
 */
inline fun <reified T> Stack<T>.pop(amount: Int): Array<T> = when
{
  amount < 0         -> throw IllegalArgumentException("Requested element count $amount is less than zero.")
  amount > this.size -> throw IllegalArgumentException("Amount exceeds size of stack.")
  else               -> Array(amount) { pop() }
}

/**
 * Creates a new [Stack] containing all specified [elements]
 */
fun <T> stackOf(elements: Collection<T>): Stack<T>
{
  val stack = Stack<T>()
  stack.addAll(elements)
  return stack
}

/**
 * Calls [joinTo] with a new [StringBuilder] as the buffer and returns the resulting joined string.
 */
fun <T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    lastSeparator: CharSequence = separator,
    prefix: CharSequence = "",
    suffix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): String
{
  return joinTo(StringBuilder(), separator, lastSeparator, prefix, suffix, limit, truncated, transform).toString()
}

/**
 * Joins and [Iterable] to a string with the option to specify the following parameters
 * - [buffer] (implements [Appendable], collects all elements of the iterable)
 * - [separator] (defaults to ', ')
 * - [lastSeparator] (defaults to the separator)
 * - [prefix] (defaults to an empty string)
 * - [suffix] (defaults to an empty string)
 * - [limit] (defaults to -1, meaning all elements of the iterable are joined)
 * - [truncated] (indicates more elements are present after the limit was reached, defaults to "...")
 * - [transform] (transforms the elements the iterable type to a char sequence, defaults to null (no transformation takes place), see [appendElement])
 */
fun <T, A : Appendable> Iterable<T>.joinTo(
    buffer: A,
    separator: CharSequence = ", ",
    lastSeparator: CharSequence = separator,
    prefix: CharSequence = "",
    suffix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): A
{
  buffer.append(prefix)
  var count = 0
  val lastCount = this.count()
  for (element in this)
  {
    if (++count > 1) buffer.append(if (count < lastCount) separator else lastSeparator)
    if (limit < 0 || count <= limit)
    {
      buffer.appendElement(element, transform)
    }
    else break
  }
  if (limit in 0 until count) buffer.append(truncated)
  buffer.append(suffix)
  return buffer
}

/**
 * Transforms [element] using the [transform] block before appending it to the calling [Appendable].
 */
fun <T> Appendable.appendElement(element: T, transform: ((T) -> CharSequence)?)
{
  when
  {
    transform != null        -> append(transform(element))
    element is CharSequence? -> append(element)
    element is Char          -> append(element)
    else                     -> append(element.toString())
  }
}

/**
 * Appends the [appendage] to the calling [Appendable] if the [condition] is met (true).
 */
fun <T> Appendable.appendWhen(appendage: T, condition: Boolean)
{
  if (condition) this.appendElement(appendage, null)
}