package docker.manager

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class DockerManagerFilesystemSpec extends MutableScalatraSpec {

    addServlet(classOf[DockerManager], "/*")


    "Loading account config file" should {
        "Throw an exception if file doesn't exist" in {
            val fs = new DockerApplianceFilesystem
            fs.load("testaccount") must throwA[Exception]
        }
    }

    "Preparing config file" should {
        "Return a simple JSON string" in {
            val fs = new DockerApplianceFilesystem
            fs.prepare("testaccount") must beEqualTo("{\"account\":\"testaccount\",\"apps\":{}}")
        }
    }




}
