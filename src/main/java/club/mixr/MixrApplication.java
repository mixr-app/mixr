package club.mixr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan(
        basePackageClasses = {MixrApplication.class, Jsr310JpaConverters.class}
)
@EnableJpaAuditing(modifyOnCreate = false)
@SpringBootApplication
public class MixrApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MixrApplication.class, args);
    }
}
