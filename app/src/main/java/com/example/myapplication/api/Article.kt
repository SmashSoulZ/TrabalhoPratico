
data class MainArticle(
    val status: String,
    val totalResults: Byte,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val author: String,
    val url: String,
    val urlToImage: String
)

