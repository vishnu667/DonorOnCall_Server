package com.donoroncall.server.rest.undertow.routes

import com.donoroncall.server.rest.undertow.handlers.DefaultApiHandler
import com.donoroncall.server.rest.undertow.handlers.admin.AdminApiHandler
import com.donoroncall.server.rest.undertow.handlers.bloodRequest.{GetBloodRequestHandler, RegisterBloodRequestHandler}
import com.donoroncall.server.rest.undertow.handlers.user.GetUserHandler
import com.google.inject.Inject
import io.undertow.server.handlers.PathHandler

/**
  * Created by vishnu on 20/1/16.
  */
class ApiRoutes @Inject()(
                           defaultApiHandler: DefaultApiHandler,
                           adminApiHandler: AdminApiHandler,
                           getUserHandler: GetUserHandler,
                           registerBloodRequestHandler: RegisterBloodRequestHandler,
                           getBloodRequestHandler: GetBloodRequestHandler

                         ) {

  val pathHandler = new PathHandler()
    .addExactPath("/", defaultApiHandler)
    .addExactPath("/user/get", getUserHandler)
    .addExactPath("/bloodRequest/register", registerBloodRequestHandler)
    .addExactPath("/bloodRequest/get", getBloodRequestHandler)
}
