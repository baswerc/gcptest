package gov.nasa.gcptest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimesServlet extends HttpServlet
{
  static final long DefaultNumberPrimes = 50000000l;

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    String numberPrimesParam = request.getParameter("number");

    long numberPrimes = DefaultNumberPrimes;
    if (numberPrimesParam != null && !numberPrimesParam.isEmpty())
    {
      try
      {
        numberPrimes = Long.parseLong(numberPrimesParam);
      }
      catch (Exception e)
      {}
    }
    log.info("On primes servlet " + numberPrimesParam);

    primeNumbersBruteForce(numberPrimes);
  }

  public static List<Integer> primeNumbersBruteForce(long n)
  {
    List<Integer> primes = new ArrayList<Integer>();
    if (n >= 2)
    {
    }
    for (int i = 3; i <= n; i += 2)
    {
      if (isPrimeBruteForce(i))
      {
        primes.add(i);
      }
    }

    return primes;
  }

  private static boolean isPrimeBruteForce(int number)
  {
    for (int i = 2; i * i < number; i++)
    {
      if (number % i == 0)
      {
        return false;
      }
    }
    return true;
  }
}


