package config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
public class MongoConfig {

    @Primary
    @Bean(name = "mongoTemplateUniversity")
    public MongoTemplate mongoTemplateUniversity(MongoDbFactory universityFactory) {
        return new MongoTemplate(universityFactory);
    }

    @Bean(name = "mongoTemplateSecond")
    public MongoTemplate mongoTemplateSecond(MongoDbFactory secondFactory) {
        return new MongoTemplate(secondFactory);
    }

    @Primary
    @Bean
    public MongoDbFactory universityFactory(MongoClient mongoClient) {
        return (MongoDbFactory) new SimpleMongoClientDbFactory("mongodb://root:example@localhost:27017/university");
    }

}
