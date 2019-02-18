package gov.nasa.gcptest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PropertiesServlet extends HttpServlet
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    log.info("On info request");

    log.info("System Properties:");
    for (Map.Entry<Object, Object> keyValue : System.getProperties().entrySet())
    {
      log.info(keyValue.getKey() + " = " + keyValue.getValue());
    }

    log.info("Environment Properties:");
    for (Map.Entry<String, String> keyValue : System.getenv().entrySet())
    {
      log.info(keyValue.getKey() + " = " + keyValue.getValue());
    }
  }
}
