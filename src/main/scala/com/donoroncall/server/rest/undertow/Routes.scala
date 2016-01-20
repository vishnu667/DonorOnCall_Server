package com.donoroncall.server.rest.undertow

import com.donoroncall.server.rest.undertow.routes.ApiRoutes
import com.google.inject.Inject
import io.undertow.server.HttpHandler
import io.undertow.server.handlers.PathHandler

/**
  * Created by vishnu on 20/1/16.
  */
class Routes @Inject()(
                        apiRoutes: ApiRoutes
                      ) {

  def getAllHandlers: HttpHandler = {
    new PathHandler().addPrefixPath("/api/", apiRoutes.pathHandler)
  }
}
