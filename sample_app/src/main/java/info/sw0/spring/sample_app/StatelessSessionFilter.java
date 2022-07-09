package info.sw0.spring.sample_app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


public class StatelessSessionFilter implements Filter{

  private String cookieName;
  private long inactiveInterval;
  private String secret;
  private Algorithm algorithm;
  private JWTVerifier verifier;

  public StatelessSessionFilter(String cookieName, long inactiveInterval, String secret){
    this.cookieName = cookieName;
    this.inactiveInterval = inactiveInterval;
    this.secret = secret;
    this.algorithm = Algorithm.HMAC256(this.secret);
    this.verifier = JWT.require(algorithm).build();  
  }
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
    if(request instanceof HttpServletRequest){
      var now = new Date().getTime();
      var sessionData = this.getSessionData((HttpServletRequest)request).orElse(
        new SessionData(true, UUID.randomUUID().toString(), now, now, (int)this.inactiveInterval, new HashMap<String, Object>())
      );
      var requestWrapper = new StatelessHttpServletRequestWrapper((HttpServletRequest)request, sessionData);
      chain.doFilter(requestWrapper, response);
      sessionData.setLastAccessedTime(now);
      this.setSessionData((HttpServletResponse)response, sessionData);
    }
    else{
      chain.doFilter(request, response);
    }   
  }

  private Optional<SessionData> getSessionData(HttpServletRequest request){

    //セッションからデータ取得
    var decodedJWT = ((Supplier<Optional<DecodedJWT>>) () -> {
      if(request.getCookies() != null){
        try{
          return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(this.cookieName)).findFirst().map(cookie -> this.verifier.verify(cookie.getValue()));
        }
        catch (JWTVerificationException exception){
          return Optional.empty();
        }
      }
      else{
        return Optional.empty();
      }
    }).get();
    if(decodedJWT.isEmpty())return Optional.empty();
    
    //■base64デコード
    var bytes = Base64.getDecoder().decode(decodedJWT.get().getClaim("data").asString());
    
    //■デシリアライズ
    return ((Supplier<Optional<SessionData>>) () -> {
      try (
        var inputStream = new ByteArrayInputStream(bytes);
        var objectInputStream = new ObjectInputStream(inputStream)
      ) {
        return Optional.of((SessionData)objectInputStream.readObject());
      } 
      catch (Exception e) {
        e.printStackTrace();
        return Optional.empty();
      }
    }).get();
  }

  private void setSessionData(HttpServletResponse response, SessionData sessionData){
    //■sessionデータをシリアライズ
    var bytes = ((Supplier<byte[]>) () -> {
      try(
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var outStream = new ObjectOutputStream(byteArrayOutputStream);
      ){
        outStream.writeObject(sessionData);
        outStream.flush();
        outStream.reset();
        return byteArrayOutputStream.toByteArray();
      }
      catch(IOException exception){
        return new byte[0];
      }
    }).get();

    //■base64エンコード
    var str = Base64.getEncoder().encodeToString(bytes);

    //■JWTトークン生成
    Date expireTime = new Date();
    expireTime.setTime(expireTime.getTime() + this.inactiveInterval);
    var token = JWT.create()
      .withExpiresAt(expireTime)
      .withClaim("data", str)
      .sign(this.algorithm);

    //■Cookie生成
    var cookie = new Cookie(this.cookieName, token);
    cookie.setMaxAge(265 * 24 * 60 * 60); 
    cookie.setPath("/"); 
    response.addCookie(cookie);
  }

}
