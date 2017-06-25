package sample.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserWithSalt extends User {

  private static final long serialVersionUID = 1L;
  
  private String salt;

  public UserWithSalt(String username, String salt, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.salt = salt;
  }

  public String getSalt() {
    return salt;
  }
}
