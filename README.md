## Reactive programming demo

* #### How to run?
    For the best experience import with IntelliJ.
    
    Execute in this order:
    1. Run `isp.example.service.ReactiveServiceApplication` - A Service application 
    2. Run `isp.example.client.ReactiveClientApplication` - A Spring backend client application
    3. In order to start reactive front-end Angular application (requires node and npm):
        * Run `cd reactive-fron`
        * Run `npm install`
        * Run `npm start`
    4. **Observe logs of all the applications!**

* #### What is inside?
    * ReactiveServiceApplication contains `localhost:8080` reactive endpoints:
        * `/event` - an infinite stream of events
        * `/event{id}` - get a specific event by id
        * `/users` - get a collection of all users
        * `/users{id}` - get a specific user by id
        * `/users/count` - get count of all users
        * `/users/by_name/{name}` - get a collection of users with a specified name
    * ReactiveClientApplication is a command line Spring application that uses Spring's new 
    [_reactive web client_](http://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html)
    to read events from the Service's `/events` endpoint.
    * Reactive Angular client under `localhost:3000`
        * As of today, RsJs has some problems with SSE (Server Side Events). See: https://github.com/ReactiveX/rxjs/issues/1644
        * TypeScript has no EventSource definition. See: https://github.com/Microsoft/TypeScript/issues/13666
        * **Temporary workaround**: using pure JavaScript EventSource implementation.

* #### Used in this project:
    * [Spring Boot 2](http://docs.spring.io/spring-boot/docs/2.0.0.M1/reference/htmlsingle/)
    * [Spring 5](https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework)
    * [Project Reactor](https://projectreactor.io/)
    * [Angular 2](https://angular.io/docs/ts/latest/quickstart.html)
    * [RxJs](https://github.com/Reactive-Extensions/RxJS)

* #### More examples of operators in `src/test/java`

* #### Also worth checking:
    * https://github.com/reactor/lite-rx-api-hands-on

* #### To do:
    * Consuming events with TypeScript and RxJs
