

dependencies {
    implementation(project(":mysql-core"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}