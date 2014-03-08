package docker.manager

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class DockerManagerFilesystemSpec extends MutableScalatraSpec {

    "Getting set-up" should {
        "Clear out the test account if it still exists" >> {
            val fs = new DockerApplianceFilesystem
            fs.deleteAccount("testaccount") must beTrue
        }
    }

    "Loading account config file" should {
        "Throw an exception if file doesn't exist" >> {
            val fs = new DockerApplianceFilesystem
            fs.load("testaccount") must throwA[Exception]
        }
    }

    "Preparing config file" should {
        "Return a simple JSON string" >> {
            val fs = new DockerApplianceFilesystem
            fs.prepare("testaccount") must beEqualTo("""{"account":"testaccount","apps":{}}""")
        }
    }


    "Creating a new account" should {
        "Not throw an error on create" >> {
            val fs = new DockerApplianceFilesystem
            fs.createAccount("testaccount")
        }

        "Config file should now exist" >> {
            val fs = new DockerApplianceFilesystem
            fs.accountExists("testaccount") must beTrue
        }
    }

    "Deleting an account" should {
        "Not throw an error on delete" >> {
            val fs = new DockerApplianceFilesystem
            fs.deleteAccount("testaccount")
        }

        "Config file should no longer exist" >> {
            val fs = new DockerApplianceFilesystem
            fs.accountExists("testaccount") must beFalse
        }
    }




}
