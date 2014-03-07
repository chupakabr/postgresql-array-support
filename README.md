PostgreSQL array support grails plugin
===

This plugin wraps native PostgreSQL dialect which a support of Postgres array type for JDBC and GORM.

Usage
---

Exaple grails DataSource.groovy configuration:

```
dataSource {
    pooled = false

    driverClassName = "org.postgresql.Driver"
    dialect = "ru.chupakabr.sql.driver.PostgreSQLDialect"

    username = "postgres"
    password = "postgres"

    logSql = false
    dbCreate = "update"
}
```

Example SQL query:

```
final expected = [ [name: "one", leafs: [123, 555]] ]

// ... insert test data into DB at this stage and open session as well ...

// query: keep in mind that indexes in Postgres start from 1
def entries = session.createSQLQuery("""
	select id, name, cast(uuid as varchar)
	from tree
	where leafs[1] = ${expected[0]['leafs'][0]}
""").list()
assert expected[0]["name"] == entries[0][1]
```

Example usage of GORM:

```
Tree t = Tree.findAll()[0]
assert expected[0]["name"] == t.name
assert expected[0]["leafs"][0] == t.leafs[0]
assert expected[0]["leafs"][1] == t.leafs[1]
```


Authors
---

- [Valeriy Chevtaev](https://github.com/chupakabr)


Links
---
- [Code on GitHub](https://github.com/chupakabr/postgresql-array-support)
- [7bit](http://7bit.co)
