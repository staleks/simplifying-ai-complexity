# simplifying-ai-complexity
Source Code that supports "Simplify AI Complexity" presentation 

## Demo v2 - GenAI - Image generation

### How to run?

All System Properties (e.g. OPENAI_API_KEY) are set in `.env` file. It will be loaded by `spring-dot-env` dependency.

Make sure that you run application with Spring Profiles Active set to `dev`.

Or run from CMD line with
```

$ ./gradlew bootRun -Dspring.profiles.active=dev

```

### How to Demo?

1. Go to `Postman` there is Collection with name - `SimplifyAIComplexity` > `demo-2`
2. Image Model is set to: `DALL-E-3`.
3. Post a Image Prompt:

```
{
    "prompt": "Generate an ultra-realistic, highly detailed, high-definition photograph. It's a photo of a boy sitting 
    on the floor. He's looking at the horizon. The photo is taken from the bottom up, at ground level. The boy's 
    sneakers are in the foreground, in the center of the scene. The boy is wearing jersey, a t-shirt and short shorts. 
    The sky is blue. Bright environment. The background is a blurred image of a park."
}
```
OpenAI blocked this prompt?

```
Caused by: dev.ai4j.openai4j.OpenAiHttpException: {
  "error": {
    "code": "content_policy_violation",
    "message": "This request has been blocked by our content filters.",
    "param": null,
    "type": "invalid_request_error"
  }
}
```


I've reused ChatGPT to generate expected prompt:
```
{
    "prompt": "A young boy sits alone on an outdoor basketball court, gazing up at the vast blue sky. He wears a 
    slightly oversized jersey and sneakers, his basketball resting beside him. The court's surface is slightly worn, 
    with faded lines and a hoop standing tall in the background. The sun casts soft shadows, creating a peaceful, 
    contemplative atmosphere. The sky is a brilliant shade of blue with wispy clouds drifting across. 
    The scene evokes a sense of dreams, hope, and quiet ambition."
}

```
Now more complex is image of older man with marbles and dog
```
{
    "prompt": "Generate an image that portrays a highly detailed and surreal scene of an elderly man crouched 
    down on the ground. He is playing marbles with intense focus. His facial expression is stern and weathered, 
    showing deep wrinkles and he is wearing a warm scarf arround his neck. His oversized hands are highlited in 
    the image, grasping a marble with meticulous attention to detail. Beside a man there is also a black dog with 
    similarly exaggarated features appears to watch the marbles closely, amlost mimicking the man's posture. 
    The dog's expression is both curious and alert. Scattered in front of them on the ground are several brightly 
    colored marbles, each rendered with reflective surfaces that contrast the muted tones of the figures. 
    The entire composition evokes sense of nostalgia, blending themes of childhood innocence with weight of age. 
    The disproportionate size of the hands and the intense gaze add a layer of surrealism, 
    creating captivating experience."
}
```
Finally something that is more localized - (Portorož/Slovenia)

```
{
    "prompt": "A spacious and elegantly designed conference hall in an exclusive hotel in Portorož, Slovenia, 
    filled to 75% capacity with a diverse audience attentively watching a presentation at the front. 
    The hall features modern architecture with warm lighting, luxurious seating, and high ceilings adorned 
    with chandeliers or sleek contemporary fixtures. Large windows or subtle coastal design elements reflect 
    the Adriatic influence. The audience, dressed in business-casual and formal attire, appears engaged—some 
    taking notes, others nodding in interest. A professional presenter stands at the front near a large 
    screen displaying a presentation. The atmosphere is dynamic, capturing a moment of insightful discussion 
    in a high-end corporate or academic event."
}    
```

