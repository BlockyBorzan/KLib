import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * Calls the specified function [block] with `this` value as its argument if the [condition] is met,
 * and returns its result. If the condition is not met, simply returns `this` value
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> R.modifyIf(condition: Boolean, block: (R) -> R): R {
  contract {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  return if(condition) block(this) else this
}

/**
 * Calls the specified function [block] with `this` value as its receiver if the [condition] is met,
 * and returns its result. If the condition is not met, simply returns `this` value
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> R.transformIf(condition: Boolean, block: R.() -> R): R {
  contract {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  return if(condition) block() else this
}

/**
 * Throws the specified [throwable] if the [condition] is met. Returns `this` value otherwise.
 */
fun <R> R.exceptIf(condition: Boolean, throwable: Throwable): R {
  if(condition) throw throwable
  return this
}
