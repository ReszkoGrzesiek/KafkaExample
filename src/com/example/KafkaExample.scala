package com.example

import java.io.{File, FileInputStream, FileOutputStream}
import java.util.Base64

object KafkaExample {

  def main(args: Array[String]): Unit = {
    val f= new File("one.txt")
    val buf = new Array[Byte](f.length().toInt)
    val i = new FileInputStream(f)
    i.read(buf)

    val f2= new File("two.txt")
    val buf2 = new Array[Byte](f2.length().toInt)
    val i2 = new FileInputStream(f2)
    i2.read(buf2)

    val buf3 = new Array[Byte](buf.length + buf2.length)

    System.arraycopy(buf, 0, buf3, 0, buf.length)
    System.arraycopy(buf2, 0, buf3, buf.length, buf2.length)

    val data = Base64.getDecoder.decode(buf3)
    val fout = new FileOutputStream("result.zip")

    fout.write(data)

    fout.close()
  }


  def main111(args: Array[String]): Unit = {
    val f= new File("kafka_message.bson")
    val buf = new Array[Byte](f.length().toInt)
    val i = new FileInputStream(f)
    i.read(buf)

    val len = 1024 * 1024 * 50

    val fout = new FileOutputStream("message1.txt")
    fout.write(buf, 0, len)
    fout.close()

    val fout2 = new FileOutputStream("message2.txt")
    fout2.write(buf, len, buf.length - len)
    fout2.close()
  }

  def main3232(args: Array[String]): Unit = {
    val f= new File("message")
    val buf = new Array[Byte](f.length().toInt)
val i = new FileInputStream(f)
    i.read(buf)
    val data = Base64.getDecoder.decode(buf)
    val fout = new FileOutputStream("result.txt")

    fout.write(data)

    fout.close()
  }

  def main222(args: Array[String]) {



val f= new File("message")
    val in = new FileInputStream(f)

//    println("Here2")
//
//      val bytes = new ByteArrayOutputStream()

//    val buffer = new Array[Byte](1024 * 1024)
//
//    Stream.continually(in.read(buffer)).filter(_ > 0).foreach{ len =>
//      println("Reading " + len)
//      bytes.write(buffer, 0, len)
//    }

    val buf = new Array[Byte](f.length().toInt)

    in.read(buf)

    println("Bye!")


    val bytes = buf

//
//    bytes.flush()


    println("Here1")

    val out =  Base64.getEncoder.encode(bytes)

    println("Here2")

   val fout = new FileOutputStream("kafka_message.bson")

    fout.write(out)

    fout.close()
  }

}
