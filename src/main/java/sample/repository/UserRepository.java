package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserName(String userName);
  
  @Query("from User u where u.userName=:userName")
  User findUser(@Param("userName") String userName);
  
}
