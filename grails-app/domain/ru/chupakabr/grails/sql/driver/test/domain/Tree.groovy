package ru.chupakabr.grails.sql.driver.test.domain

import ru.chupakabr.sql.driver.types.DoubleArray
import ru.chupakabr.sql.driver.types.IntegerArray
import ru.chupakabr.sql.driver.types.LongArray
import ru.chupakabr.sql.driver.types.TextArray

/**
 * Test domain class which has arrays of different types and UUID property as well
 */
class Tree {

    UUID uuid
    String name

    Integer[] leafs
    String[] roots
    Double[] cells
    Long[] children

    static mapping = {
        uuid type:"pg-uuid"

        leafs type: IntegerArray
        roots type: TextArray
        cells type: DoubleArray
        children type: LongArray
    }

    static constraints = {
        uuid(nullable:true)

        leafs(nullable:true)
        roots(nullable:true)
        cells(nullable:true)
        children(nullable:true)
    }
}
