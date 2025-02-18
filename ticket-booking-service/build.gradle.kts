plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "6.21.0"

}

group = "ai.ednova"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	implementation("org.redisson:redisson:3.17.6")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

spotless{
	java {
		googleJavaFormat("1.17.0")  // Or eclipse() if you're using Eclipse formatter
		trimTrailingWhitespace()
		endWithNewline()
		// Enforce LF line endings
		//lineEndings = 'UNIX'
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
