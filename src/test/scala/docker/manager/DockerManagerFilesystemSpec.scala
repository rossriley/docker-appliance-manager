package docker.manager

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class DockerManagerFilesystemSpec extends MutableScalatraSpec {

    addServlet(classOf[DockerManager], "/*")


    "GET /" should {
        "return status 200" in {
            get("/") {
                status must_== 200
            }
        }
    }

    "GET /accounts" should {
        "return status 200" in {
            get("/accounts") {
                status must_== 200
            }
        }
    }




}
