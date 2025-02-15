# simplifying-ai-complexity
Source Code that supports "Simplify AI Complexity" presentation 

## How to run?

All System Properties (e.g. OPENAI_API_KEY) are set in `.env` file. It will be loaded by `spring-dot-env` dependency.

Make sure that you run application with Spring Profiles Active set to `dev`.

Or run from CMD line with
```

$ ./gradlew bootRun -Dspring.profiles.active=dev

```

## How to Demo?

1. Go to `Postman` there is Collection with name - `SimplifyAIComplexity` > `demo-1`
2. As default Chat Model is set to: `GPT-3.5-turbo` post question:
```
{
    "question": "Who won FIFA WorldCup in 2022?"
}
```
3. It is trained to September 2021. Therefore, it can't provide the answer.
4. Switch to other models: `GPT-4o` or `Claude Haiku V3` to get answer to this question.



