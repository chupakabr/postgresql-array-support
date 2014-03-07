package ru.chupakabr.grails.sql.driver.test.domain

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.junit.After
import org.junit.Before
import org.junit.Test

class TreeIntegrationTests {

    // Test data
	final expected = [
			[name: "first", leafs: [111, 99], roots: ["someR", "'X,K,u\"zxc\"qwe", ""], cells: [55.77], children: [789l], uuid: UUID.randomUUID()],
			[name: "2nd", leafs: [100, 200, 300, 400, 500], roots: null, cells: [], children: [500l, 700l], uuid: UUID.randomUUID()]
		]
	
	@Before
	void setup() {
        for (int i = 0; i < expected.size(); ++i) {
            new Tree(name: expected[i]["name"],
                    leafs: expected[i]["leafs"],
                    roots: expected[i]["roots"],
                    cells: expected[i]["cells"],
                    children: expected[i]["children"],
                    uuid: expected[i]["uuid"]
                ).save(flush:true)
        }
	}
	
	@After
	void tearDown() {
		Tree.executeUpdate("delete from Tree")
	}

	@Test
	void testContidionalSelectSqlReturningNoArrays() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def entries = session.createSQLQuery("select id, name, cast(uuid as varchar) from tree where leafs[1] = ${expected[0]['leafs'][0]}").list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
            assert expected[i]["uuid"] == UUID.fromString(it[2])
			++i
		}
	}
	
	@Test
	void testConditionalSelectSqlReturningIntegerArray() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def q = session.createSQLQuery("select id, name, leafs from tree where leafs[1] = :val")
		q.setParameter("val", expected[0]["leafs"][0])
		assert q != null

		def entries = q.list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
			assert expected[i]["leafs"] == it[2]
			++i
		}
	}
	
	@Test
	void testConditionalSelectSqlReturningIntegerAndTextArrays() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def q = session.createSQLQuery("select id, name, leafs, roots, cells, children, cast(uuid as varchar) from tree where roots[2] = :val")
		q.setParameter("val", expected[0]["roots"][1])
		assert q != null

		def entries = q.list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
			assert expected[i]["leafs"] == it[2]
			assert expected[i]["roots"] == it[3]
			assert expected[i]["cells"] == it[4]
			assert expected[i]["children"] == it[5]
            assert expected[i]["uuid"] == UUID.fromString(it[6])
		    ++i
		}
	}
	
	@Test
	void testConditionalSelectSqlReturningArrayNoQueryParams() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def entries = session.createSQLQuery("select id, name, leafs, roots, cells, children, cast(uuid as varchar) from tree where leafs[1] = ${expected[0]['leafs'][0]}").list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
			assert expected[i]["leafs"] == it[2]
			assert expected[i]["roots"] == it[3]
			assert expected[i]["cells"] == it[4]
			assert expected[i]["children"] == it[5]
            assert expected[i]["uuid"] == UUID.fromString(it[6])
			++i
		}
	}
	
	@Test
	void testConditionalSelectSqlReturningArrayIndexValue() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def entries = session.createSQLQuery("select id, name, leafs[1], roots[2], cells[1], children[1] from tree where leafs[1] = ${expected[0]['leafs'][0]}").list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
			assert expected[i]["leafs"][0] == it[2]
			assert expected[i]["roots"][1] == it[3]
			assert expected[i]["cells"][0] == it[4]
			assert expected[i]["children"][0] == it[5]
			++i
		}
	}
	
	@Test
	void testConditionalSelectSqlReturningAll() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def entries = session.createSQLQuery("select id, name, leafs, roots, cells, children, cast(uuid as varchar) from tree where leafs[1] = ${expected[0]['leafs'][0]}").list()
		assert 1 == entries.size()
		
		def i = 0
		entries.each {
			assert null != it[0]
			assert it[0] > 0
			assert expected[i]["name"] == it[1]
			assert expected[i]["leafs"] == it[2]
			assert expected[i]["roots"] == it[3]
			assert expected[i]["cells"] == it[4]
			assert expected[i]["children"] == it[5]
            assert expected[i]["uuid"] == UUID.fromString(it[6])
			++i
		}
	}

    @Test
    void testSelectSqlWithAggregationMax() {
        def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
        def q = session.createSQLQuery("select max(leafs[1]) from tree")
        assert q != null

        def entries = q.list()
        assert 1 == entries.size()
        assert null != entries[0]

        def max = { x, y ->
            return (x > y ? x : y)
        }
        assert max(expected[0]["leafs"][0], expected[1]["leafs"][0]) == entries[0]
    }

    @Test
	void testSelectSqlWithAggregationSum() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		def q = session.createSQLQuery("select sum(leafs[1]) from tree")
		assert q != null

		def entries = q.list()
		assert 1 == entries.size()
		
		assert null != entries[0]
		assert expected[0]["leafs"][0] + expected[1]["leafs"][0] == entries[0]
	}
	
	@Test
	void testConditionalSelectSqlEmptyResults() {
		def session = ApplicationHolder.application.mainContext.sessionFactory.currentSession
		assert 0 == session.createSQLQuery("select id, name from tree where leafs[1] = ${expected[0]['leafs'][0]+100}").list().size()
	}
	
	@Test
	void testGormUpdate() {
		assert Tree.count() == 2
		Tree t = Tree.findAll()[0]
		def id = t.id
		def name = t.name
		def leaf1 = t.leafs[0]
		def root2 = t.roots[1]
		def cell1 = t.cells[0]
		def child1 = t.children[0]

		assert expected[0]["name"] == t.name
		assert expected[0]["leafs"][0] == t.leafs[0]
		assert expected[0]["roots"][0] == t.roots[0]
		assert expected[0]["cells"][0] == t.cells[0]
		assert expected[0]["children"][0] == t.children[0]
        assert expected[0]["uuid"] == t.uuid

        final newUUID = UUID.randomUUID()
        t.uuid = newUUID
		t.name = name + "_2nd"
		t.leafs[0] = leaf1+1
		t.roots[1] = root2+"_2nd"
		t.cells[0] = cell1+1.115
		t.children[0] = child1+10
        t.save(flush:true)
		assert Tree.count() == 2
		
		Tree newTree = Tree.get(id)
		assert name+"_2nd" == newTree.name
		assert leaf1+1 == newTree.leafs[0]
		assert root2+"_2nd" == newTree.roots[1]
		assert cell1+1.115 == newTree.cells[0]
		assert child1+10 == newTree.children[0]
        assert newUUID == newTree.uuid
	}
	
	@Test
	void testGormDelete() {
		assert Tree.count() == 2

		new Tree(name: expected[0]["name"]+"2nd", leafs: expected[0]["leafs"]).save(flush:true)
		assert Tree.count() == 3
		
		Tree.findAll().each {
			it.delete(flush:true)
		}
		assert Tree.count() <= 0
	}

    @Test
    void testSelectWithGorm() {
        def entries = Tree.findAll()
        assert 2 == entries.size()

        def i = 0
        entries.each {
            assert null != it.id
            assert it.id > 0
            assert expected[i]["name"] == it.name
            assert expected[i]["leafs"] == it.leafs
            assert expected[i]["roots"] == it.roots
            assert expected[i]["cells"] == it.cells
            assert expected[i]["children"] == it.children
            assert expected[i]["uuid"] == it.uuid
            i++
        }
    }

    @Test
    void testSelectHqlAll() {
        def entries = Tree.executeQuery("from Tree")
        assert 2 == entries.size()

        def i = 0
        entries.each {
            assert null != it.id
            assert it.id > 0
            assert expected[i]["name"] == it.name
            assert expected[i]["leafs"] == it.leafs
            assert expected[i]["roots"] == it.roots
            assert expected[i]["cells"] == it.cells
            assert expected[i]["children"] == it.children
            assert expected[i]["uuid"] == it.uuid
            i++
        }
    }

}
