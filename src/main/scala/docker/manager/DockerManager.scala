package docker.manager

import org.scalatra._
import scalate.ScalateSupport

class DockerManager extends DockerApplianceManagerStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/test") {

  }

}
