package io.voteofconfidence.meeting.controller;

import io.minio.PutObjectArgs;
import io.voteofconfidence.meeting.service.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("test")
public class SimpleController {

    @Autowired
    FileUploader fileUploader;

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getBook() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(
                    "Sphinx of black quartz, judge my vow: Used by Adobe InDesign to display font samples. ");
            builder.append("(29 letters)\n");
            builder.append(
                    "Jackdaws love my big sphinx of quartz: Similarly, used by Windows XP for some fonts. ");
            builder.append("(31 letters)\n");
            builder.append(
                    "Pack my box with five dozen liquor jugs: According to Wikipedia, this one is used on ");
            builder.append("NASAs Space Shuttle. (32 letters)\n");
            builder.append(
                    "The quick onyx goblin jumps over the lazy dwarf: Flavor text from an Unhinged Magic Card. ");
            builder.append("(39 letters)\n");
            builder.append(
                    "How razorback-jumping frogs can level six piqued gymnasts!: Not going to win any brevity ");
            builder.append("awards at 49 letters long, but old-time Mac users may recognize it.\n");
            builder.append(
                    "Cozy lummox gives smart squid who asks for job pen: A 41-letter tester sentence for Mac ");
            builder.append("computers after System 7.\n");
            builder.append(
                    "A few others we like: Amazingly few discotheques provide jukeboxes; Now fax quiz Jack! my ");
            builder.append("brave ghost pled; Watch Jeopardy!, Alex Trebeks fun TV quiz game.\n");
            builder.append("---\n");
        }

        {
            // Create a InputStream for object upload.
            ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

            // Create object 'my-objectname' in 'my-bucketname' with content from the input stream.
            fileUploader.putObject(
                    PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
                            bais, bais.available(), -1)
                            .build());
            bais.close();
            System.out.println("my-objectname is uploaded successfully");
        }

    }
}
