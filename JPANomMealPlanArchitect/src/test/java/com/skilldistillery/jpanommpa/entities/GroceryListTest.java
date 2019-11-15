package com.skilldistillery.jpanommpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroceryListTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GroceryList gc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("NomMPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		gc = em.find(GroceryList.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		gc = null;
	}

	@DisplayName("Testing GroceryList id field")
	@Test
	void test1() {
		assertEquals(1, gc.getId());
	}
	@DisplayName("Testing GroceryList mealPlan field")
	@Test
	void test2() {
		assertEquals(1, gc.getMealPlan());
	}
	@DisplayName("Testing GroceryList purchased field")
	@Test
	void test3() {
		assertEquals(0, gc.getId());
	}

}
