import com.example.api.ConduitClient
import com.example.api.service.ConduitApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

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

}