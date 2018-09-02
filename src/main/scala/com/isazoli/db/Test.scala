package com.isazoli.db

import slick.lifted.Tag
import slick.jdbc.H2Profile.api._

class Test(tag: Tag) extends Table[(Int, String)](tag, "TEST") {

  def id = column[Int]("ID", O.PrimaryKey) // This is the primary key column
  def name = column[String]("NAME")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name)
}

object Test {

  def apply(tag: Tag): Test = new Test(tag)

}



