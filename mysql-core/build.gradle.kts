import org.springframework.boot.gradle.tasks.bundling.BootJar


val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    // Database
    implementation("mysql:mysql-connector-java:8.0.33")

    // Querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
