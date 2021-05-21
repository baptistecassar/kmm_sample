package com.example.kmmapplication.shared.cache.shared

import com.example.kmmapplication.shared.cache.AppDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import comexamplekmmapplicationsharedcache.AppDatabaseQueries
import comexamplekmmapplicationsharedcache.Player
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE Player (
          |name TEXT NOT NULL,
          |email TEXT NOT NULL,
          |imageUrl TEXT
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val selectAllPlayers: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectAllPlayers(mapper: (
    name: String,
    email: String,
    imageUrl: String?
  ) -> T): Query<T> = Query(-1080752628, selectAllPlayers, driver, "AppDatabase.sq",
      "selectAllPlayers", "SELECT * FROM Player") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)
    )
  }

  override fun selectAllPlayers(): Query<Player> = selectAllPlayers(::Player)

  override fun insertPlayer(
    name: String,
    email: String,
    imageUrl: String?
  ) {
    driver.execute(-988280423, """
    |INSERT INTO Player(name, email, imageUrl)
    |VALUES(?, ?, ?)
    """.trimMargin(), 3) {
      bindString(1, name)
      bindString(2, email)
      bindString(3, imageUrl)
    }
    notifyQueries(-988280423, {database.appDatabaseQueries.selectAllPlayers})
  }

  override fun removeAllPlayers() {
    driver.execute(-372768876, """DELETE FROM Player""", 0)
    notifyQueries(-372768876, {database.appDatabaseQueries.selectAllPlayers})
  }
}
