package info.sw0.spring.sample_app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class StatelessHttpServletRequestWrapper extends HttpServletRequestWrapper{

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
  

}
