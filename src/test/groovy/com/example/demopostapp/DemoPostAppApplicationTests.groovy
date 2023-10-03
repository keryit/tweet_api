package com.example.demopostapp

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoPostAppApplicationTests {

	@Test
	void contextLoads() {
	}

	/*
	login correct
	login incorrct email
	incorrect pass

	register correct
	register email les 4
	pass less 4
	register the same email - error

	logout

	creat post
	with empty content

	update
	update empty
	update other - error

	delete
	delete other - error
	delete wrong id

	showall - for user only
	showall empty for user withot posts


	* */

}
