# Spring and Spring Boot

Presenter: Ken Kousen (ken.kousin@kousenit.com)

Slides: tinyurl.com/y6j6zvds

Steps: http://www.kousenit.com/springboot/

Repo: https://github.com/kousen/spring-and-spring-boot

Twitter: [@kenkousin](https://twitter.com/kenkousen)

## Java
* Like so many java projects, there are decades of legacy domain knowledge behind all these decisions.

## Spring
* Spring provides services in a declarative fashion. You provide the metadata and Spring will create it for you.
* These tutorials will try to use Restful routes and other fancy stuff because it's a powerful framework.
* A bean is a POJO (a plain old java object)
* Spring has a library of beans that we can use.
  * transaction managers
  * db connection pools
  * rest client
  * testing mechanisms
* Need metadata to tell spring what to instantiate and configure. All are still supported.
  * XML (yuck)
  * Annotations (better than XML)
  * JavaConfig (preferred, added in Spring 3) The code is never called directly but Spring brings it into the dependencies.

## Spring Boot
* About 5 years old. A way to autoconfigure a Spring app quickly without all that copy and paste.
* It's nice and "lightweight", but when things go wrong it's harder to debug. Duh.
* Owned by Pivotal, so you'll get the shill in the docs

## Interfaces
* Library has many interfaces, each with many implementations.
* So it's best to code with interfaces and chose which you want

## Application Context
* A collection of beans that Spring is managing
* When we say we want something, Spring pulls it out of the application Context
* Every bean is assumed to be a singleton class
* It's assumed you're using Maven or Gradle to manage things
* Autoconfiguration is based on class path.
  * If you add the JDBC driver, it adds the DataSource bean
* This is like the Rails asset pipeline. Everything is available within it.

## Dependency Injection
* Spring adds dependencies on request (IOC is an old term)
  * Annotate field, a setter, or constructer
  * `@Autowired` is autowiring by type
  * `@Resource` is from Java EE, autowiring by bean name, then by type

## Spring Initializr
* https://start.spring.io/
* Handles dependencies of Java version and packages
* `war` is an old web thing. Now we use `jar` obviously.
* Then import
* Spring Tools Suite 4 Launcher ?

## Demo
* `@SpringBootApplication` composite annotation
* `@ComponentScan` will scan for all `@Component`s within the package (like `@Controller`)
* `@RunWith(SpringRunner.class)` will allow dependency injection and cache it
* The build file is small but imports like 50 jars from Maven central
* `$ gradlew bootRun` will watch the files for changes

## The Controller
```java
package com.oreilly.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // example of a component
public class HelloController {

    @GetMapping("/hello") // GET localhost::8080/hello
    public String sayHello(
            // optional param with default of "world"
            @RequestParam(value = "name", required = false,
                          defaultValue = "World") String name, Model model) { // model will map keys and vals between resources
        model.addAttribute("user", name); // put the name param value on key "user"
        // model is forwarded to the view automatically
        // some ViewResolver oldschool stuff used to need to happen here
        return "hello"; // resolver looks for 'hello.html' magically
    }
}
```

## The View
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello, World!</title>
</head>
<body>
<h2 th:text="'Hello, ' + ${user} + '!'"></h2> (thymeleaf header thingy)
</body>
</html>
```

## Tests
* Mockito and `@MockBean` will add it into application context

```java
package com.oreilly.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.*;

public class HelloControllerUnitTests {

    @Test
    public void testSayHello() throws Exception {
      HelloController controller = new HelloController();
      Model model = new BindingAwareModelMap(); // to test that inner working of Model
      String result = controller.sayHello("World", model);
      assertEquals("World", model.asMap().get("user"));
      assertEquals("hello", result);
    }
}
```

```java
package com.oreilly.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerIntegrationTests {
    @Autowired
    private MockMvc mvc; // mock the controller and related classes

    @Test
    public void testHelloWithoutName() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN)) // a MockMvcRequestBuilders
                .andExpect(status().isOk()) // 200
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("user", is("World")));

    }

    @Test
    public void testHelloWithName() throws Exception {
        mvc.perform(get("/hello").param("name", "Dolly").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("user", is("Dolly")));
    }
}
````

## Other Links
* https://micronaut.io/ is a cool new framework
* https://spring.io/ for docs

## Rest Controller Demo
* http://localhost:8080/rest?name=Dolly

## Application.properties
* `application.properties` sets database access, port number, many more things
* You can override every default that Spring establishes

## Spring Tools for VScode
* https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack

## Client example
* `@Service` is `@Component` by another name, it just says it is the buisness logic place.
* `RestTemplateBuilder`
* Spring is autowiring the single constructor, but say `@Autowired` anyway
* GDD (guilt driven development) "Oh golly, I should write tests".
* `Logger` is already there.

# Java Database Connectivity
* http://www.kousenit.com/springboot/#_using_the_jdbc_template
* The reactive db and relational databases do not play well together
* Reactive Drivers are async
* Spring is working on Reactive Relational (R2) JDBC
* Beans can be in various profiles

* One of the features of Spring Boot is that you can create and populate database tables by define scripts with the names schema.sql and data.sql in the src/main/resources folder. First define the database table in schema.sql
* (Rails does this much more clearly imo)
## schema.sql
```sql
DROP TABLE IF EXISTS officers;
CREATE TABLE officers (
  id         INT         NOT NULL AUTO_INCREMENT,
  rank       VARCHAR(20) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);
```
## data.sql
```sql
INSERT INTO officers(rank, first_name, last_name) VALUES('CAPTAIN', 'James', 'Kirk');
INSERT INTO officers(rank, first_name, last_name) VALUES('CAPTAIN', 'Jean-Luc', 'Picard');
INSERT INTO officers(rank, first_name, last_name) VALUES('CAPTAIN', 'Benjamin', 'Sisko');
INSERT INTO officers(rank, first_name, last_name) VALUES('CAPTAIN', 'Kathryn', 'Janeway');
INSERT INTO officers(rank, first_name, last_name) VALUES('CAPTAIN', 'Jonathan', 'Archer');
```

## Autowiring
* `JdbcTemplate` can be autowired and will be thread safe now (it used to not be?)
* `@Repository` does exception handling and translation
    * lots of checked assumptions are converted to checked exceptions
* Don't annotate interfaces (unless you're doing SOAP lol)

```java
@Override
public long count() {
    return jdbcTemplate.queryForObject( // convert SQL to a class
        "select count(*) from officers", Long.class);
}
```

## Tests
* Cool Hamcrest matchers `containsInAnyOrder`
* "Hamcrest" is an anagram of "matchers"

# JPA Table / Row Configs
* Again, oh god just use rails. Is this really what we have to do?
* Hibernate
```java
@Entity
@Table(name = "officers") // Model Officer, table "officers"
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Rank rank;

    @Column(nullable = false, name = "first_name") // why? terrible idea.
    private String first;

    @Column(nullable = false, name = "last_name")
    private String last;
```

* More stuff about how it "used to be"

```java
@Repository
public class JpaOfficerDAO implements OfficerDAO { // NOOOOOOOOOO
    @PersistenceContext // does lots of magic with connection pool, sql
    private EntityManager entityManager; // can now use the API

    @Autowired
    private JdbcTemplate template; // use this to get the ids, not manage it?
    // "It is not uncommon to use both" Yuck.
}
```