package info.sw0.spring.stateless_session;

import java.io.IOException;
import java.util.function.Supplier;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServletOutputStreamWrapper extends ServletOutputStream{

  private ServletOutputStream source;
  private Runnable setSessionData;
  public ServletOutputStreamWrapper(ServletOutputStream source, Runnable setSessionData){
    this.source = source;
    this.setSessionData = setSessionData;
  }
  @Override
  public boolean isReady() {
    return source.isReady();
  }

  @Override
  public void setWriteListener(WriteListener listener) {
    this.source.setWriteListener(listener);
  }

  @Override
  public void write(int b) throws IOException {
    this.source.write(b);
  }
  @Override
  public void close() throws IOException {
    this.setSessionData.run();
    this.source.close();
  }
  @Override
  public void flush() throws IOException {
    this.setSessionData.run();
    this.source.flush();
  }
  
}
