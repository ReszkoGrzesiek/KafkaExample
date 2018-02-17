package com.example

import java.io._
import java.util.zip.ZipInputStream

/**
  * Created by aleksander on 17.02.2018.
  */
object Zipper {

  def main(args: Array[String]) {

    uncompress("pack", "payload.dat")


  }

  def uncompress(outputName: String, file: String): File =
    uncompress(outputName, new ZipInputStream(new FileInputStream(file)))

  def uncompress(outputName: String, zis: ZipInputStream): File = {
    val output = new File(outputName)
    Stream
      .continually(zis.getNextEntry)
      .takeWhile(_ != null)
      .foreach { entry =>
        val fileName = entry.getName
        val newFile = new File(output, fileName)
        new File(newFile.getParent).mkdir()
        if (! fileName.endsWith("/")) {
          val fos = new FileOutputStream(newFile)
          copy(zis, fos)
          fos.close()
        }
      }
    zis.closeEntry()
    zis.close()
    output
  }

  def copy(input : InputStream, output : OutputStream) : Unit = {
    val buffer = new Array[Byte](1024 * 1024)
    var keep = true
    while (keep) {
      val bytesRead = input.read(buffer)
      if (bytesRead != -1)
        output.write(buffer, 0, bytesRead)
      else
        keep = false
    }
  }


}
