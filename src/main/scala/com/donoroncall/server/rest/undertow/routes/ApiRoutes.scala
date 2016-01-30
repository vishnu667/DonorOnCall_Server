package com.donoroncall.server.rest.undertow.routes

import com.donoroncall.server.rest.undertow.handlers.DefaultApiHandler
import com.donoroncall.server.rest.undertow.handlers.admin.AdminApiHandler
import com.donoroncall.server.rest.undertow.handlers.user.GetUserHandler
import com.google.inject.Inject
import io.undertow.server.handlers.PathHandler

/**
  * Created by vishnu on 20/1/16.
  */
class ApiRoutes @Inject()(
                           defaultApiHandler: DefaultApiHandler,
                           adminApiHandler: AdminApiHandler,
                           getUserHandler: GetUserHandler

                         ) {

  val pathHandler = new PathHandler()
    .addExactPath("/", defaultApiHandler)
    .addExactPath("/get/user", getUserHandler)
}
