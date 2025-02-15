# simplifying-ai-complexity
Source Code that supports "Simplify AI Complexity" presentation 

## Demo v3 - GenAI - RAG

### How to run?

Prerequisites:
1. Go to Docker folder
2. Run docker compose to start Elasticsearch & Kibana
```
$ docker compose -f docker-compose.yaml up -d
```
3. Wait couple of minutes to Kibana become available
4. Go to: `http://localhost:5601` (username: elastic, password: password)

All System Properties (e.g. OPENAI_API_KEY) are set in `.env` file. It will be loaded by `spring-dot-env` dependency.

Make sure that you run application with Spring Profiles Active set to `dev`.

Or run from CMD line with
```

$ ./gradlew bootRun

```

### How to Demo?

1. Go to `Postman` there is Collection with name - `SimplifyAIComplexity` > `demo-3`
