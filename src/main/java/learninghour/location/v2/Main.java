package learninghour.location.v2;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        LocationService locationService = (LocationService) context.getBean("locationService");

        System.out.println("Result: " + locationService.getLocation("88.196.236.95"));
    }

    @Configuration
    static class Config {

        @Bean
        public CloseableHttpClient closeableHttpClient() {
            return HttpClients.createDefault();
        }

        @Bean
        public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            var propertySource = new PropertySourcesPlaceholderConfigurer();
            var properties = new Properties();
            properties.setProperty("dbip.api.key", "free");
            propertySource.setProperties(properties);
            return propertySource;
        }

        @Bean
        public LocationService locationService() {
            return new LocationService();
        }
    }
}
