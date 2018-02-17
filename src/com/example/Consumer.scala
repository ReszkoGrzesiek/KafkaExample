package com.example

object Consumer {


  import KafkaExample._

  def main(args: Array[String]) {


    val result = list(".", "partition[\\d+].dat").map(f => read(f.getName)).foldLeft(new Array[Byte](0)){
      (left, right) =>
        merge(left, right.reverse)
    }

    write(decode(result), "payload.dat")

  }

}
