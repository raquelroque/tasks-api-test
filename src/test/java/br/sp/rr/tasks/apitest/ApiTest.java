package br.sp.rr.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI="http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void testDeveRetornarTarefas () {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void testDeveAdicionarTarefasComSucesso () {
		RestAssured.given()
			.body("{\"task\": \"Teste via API\", \"dueDate\":\"2021-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void testNaoDeveAdicionarTarefaInvalida () {
		RestAssured.given()
			.body("{\"task\": \"Teste via API\", \"dueDate\":\"2020-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message",CoreMatchers.is("Due date must not be in past")
					);
	}
}


