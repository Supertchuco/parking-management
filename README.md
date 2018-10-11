

This is an API to Parking management, I developed this API using Intellij IDE and these technologies:

    Springboot;
    Gradle;
    Oracle Database;
    H2 (memory database);
    Swagger;
    Lombok;
    Actuator;
    Spring rest.

This API allow these operations:



You can check the all rest operations and the payloads through Swagger, to do it you need to running the application and access this url "http://localhost:8090/pms/swagger-ui.html"

This project have these kind of tests:

    Integration Tests (16 tests): EndToEndImageIT and EndToEndProductIT classes;
    Unity Tests (26 tests): ImageServiceTest and ProductServiceTest classes.

Note: You can run the tests through Intellij IDE or call direct in Gradle (execute the command "gradlew clean test" in cmd prompt)

Step to running the project:

    Intellij IDE:
        Import the project like Gradlew Project;
        Mark the check box Enable Annotation Processing in Settings->Build->Execution->Deployment->Compiler->Annotation Processor
        Run the major class ProductManagementApplication.

    Without an IDE:
        Execute the command "gradlew clean build" inside the project in a cmd prompt;
        Execute command "java -jar Jar_Name.jar" in "product-management\build\libs" directory.

In both of cases you can check if the application is running accessing this url "http://localhost:8090/productManagement/api/health" (actuator feature);

Important Note: This project is configured to create the database structure every time that the application is started with specified data (you can check it in DatabaseLoader class), if you want to disable it, you need to comment (#) or delete this line "spring.jpa.hibernate.ddl-auto=create" in application.properties. This project use a memory database (Spring H2) but also it is configured to use Oracle database, to use it you just need to configure the connection in application.properties (there is my configuration commented in application.properties, you can follow it like an example).

If you have questions, please feel free to contact me.
