package info.sw0.spring.stateless_session;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationWrapper<T> implements Enumeration<T>{
  private Iterator<T> source;
    
    public EnumerationWrapper(Iterator<T> source){
      this.source = source;
    }
    @Override
    public boolean hasMoreElements() {
      return this.source.hasNext();
    }

    @Override
    public T nextElement() {
      return this.source.next();
    }
}
