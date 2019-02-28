package gov.nasa.gcptest;

import io.opencensus.common.Scope;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Trace2Servlet extends HttpServlet
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

    try (Scope ss  = tracer.spanBuilder("ListDocuments").setSampler(Samplers.alwaysSample()).startScopedSpan())
    {
      tracer.getCurrentSpan().addAnnotation("Pulling documents from DB");
      try
      {
        Thread.sleep(random.nextInt(5000));
      }
      catch (InterruptedException e)
      {}


      tracer.getCurrentSpan().addAnnotation("Filtering inactive documents");
      try
      {
        Thread.sleep(random.nextInt(2500));
      }
      catch (InterruptedException e)
      {}

      tracer.getCurrentSpan().addAnnotation("Encoding to JSON");
      try
      {
        Thread.sleep(random.nextInt(1000));
      }
      catch (InterruptedException e)
      {}


      if (random.nextInt(10) == 5)
      {
        tracer.getCurrentSpan().setStatus(Status.INTERNAL);
      }
      else
      {
        tracer.getCurrentSpan().setStatus(Status.OK);
      }
    }
  }
}
