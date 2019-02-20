package gov.nasa.gcptest;

import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import static gov.nasa.gcptest.State.*;

public class Bootstrap implements ServletContextListener
{
  private Logger log = LoggerFactory.getLogger(getClass());

  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    try
    {
      StackdriverTraceExporter.createAndRegister(StackdriverTraceConfiguration.builder().setProjectId("macs-engineering").build());
      traceEnabled = true;
    }
    catch (Exception e)
    {
      log.error("Unable to register stack trace driver", e);
    }
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {

  }
}
