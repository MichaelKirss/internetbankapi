package org.skill.internetbankapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
   info = @Info(
           title = "Internet Bank Api",
           description = "Bank accounts and operation System",
           version = "1.0.0",
           contact = @Contact(
                  name = "Michael Kirss",
                  email = "i@mkirss.ru"
           )
   )
)

public class OpenApiConfig {
}
