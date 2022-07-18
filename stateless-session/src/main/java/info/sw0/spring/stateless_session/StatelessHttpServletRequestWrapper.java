package info.sw0.spring.stateless_session;

import java.io.Closeable;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class StatelessHttpServletRequestWrapper extends HttpServletRequestWrapper implements Closeable{

  private StatelessHttpSession session;
  public StatelessHttpServletRequestWrapper(HttpServletRequest request, SessionData sessionData) {
    super(request);
    this.session = new StatelessHttpSession(request, sessionData);
  }
  @Override
  public HttpSession getSession() {
    return this.session;
  }

  @Override
  public HttpSession getSession(boolean create) {
    return this.session;
  }
  @Override
  public void close() throws IOException {
    this.session.close();
    this.session = null;
  }
  

}
