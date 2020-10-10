# BoozDb

Have you always wanted a single, definitive, source of truth of all the hard liquors available on the market?  

Your wish has come true. BoozDb brings a user friendly api to the web for anyone that is interested 
the distilled spirits market offerings.  

BoozDb can be used in lifestyle, social media, and mixoligy references. And so on, and so forth. 

## About this project
A practice project using [Dropwizard](https://www.dropwizard.io/en/latest/), which "pulls together stable, 
mature libraries from the Java ecosystem into a simple, light-weight package that lets you focus on getting things done." 
Core libraries in Dropwizard are Jetty (for webserver), Jackson (for JSON), and Jersey (for JAX-RS). 

Features I rolled  
1. Hypermedia as the Engine of Application State (HATEOAS)
   - Single bottle responses contain links to 'self' as well as searches for bottles the same category, 
   price, age, and proof.
   - Bottle lists responses contain links for pagination ('next' and 'back'), and an empty list contains links to
   various searches that may be of interest to the user. 
1. Pagination controls 
   - Caller can control page size and page number; default page size is 25. 

How to start the Main application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/boozedb-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`
