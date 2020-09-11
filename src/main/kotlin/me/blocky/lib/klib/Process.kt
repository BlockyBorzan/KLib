package me.blocky.lib.klib

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Starts the calling process and executes the [alsoBlock] afterwards
 */
fun ProcessBuilder.go(alsoBlock: (Process) -> Unit): Process = this.start().also { alsoBlock(it) }

/**
 * Pipes the input stream of the calling process to the output stream of the current runtime environment.
 */
fun Process.pipeInputStream()
{
  BufferedReader(InputStreamReader(this.inputStream), 1024).also {
    do
    {
      if (it.ready()) it.readLine()?.also { line -> println(line) }
    }
    while (isAlive)
  }
}

/**
 * Pipes the input stream of the calling process to the output stream of the current runtime environment.
 * If any Regex key in the [triggerResponse] map matches a line in the stream, the corresponding value in the map is
 * written to the output stream of the calling process and the output stream gets flushed.
 */
fun Process.reactToStream(triggerResponse: Map<Regex, String>)
{
  BufferedReader(InputStreamReader(this.inputStream)).also {
    do
    {
      it.readLine()?.also { line ->
        println(line)
        for ((regex, response) in triggerResponse)
        {
          if (!regex.containsMatchIn(line)) continue
          this.outputStream.write(response.toByteArray())
          this.outputStream.flush()
        }
      }
    }
    while (this.isAlive)
  }
}