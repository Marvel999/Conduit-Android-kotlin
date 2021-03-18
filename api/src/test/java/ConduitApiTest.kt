import com.example.api.ConduitClient
import com.example.api.model.entity.ArticleRequestEntity
import com.example.api.model.entity.UpdateUserProfileEntity
import com.example.api.model.entity.UserCred
import com.example.api.model.requests.CreateArticleRequest
import com.example.api.model.requests.UpdateProfileRequest
import com.example.api.model.requests.UserRequests
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

class ConduitApiTest {

    val conduitClient=ConduitClient
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

    @Test
    fun `Create Article`(){
        val createArticleRequest=CreateArticleRequest(ArticleRequestEntity(
            body = "This is test article by jathalal",
            title = "Jatalal Test Article",

            description = "This article is for testing api from jathalal",
            tagList = listOf("jatha","fun","test")
        ))
        ConduitClient.authToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTQ5Nzg3LCJ1c2VybmFtZSI6ImphdGhhIiwiZXhwIjoxNjIxMjAxNjIwfQ.7jAsGGPaa3bkbqQ6awMhP3v__L3BHSO9uc83hszqdhw"
        runBlocking {
           print(conduitClient.authToken)
            val article=ConduitClient.authApi.createArticles(createArticleRequest)
            assertEquals(article.body()?.article?.body,createArticleRequest.articleRequestEntity.body)

        }
    }

    @Test
    fun `get profile`(){
        ConduitClient.authToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTQ5Nzg3LCJ1c2VybmFtZSI6ImphdGhhIiwiZXhwIjoxNjIxMjAxNjIwfQ.7jAsGGPaa3bkbqQ6awMhP3v__L3BHSO9uc83hszqdhw"

        runBlocking {
            print(conduitClient.authToken)
            val article=ConduitClient.authApi.getProfile("jatha")
            assertEquals(article.body()?.profile?.username,"jatha")


        }
    }

    @Test
    fun `update profile`(){
        ConduitClient.authToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTQ5Nzg3LCJ1c2VybmFtZSI6ImphdGhhIiwiZXhwIjoxNjIxMjAxNjIwfQ.7jAsGGPaa3bkbqQ6awMhP3v__L3BHSO9uc83hszqdhw"
        val updateProfileReques=UpdateProfileRequest(UpdateUserProfileEntity(bio = "Hello jatha",
        email = "jatha123@gmail.com",
        image = "https://static-koimoi.akamaized.net/wp-content/new-galleries/2020/10/dilip-joshi-turns-into-jethalal-of-taarak-mehta-ka-ooltah-chashmah-irl-this-picture-is-the-proof001.jpg?format=webp&compress=true&quality=80&w=720&dpr=1.0",
        username ="jathalal" ,
        password ="jatha1245" ))
        runBlocking {
            print(conduitClient.authToken)
            val article=ConduitClient.authApi.updateProfile(updateProfileReques)
            assertEquals(article.body()?.user?.username,"jathalal")


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