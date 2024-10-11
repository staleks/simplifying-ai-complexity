package com.jatheon.ergo.ai.assistant.service.image;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class OpenAIImageService implements ImageService {

    private final ImageModel model;

    @Override
    public String generateImage(final String prompt) {
        Response<Image> response = model.generate(prompt);
        return response.content().url().toString();
    }
}
