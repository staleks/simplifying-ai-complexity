package com.jatheon.ergo.ai.assistant.endpoint.image;

import com.jatheon.ergo.ai.assistant.model.image.ImageRequest;
import com.jatheon.ergo.ai.assistant.model.image.ImageResponse;
import com.jatheon.ergo.ai.assistant.service.error.ImageServiceException;
import com.jatheon.ergo.ai.assistant.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    private static final String IMAGE_ENDPOINT = "/ai/get-image";

    private final ImageService imageService;

    @PostMapping(value = IMAGE_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageResponse> generateImage(@RequestBody ImageRequest request) {
        if (StringUtils.isNotBlank(request.getPrompt())) {
            try {
                return ResponseEntity.ok(
                        ImageResponse.builder().answer(imageService.generateImage(request.getPrompt())).build());
            } catch (ImageServiceException qse) {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
