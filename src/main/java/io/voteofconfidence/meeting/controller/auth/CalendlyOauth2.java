package io.voteofconfidence.meeting.controller.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class CalendlyOauth2 {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/calendly_oauth2/callback")
    void calendlyOauth2Callback(@PathVariable String code) {
        log.info("the Authorization Code for calendly is {}", code);
    }
}
