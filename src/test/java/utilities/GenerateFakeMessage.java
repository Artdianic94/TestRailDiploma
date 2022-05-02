package utilities;

import com.github.javafaker.Faker;

public class GenerateFakeMessage {
    public static String getProjectName() {
        Faker faker = new Faker();
        return faker.funnyName().name();
    }
    public static int getAnyNumber(){
        Faker faker = new Faker();
        return faker.number().numberBetween(1,10);
    }
    public static String getTitle(){
        Faker faker = new Faker();
        return faker.app().name();
    }
    public static String getEstimate(){
        Faker faker = new Faker();
        return String.valueOf(faker.number().numberBetween(10, 60));
    }
    public static String getReferences(){
        Faker faker = new Faker();
        return faker.space().galaxy();
    }
    public static String getPreconditions(){
        Faker faker = new Faker();
        return faker.backToTheFuture().quote();
    }
    public static String getSteps(){
        Faker faker = new Faker();
        return faker.shakespeare().kingRichardIIIQuote();
    }
    public static String getExpectedResult(){
        Faker faker = new Faker();
        return faker.backToTheFuture().quote();
    }
}
