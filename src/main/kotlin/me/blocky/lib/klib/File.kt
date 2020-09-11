package me.blocky.lib.klib

import java.io.File
import java.io.IOException

/**
 * Creates a new file or directory if the extended [File] does not exist.
 *
 * Whether a file or folder are created is denoted by [isFile].
 * If it is true, a file is created, if it is false, a directory is created.
 *
 * The extended File that was created is returned.
 *
 * Can throw any errors and exceptions caused by [File.createNewFile] or [File.mkdirs]
 */
fun File.createIfNotExists(isFile: Boolean): File = if (isFile)
{
  this.createNewFile(); this
}
else
{
  this.mkdirs(); this
}

/**
 * Returns true if the file has a valid canonical and false if not.
 *
 * This serves to weed out invalid file names.
 */
fun File.hasValidName(): Boolean = try {
  canonicalPath
  true
} catch (e: IOException) {
  false
}

/**
 * Returns true if the string represents a valid file name and false if not.
 */
fun String.isValidFilename(): Boolean = File(this).hasValidName()

/** Returns the absolute parent folder of the file*/
val File.parentFolder: File
  get() = absoluteFile.parentFile