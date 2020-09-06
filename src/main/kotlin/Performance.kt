import kotlin.time.measureTimedValue

/**
 * Times the execution of [block] and returns the [kotlin.time.TimedValue] of the result.
 */
@OptIn(kotlin.time.ExperimentalTime::class)
inline fun <T> timeExecution(block: () -> T) = measureTimedValue(block)
