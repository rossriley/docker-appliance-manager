package docker.manager

import org.scalatra._
import scalate.ScalateSupport
import scala.io._
import java.io.File
import java.io.FileWriter

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}
import org.json4s.JsonDSL._
import org.scalatra.json._


/**
 * HostManager Class
 *
 * This class takes care of mapping the underlying host filesystem to individual docker containers.
 *
 * The structure by default looks like this:
 *
 * dockerapplicance/
 *   config/
 *   containers/
 *     account1/
 *       app_1/
 *         config/
 *         mounts/
 *       app_2/
 *         config/
 *         mounts/
 *       app_3/
 *         config/
 *         mounts/
 *     account2/
 *       app_1/
 *         config/
 *         mounts/
 *       app_2/
 *         config/
 *         mounts/
 *
 * The structure should be self-explanatory, multiple accounts, can each have multiple apps and inside each app directory
 * is the bootstrap config (that is the files needed to build the container) and the persistent mounts.
 *
 * There is also support for host-level reverse proxy support so any http requests by domain can be forwarded to the relevant
 * container. To make this simpler to follow, a top-level config directory will also maintain some information about the underlying
 * container configs to allow any necessary forwarding to happen. For example a request to account1.app2.example.com can be forwarded
 * on to the http server running on docker port 48987 (example only).
 *
 **/

class DockerApplianceFilesystem extends DockerApplianceManagerStack with JacksonJsonSupport {

    protected implicit val jsonFormats: Formats = DefaultFormats

    val base: String                    = "/dockerapplicance/"
    val baseDirectory: String           = "containers"
    val configDirectory: String         = "config"
    val mountDirectory: String          = "mounts"
    val trashDirectory: String          = "trash"
    val allowedAccounts: Array[String]  = Array()


    /** The only way to create an account currently is to add to the array in config/production.php
     *  If the account name exists, although there isn't a config file in the accounts folder, this method will seed
     *  the initial file.
     **/
    def createAccount(account:String) {
        val config = prepare(account)
        val f = configFile(account)
        f.createNewFile()
        val fw = new FileWriter(f);
        fw.write(config);
        fw.close();
    }

    def deleteAccount(account:String) :Boolean = {
        if(configFile(account).isFile() == false) return true
        configFile(account).delete()
    }

    def accountExists(account:String) :Boolean = {
        configFile(account).canRead()
    }


    /**
     *
     * The methods below take care of the filesystem operations for creating apps
     *
    **/

    def createApp(account:String, app:String) {

    }

    def renameApp(account:String, oldapp:String, newapp:String) {

    }

    def deleteApp(account:String, app:String) {

    }


    def load(account:String) : String = {
        val accountInfo = Source.fromFile("config/accounts/"+account+".json").mkString
        accountInfo
    }

    def save(account:String, settings:Array[String]) {

    }

    def prepare(account:String) :String = {
        val jsonTemplate =(("account" -> account) ~ ("apps" -> List()))
        compact(render(jsonTemplate))
    }

    def configFile(account:String) :File = {
        return new File("config/accounts/"+account+".json")
    }



}
