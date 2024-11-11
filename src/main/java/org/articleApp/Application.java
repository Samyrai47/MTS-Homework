package org.articleApp;

import java.util.List;
import org.articleApp.controller.Controller;

public class Application {
  private final List<Controller> controllers;

  public Application(List<Controller> controllers) {
    this.controllers = controllers;
  }

  public void start() {
    for (Controller controller : controllers) {
      controller.initializeEndpoints();
    }
  }
}
