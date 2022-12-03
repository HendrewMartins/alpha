package org.alpha.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags ={
        @Tag(name = "alpha",description = "API")
    },
    info = @Info(
        title = "Alpha",
        version = "1.0",
        contact = @Contact(
            name = "Zanlo",
            email = "teste@teste.com"
        ),
        license = @License(
            name="MIT",
            url = "https://opensource.org/licenses/MIT"
        )
    )
)

public class SwaggerConfig extends Application{


    
}
