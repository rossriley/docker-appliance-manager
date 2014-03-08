package docker.manager

import org.scalatra._
import scalate.ScalateSupport
import scala.io._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._


case class Account(name: String, token: String)
case class Application(name: String, account: String, url: String, port: String)




class DockerManager extends DockerApplianceManagerStack with JacksonJsonSupport {

    protected implicit val jsonFormats: Formats = DefaultFormats

    val accounts = Source.fromFile("config/appliance.json").mkString



    get("/accounts") {
        contentType = formats("json")
        accounts
    }

    get("/testing") {
        contentType = formats("json")
        Account("testing2","testtoken")
    }

}


