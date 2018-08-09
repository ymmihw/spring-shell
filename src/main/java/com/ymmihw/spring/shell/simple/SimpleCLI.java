package com.ymmihw.spring.shell.simple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class SimpleCLI {

  private String getContentsOfUrlAsString(URL url) {
    StringBuilder sb = new StringBuilder();
    try {
      try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          sb.append(inputLine);
        }
      }
    } catch (IOException ex) {
      sb.append("ERROR");
    }
    return sb.toString();
  }

  @ShellMethod(key = {"web-get", "wg"}, value = "Displays the contents of a URL.")
  public String webGet(
      @ShellOption(value = {"", "--url"}, help = "URL whose contents will be displayed.") URL url) {
    return getContentsOfUrlAsString(url);
  }

  @ShellMethod(key = {"web-save", "ws"}, value = "Saves the contents of a URL.")
  public String webSave(
      @ShellOption(value = {"", "--url"}, help = "URL whose contents will be saved.") URL url,
      @ShellOption(value = {"out", "--file"}, help = "The name of the file.",
          defaultValue = "ws.txt") String file) {
    String contents = getContentsOfUrlAsString(url);
    try (PrintWriter out = new PrintWriter(file)) {
      out.write(contents);
    } catch (FileNotFoundException ex) {
      // Ignore
    }
    return "Done.";
  }

  private boolean adminEnableExecuted = true;

  @ShellMethodAvailability({"web-save"})
  public Availability isAdminEnabled() {
    if (adminEnableExecuted) {
      return Availability.available();
    } else {
      return Availability.unavailable("the command is for admin only");
    }
  }

  @ShellMethod(key = "admin-enable", value = "enbale admin")
  public String adminEnable() {
    adminEnableExecuted = true;
    return "Admin commands enabled.";
  }

  @ShellMethod(key = "admin-disable", value = "disable admin")
  public String adminDisable() {
    adminEnableExecuted = false;
    return "Admin commands disabled.";
  }
}
