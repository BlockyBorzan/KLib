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
 * Calls the specified function [conditionBlock] with `this` value as its receiver. If the result is true,
 * throws the result of the function [throwableBlock] called with `this` value as its receiver.
 * Otherwise returns `this` value
 */
fun <R> R.exceptIf(conditionBlock: R.() -> Boolean, throwableBlock: R.() -> Throwable): R {
  if(conditionBlock()) throw throwableBlock()
  return this
}

/**
* Calls the specified function [conditionBlock] with `this` value as its argument. If the result is true,
* throws the result of the function [throwableBlock] called with `this` value as its argument.
 * Otherwise returns `this` value
*/
fun <R> R.throwIf(conditionBlock: (R) -> Boolean, throwableBlock: (R) -> Throwable): R {
  if(conditionBlock(this)) throw throwableBlock(this)
  return this
}

