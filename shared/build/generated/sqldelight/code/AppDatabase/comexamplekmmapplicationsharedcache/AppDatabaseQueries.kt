package comexamplekmmapplicationsharedcache

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String

interface AppDatabaseQueries : Transacter {
  fun <T : Any> selectAllPlayers(mapper: (
    name: String,
    email: String,
    imageUrl: String?
  ) -> T): Query<T>

  fun selectAllPlayers(): Query<Player>

  fun insertPlayer(
    name: String,
    email: String,
    imageUrl: String?
  )

  fun removeAllPlayers()
}
