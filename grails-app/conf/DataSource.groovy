dataSource {
    pooled = false

    driverClassName = "org.postgresql.Driver"
    dialect = "ru.chupakabr.sql.driver.PostgreSQLDialect"

    username = "postgres"
    password = "postgres"

    logSql = false
    dbCreate = "update"
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

db_props = {
    maxActive = -1
    minEvictableIdleTimeMillis=1800000
    timeBetweenEvictionRunsMillis=1800000
    numTestsPerEvictionRun=3
    testOnBorrow=true
    testWhileIdle=true
    testOnReturn=true
    validationQuery="SELECT 1"
}

// environment specific settings
environments {
    development {
        dataSource {
            url = "jdbc:postgresql://localhost:5432/array_support_test"
            properties = db_props
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:postgresql://localhost:5432/array_support_test"
            properties = db_props
        }
    }
    production {
        dataSource {
            url = "jdbc:postgresql://localhost:5432/array_support_test"
            pooled = true
            properties = db_props
        }
    }
}
