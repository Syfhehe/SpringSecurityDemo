package sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import sample.model.Role;
import sample.model.User;
import sample.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class WebApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;


  private static String userName = "ccc@ibm.com";
  private static String password = "123456";

  @Before
  public void setUp() {
    User user = userRepository.findByUserName(userName);
    if (user == null) {
      userRepository.save(
          new User(userName, "1e85641a45ac9c9304216a6edc109b41", Role.DEVELOPER, true));
    }
  }

  @Test
  public void accessUnprotected() throws Exception {
    this.mockMvc.perform(get("/login")).andExpect(status().isOk());
  }

  @Test
  public void accessProtectedRedirectsToLogin() throws Exception {
    MvcResult mvcResult =
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andReturn();
    assertThat(mvcResult.getResponse().getRedirectedUrl()).endsWith("/login");
  }

  @Test
  public void loginUser() throws Exception {
    this.mockMvc.perform(formLogin().user(userName).password(password)).andExpect(authenticated());
  }

  @Test
  public void loginInvalidUser() throws Exception {
    this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
        .andExpect(unauthenticated()).andExpect(status().is3xxRedirection());
  }

}
