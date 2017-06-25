package sample.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sample.model.User;
import sample.model.UserWithSalt;
import sample.repository.UserRepository;

@Service
public class TestingUserDetailService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(TestingUserDetailService.class);

  @Autowired
  private UserRepository userRepository;
  
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    logger.debug("Load user by username: " + userName);
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    User user = userRepository.findByUserName(userName);
    if (user == null) {
      logger.error("User not found");
      throw new UsernameNotFoundException("User not found");
    }

    authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
    return new UserWithSalt(user.getUserName(), user.getUserName(), user.getPassword(),
        authorities);
  }

}
