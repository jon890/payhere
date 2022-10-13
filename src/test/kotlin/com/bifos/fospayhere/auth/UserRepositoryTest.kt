package com.bifos.fospayhere.auth

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository
){

    @Test
    @DisplayName("유저가 저장된다")
    fun `when_save_thenReturn_user`() {
        // given
        val user = User(email = "jon89071@gmail.com", password = "1234")
        entityManager.persist(user)
        entityManager.flush()

        // when
        // 실제로 Database에 쿼리를 날리기 위하여 TestEntityManager를 사용하여 flush
        val dbUser = user.id?.let {
            userRepository.findById(it).orElse(null)
        }

        // then
        // todo import static 쉽게 할 수 없나?
        Assertions.assertNotNull(dbUser)
        dbUser?.let {
            Assertions.assertEquals(user.id, dbUser.id)
            Assertions.assertEquals(user.email, dbUser.email)
            Assertions.assertEquals(user.password, dbUser.password)
        }
    }
}