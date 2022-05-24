package com.knoldus.chat_messenger.repository;
import com.knoldus.chat_messenger.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByMobile(long mobile);
    @Query(value = "SELECT * FROM user WHERE MOBILE = ?1 AND PASSWORD =?2  ", nativeQuery = true)
    User fetchUser(long mobile, String password);


}
