package com.example


object Producer {

  import KafkaExample._

  def main(args: Array[String]) {

    split(encode(read("payload.dat")), 27 * 1024 * 1024).zipWithIndex.foreach{
      case (data, index) =>
        write(data.reverse, s"partition${index + 1}.bin")
    }

  }

}
