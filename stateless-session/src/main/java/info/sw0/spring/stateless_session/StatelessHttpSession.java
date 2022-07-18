package info.sw0.spring.stateless_session;

import java.io.Closeable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class StatelessHttpSession implements HttpSession, Closeable {

  private SessionData sessionData;
  private HttpServletRequest request;
  private HttpServletResponse response;
  public StatelessHttpSession(HttpServletRequest request,  SessionData sessionData){
    this.sessionData = sessionData;
    this.request = request;
    //this.response = response;
  }

  public SessionData getSessionData(){
    return this.sessionData;
  }
  private void wirteCookie(){
    if(!this.response.isCommitted()){

    }
  }
  @Override
  public long getCreationTime() {
    return this.sessionData.getCreationTime();
  }

  @Override
  public String getId() {
    return this.sessionData.getId();
  }

  @Override
  public long getLastAccessedTime() {
    return this.sessionData.getLastAccessedTime();
  }

  @Override
  public ServletContext getServletContext() {
    return this.request.getServletContext();
  }

  @Override
  public void setMaxInactiveInterval(int interval) {
    this.sessionData.setMaxInactiveInterval(interval);
  }

  @Override
  public int getMaxInactiveInterval() {
    return this.sessionData.getMaxInactiveInterval();
  }

  @Override
  public HttpSessionContext getSessionContext() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object getAttribute(String name) {
    return this.sessionData.getAttributes().get(name);
  }

  @Override
  public Object getValue(String name) {
    return this.getAttribute(name);
  }

  @Override
  public Enumeration<String> getAttributeNames() {
    return new EnumerationWrapper<String>(this.sessionData.getAttributes().keySet().iterator());
  }

  @Override
  public String[] getValueNames() {
    return this.sessionData.getAttributes().keySet().toArray(new String[0]);
  }

  @Override
  public void setAttribute(String name, Object value) {
    this.sessionData.getAttributes().put(name, value);

  }

  @Override
  public void putValue(String name, Object value) {
    this.setAttribute(name, value);
    
  }

  @Override
  public void removeAttribute(String name) {
    this.sessionData.getAttributes().remove(name);
  }

  @Override
  public void removeValue(String name) {
    this.removeAttribute(name);
  }

  @Override
  public void invalidate() {
    this.sessionData.setAttributes(new HashMap<String, Object>());
  }

  @Override
  public boolean isNew() {
    return this.sessionData.isNew();
  }
  @Override
  public void close() throws IOException {
    this.request = null;
    this.sessionData = null;
    
  }


  
}
