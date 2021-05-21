package com.example.kmmapplication.shared.cache

import com.example.kmmapplication.shared.cache.shared.newInstance
import com.example.kmmapplication.shared.cache.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import comexamplekmmapplicationsharedcache.AppDatabaseQueries

interface AppDatabase : Transacter {
  val appDatabaseQueries: AppDatabaseQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    operator fun invoke(driver: SqlDriver): AppDatabase = AppDatabase::class.newInstance(driver)}
}
