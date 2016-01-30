package com.donoroncall.server.rest.undertow

import com.donoroncall.server.rest.undertow.handlers.authentication.{LogoutApiHandler, RegistrationApiHandler, LoginApiHandler}
import com.donoroncall.server.rest.undertow.routes.ApiRoutes
import com.google.inject.Inject
import io.undertow.server.HttpHandler
import io.undertow.server.handlers.PathHandler

/**
  * Created by vishnu on 20/1/16.
  */
class Routes @Inject()(
                        apiRoutes: ApiRoutes,
                        loginApiHandler: LoginApiHandler,
                        logoutApiHandler: LogoutApiHandler,
                        registrationApiHandler: RegistrationApiHandler
                      ) {

  def getAllHandlers: HttpHandler = {
    new PathHandler()
      .addExactPath("/login", loginApiHandler)
      .addExactPath("/logout", logoutApiHandler)
      .addExactPath("/register", registrationApiHandler)
      .addPrefixPath("/api/", apiRoutes.pathHandler)
  }
}
