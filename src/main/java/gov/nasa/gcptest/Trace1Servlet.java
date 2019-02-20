package gov.nasa.gcptest;

import io.opencensus.common.Scope;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Trace1Servlet extends HttpServlet
{

  private Tracer tracer;

  static final Random random = new Random();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    if (!State.traceEnabled)
    {
      return;
    }

    if (tracer == null)
    {
      tracer = Tracing.getTracer();
    }

    try (Scope ss  = tracer.spanBuilder("GetUserAccountInfo").setSampler(Samplers.alwaysSample()).startScopedSpan())
    {
      tracer.getCurrentSpan().addAnnotation("Pulling account info from DB");
      try
      {
        Thread.sleep(random.nextInt(5000));
      }
      catch (InterruptedException e)
      {}


      tracer.getCurrentSpan().addAnnotation("Loading social profile");
      try
      {
        Thread.sleep(random.nextInt(15000));
      }
      catch (InterruptedException e)
      {}

      tracer.getCurrentSpan().addAnnotation("Loading social profile");
      try
      {
        Thread.sleep(random.nextInt(15000));
      }
      catch (InterruptedException e)
      {}

    }
  }
}
