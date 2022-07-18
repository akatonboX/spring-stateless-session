package info.sw0.spring.stateless_session;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionData implements Serializable{
  private boolean isNew;
  private String id;
  private long lastAccessedTime;
  private long creationTime;
  private int maxInactiveInterval;
  private Map<String, Object> attributes;
}
