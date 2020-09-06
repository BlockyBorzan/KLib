package extensions

import java.io.File


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