package io.voteofconfidence.meeting.controller.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class CalendlyOauth2 {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/calendly_oauth2/callback")
    @ResponseBody
    void calendlyOauth2Callback(@RequestParam String code) {
        log.info("the Authorization Code for calendly is {}", code);
    }
}
