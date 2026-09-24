package org.aulune.rajtigo


import cats.effect.IO
import cats.effect.IOApp
import com.comcast.ip4s.Host
import com.comcast.ip4s.Port
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.HttpRoutes


/** Placeholder main class. Real functionality lands in later commits. */
object App extends IOApp.Simple:
  private val port = sys.env
    .get("APP_PORT")
    .flatMap(_.toIntOption)
    .flatMap(Port.fromInt)
    .getOrElse(Port.fromInt(8080).get)

  private val routes = HttpRoutes.of[IO] { case GET -> Root =>
    Ok("Hello, world!")
  }

  override def run: IO[Unit] = EmberServerBuilder
    .default[IO]
    .withHost(Host.fromString("0.0.0.0").get)
    .withPort(port)
    .withHttpApp(routes.orNotFound)
    .build
    .use(_ => IO.never)
