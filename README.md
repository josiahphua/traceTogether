Welcome to trace together. My meek attempt at building a full-stack app.

1. 
```
docker run --name trace_together_db -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
```

2. 
```
./gradlew clean build
```
3. 
```
./gradlew bootRun
```

The plan for this includes, building microservices, setting up a spring boot API aggregation service,
a blog of which I can document my learning, and lastly, to deploy this somewhere
so that my wife and I can actually use this any time we'd like to. 