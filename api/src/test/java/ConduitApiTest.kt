import com.example.api.ConduitClient
import com.example.api.model.entity.UserCred
import com.example.api.model.entity.UserLoginRequest
import com.example.api.model.requests.LoginRequest
import com.example.api.model.requests.UserRequests
import com.example.api.service.ConduitApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*
import kotlin.random.Random.Default.nextInt

class ConduitApiTest {

    val conduitClient=ConduitClient()
    @Test
    fun getArticle(){
        runBlocking {
            val articles=conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }

    }
    @Test
    fun `get Article by author`(){
        runBlocking {
            val articles=conduitClient.api.getArticles(author = "444")
            assertNotNull(articles.body()?.articles)
        }

    }
    @Test
    fun `get Article by tag`(){
        runBlocking {
            val articles=conduitClient.api.getArticles(tag = Arrays.asList("js","dragon"))
            assertNotNull(articles.body()?.articles)
        }

    }
    @Test
    fun `get Article by favorite`(){
        runBlocking {
            val articles=conduitClient.api.getArticles(favorited = "444")
            assertNotNull(articles.body()?.articles)
        }

    }

    @Test
    fun `Create new user`(){
        val userCred=UserCred(email = "testmail332@test.com",
                            password = "Tu999999999",
                            username = "Marhwiojrioew")
        runBlocking {
            val user=conduitClient.api.addUser(UserRequests(userCred))
            assertEquals(userCred.username,user.body()?.user?.username)
        }
    }


//    @Test
//    fun `Login user`(){
//        val userCred=UserLoginRequest("jatha","jatha123@gmail.com")
//        runBlocking {
//            val user=conduitClient.api.loginRequest(LoginRequest(userCred))
//            assertEquals(userCred.userLoginRequest.email,user.body()?.user?.email)
//        }
//    }

}