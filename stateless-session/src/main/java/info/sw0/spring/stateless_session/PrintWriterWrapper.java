package info.sw0.spring.stateless_session;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class PrintWriterWrapper extends PrintWriter{

  private PrintWriter source;
  private Runnable setSessionData;

  public PrintWriterWrapper(PrintWriter source, Runnable setSessionData){
    super(source);
    this.source = source;
    this.setSessionData = setSessionData;
  }
  public PrintWriter append(CharSequence csq) {
    return this.source.append(csq);
  }

  @Override
  public PrintWriter append(char c) {
    return this.source.append(c);
  }

  @Override
  public PrintWriter append(CharSequence csq, int start, int end) {
    return this.source.append(csq, start, end);
  }

  @Override
  public boolean checkError() {
    return this.source.checkError();
  }

  @Override
  public void close() {
    this.setSessionData.run();
    this.source.close();
  }

  @Override
  public void flush() {
    this.setSessionData.run();
    this.source.flush();
  }

  @Override
  public PrintWriter format(String format, Object... args) {
    return this.source.format(format, args);
  }

  @Override
  public PrintWriter format(Locale l, String format, Object... args) {
    return this.source.format(l, format, args);
  }

  @Override
  public void print(boolean b) {
    this.source.print(b);
  }

  @Override
  public void print(char c) {
    this.source.print(c);
  }

  @Override
  public void print(int i) {
    this.source.print(i);
  }

  @Override
  public void print(long l) {
    this.source.print(l);
  }

  @Override
  public void print(float f) {
    this.source.print(f);
  }

  @Override
  public void print(double d) {
    this.source.print(d);
  }

  @Override
  public void print(char[] s) {
    this.source.print(s);
  }

  @Override
  public void print(String s) {
    this.source.print(s);
  }

  @Override
  public void print(Object obj) {
    this.source.print(obj);
  }

  @Override
  public PrintWriter printf(String format, Object... args) {
    return this.source.printf(format, args);
  }

  @Override
  public PrintWriter printf(Locale l, String format, Object... args) {
    return this.source.printf(l, format, args);
  }

  @Override
  public void println() {
    this.source.println();
  }

  @Override
  public void println(boolean x) {
    this.source.println(x);
  }

  @Override
  public void println(char x) {
    this.source.println(x);
  }

  @Override
  public void println(int x) {
    this.source.println(x);
  }

  @Override
  public void println(long x) {
    this.source.println(x);
  }

  @Override
  public void println(float x) {
    this.source.println(x);
  }

  @Override
  public void println(double x) {
    this.source.println(x);
  }

  @Override
  public void println(char[] x) {
    this.source.println(x);
  }

  @Override
  public void println(String x) {
    this.source.println(x);
  }

  @Override
  public void println(Object x) {
    this.source.println(x);
  }

  @Override
  public void write(int c) {
    this.source.write(c);
  }

  @Override
  public void write(char[] buf) {
    this.source.write(buf);
  }

  @Override
  public void write(String s) {
    this.source.write(s);
  }

  @Override
  public void write(char[] buf, int off, int len) {
    this.source.write(buf, off, len);
  }

  @Override
  public void write(String s, int off, int len) {
    this.source.write(s, off, len);
  }

  @Override
  public boolean equals(Object obj) {
    return this.source.equals(obj);
  }

  @Override
  protected void finalize() throws Throwable {
    PrintWriter.class.getMethod("finalize").invoke(this.source);
  }

  @Override
  public int hashCode() {
    return this.source.hashCode();
  }

  @Override
  public String toString() {
    return this.source.toString();
  }
  
}
