package com.example

import java.io.{File, FileInputStream, FileOutputStream}
import java.util.Base64

object KafkaExample {

  def main(args: Array[String]) {

    split(read("test.txt"), 3).map(_.reverse).foreach{
      line =>
        println("Got " + new String(line) + " of " + line.length)
    }

  }

  def list(name : String, pattern : String) : Seq[File] =
    new File(name).listFiles().filter(f => f.getName.matches(pattern))


  def encode(data : Array[Byte]) : Array[Byte] =
    Base64.getEncoder.encode(data)

  def decode(data : Array[Byte]) : Array[Byte] =
    Base64.getDecoder.decode(data)

  def read(name : String) : Array[Byte] = {
    val f2= new File(name)
    val buf2 = new Array[Byte](f2.length().toInt)
    val i2 = new FileInputStream(f2)
    i2.read(buf2)
    buf2
  }

  def write(data : Array[Byte], name : String) : Unit = {
    val out = new FileOutputStream(name)
    out.write(data)
    out.close()
  }

  def merge(left : Array[Byte], right : Array[Byte])  : Array[Byte] = {
    val buf3 = new Array[Byte](left.length + right.length)
    System.arraycopy(left, 0, buf3, 0, left.length)
    System.arraycopy(right, 0, buf3, left.length, right.length)
    buf3
  }

  def split(data : Array[Byte], size : Int) : Seq[Array[Byte]] = {
    (0 to ((data.length / size))).foldLeft( Seq[Array[Byte]]() ) {
      case (state, i) =>
        val from = (i * size)
        val s = Math.min( size, data.length - from    )
        val buf = new Array[Byte]( s )
        System.arraycopy(data, from, buf, 0,  buf.length )
        state :+ buf
    }
  }


}
