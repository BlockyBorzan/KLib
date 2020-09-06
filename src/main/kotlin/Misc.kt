/**
 * Shortens [StackTraceElement.getClassName] to the substring after the last **'.'**.
 *
 * e.g.: "java.lang.String" becomes "String"
 */
val StackTraceElement.simpleClassName: String
  get() = this.className.substringAfterLast(".")

/**
 * Executes [block] if the calling object is null and returns the object (null).
 */
inline fun <T> T?.isNull(block: T?.() -> Unit): T?
{
  if (this == null) block()
  return this@isNull
}

/**
 * Executes [block] if the calling object is not null and returns the object.
 */
inline fun <T> T?.isNotNull(block: T.() -> Unit): T?
{
  this?.block()
  return this@isNotNull
}