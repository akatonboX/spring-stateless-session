package info.sw0.spring.stateless_session;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Supplier;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StatelessHttpServletResponseWrapper extends HttpServletResponseWrapper implements Closeable{

  private Runnable setSessionData;


  public StatelessHttpServletResponseWrapper(HttpServletResponse response, Runnable setSessionData) {
    super(response);
    this.setSessionData = setSessionData;
  }

  @Override
  public void close() throws IOException {

  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return new ServletOutputStreamWrapper(this.getResponse().getOutputStream(), this.setSessionData);
  }

  @Override
  public void flushBuffer() throws IOException {
    this.setSessionData.run();
    super.flushBuffer();
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    
    //return new PrintWriter(this.getOutputStream(), true);
    return new PrintWriterWrapper(super.getWriter(), this.setSessionData);
  }

 
  
}
