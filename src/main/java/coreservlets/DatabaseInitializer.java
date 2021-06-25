package coreservlets;

import javax.servlet.*;
import java.text.ParseException;

/** Makes sure database and employees table exists. Runs at Web app
 *  startup, but even that may be too often, so code has try/catch
 *  internally to prevent problems if table already exists.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT,
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

public class DatabaseInitializer implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    try {
      new EmbeddedDbCreator().createDatabase();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void contextDestroyed(ServletContextEvent event) {}
}
