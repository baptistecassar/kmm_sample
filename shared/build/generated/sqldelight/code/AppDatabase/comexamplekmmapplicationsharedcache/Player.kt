package comexamplekmmapplicationsharedcache

import kotlin.String

data class Player(
  val name: String,
  val email: String,
  val imageUrl: String?
) {
  override fun toString(): String = """
  |Player [
  |  name: $name
  |  email: $email
  |  imageUrl: $imageUrl
  |]
  """.trimMargin()
}
