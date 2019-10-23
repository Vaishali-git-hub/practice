package testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Wheather {
    @BeforeClass
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        String baseURI = "https://samples.openweathermap.org/data/2.5/weather?id=cityID&appid=b6907d289e10d714a6e88b30761fae22";
        driver.get(baseURI);
        driver.close();
    }
    @Test
    public void GetWeatherDetails()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response1 = httpRequest.request(Method.GET, "/Hyderabad");
        Response response = httpRequest.request(Method.GET, "/Bidar");
       // Response response2 = httpRequest.request(Method.GET,"2172797");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }
     @Test
    public void getTestData()
    {
        Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?id=cityID&appid=b6907d289e10d714a6e88b30761fae22");
        int code = response.getStatusCode();
        System.out.println("The Status code is" +code);
        Assert.assertEquals(code,200);
        String data =response.asString();
        System.out.println("The data is" +data);
    }
}

