import zio._
import zio.console._
import org.mongodb.scala._


object Main extends App {

  def Client(): ZIO[Any, Throwable, MongoClient] =
    ZIO.effect(MongoClient())
  def Database(client: MongoClient): ZIO[Any, Throwable, MongoDatabase] =
    ZIO.effect(client.getDatabase("mydb"))
  def Collection(db: MongoDatabase): ZIO[Any, Throwable, MongoCollection[Document]] =
    ZIO.effect(db.getCollection("test"))
  def Insert(collection: MongoCollection[Document], doc: Document): ZIO[Any, Throwable, Observable[Completed]] =
    ZIO.effect(collection.insertOne(doc))
  def Subscribe(observable: Observable[Completed]): ZIO[Any, Throwable, Unit] =
    ZIO.effect(observable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = putStrLn("Inserted")
      override def onError(e: Throwable): Unit = putStrLn("Failed")
      override def onComplete(): Unit = putStrLn("Completed")
    }))
  def Close(mongoClient: MongoClient): ZIO[Any, Throwable, Unit] =
    ZIO.effect(mongoClient.close())


  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))

  override def run(args: List[String]): ZIO[Main.Environment, Nothing, Int] = {
    (for {
      _           <- putStrLn("Starting...")
      client      <- Client()
      database    <- Database(client)
      collection  <- Collection(database)
      observable  <- Insert(collection, doc)
      _           <- Subscribe(observable)
      _           <- getStrLn *> Close(client)
    } yield 0) orElse ZIO.succeed(1)
  }

}
