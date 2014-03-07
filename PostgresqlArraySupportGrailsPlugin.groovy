class PostgresqlArraySupportGrailsPlugin {

    // the plugin version
    def version = "1.0"

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.3 > *"

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
            "grails-app/views/index.gsp",
            "grails-app/domain/ru/chupakabr/grails/sql/driver/test/domain/Tree.groovy",
    ]

    def title = "Postgresql Array Support Plugin" // Headline display name of the plugin
    def author = "Valeriy Chevtaev"
    def authorEmail = "myltik@gmail.com"
    def description = '''\
This plugin introduces PostgreSQL dialect that adds a support of Postgres array type for JDBC and GORM.
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/chupakabr/postgresql-array-support"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "7bit", url: "http://www.7bit.co/" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/chupakabr/postgresql-array-support" ]

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { ctx ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
