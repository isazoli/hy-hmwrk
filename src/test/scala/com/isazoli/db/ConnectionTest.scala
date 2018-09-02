package com.isazoli.db

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}
import slick.jdbc.H2Profile.api._
import slick.lifted.QueryBase

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration


class ConnectionTest extends FlatSpec with Matchers {

  val db = Database.forConfig(
    path = "h2test",
    config = ConfigFactory.load("application.conf")
  )

  val tests = TableQuery[Test]


  "Database" should "return 2 rows" in {
    try {
      val tests = TableQuery[Test]

      val value: QueryBase[Seq[((Int, String), (Int, String))]] = tests join tests on (_.name === _.name)

      Await.result(
        db.run(tests.result).map(_.map(_._2)),
        Duration.Inf
      ).toSet shouldBe Set("Hello", "World")
    } finally db.close
  }

}
