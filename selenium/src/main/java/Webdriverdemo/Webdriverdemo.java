package Webdriverdemo;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;


public class Webdriverdemo {

    private WebDriver driver;

    // Este método se ejecuta antes de cada prueba
    @Before
    public void setUp() {
        // Configuración inicial
        driver = new ChromeDriver();
        // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Launch website
        driver.navigate().to("http://www.calculator.net/");
        // Maximize the browser
        driver.manage().window().maximize();
        // Click on Math Calculators
        driver.findElement(By.xpath("//*[@id=\"homelistwrap\"]/div[3]/div[2]/a")).click();
        
    }
    
    @Test
    public void testPositivos() {
        // Lógica de la prueba
    	// Click on Percent Calculators
        driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[3]/a")).click();
        // Enter value 10 in the first number of the percent Calculator
        driver.findElement(By.id("cpar1")).sendKeys("10");
        // Enter value 50 in the second number of the percent Calculator
        driver.findElement(By.id("cpar2")).sendKeys("50");
        // Click Calculate Button
        driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/table/tbody/tr[2]/td/input[2]")).click();
        // Get the Result Text based on its xpath
        String result = driver.findElement(By.xpath(".//*[@id = 'content']/p[2]/font/b")).getText();
        // Assert actual value
        assertEquals(result, "5");
    }

    @Test
    public void testNegativos() {
        // Lógica de la prueba
    	// Click on Percent Calculators
        driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[3]/a")).click();
        // Enter value 10 in the first number of the percent Calculator
        driver.findElement(By.id("cpar1")).sendKeys("10");
        // Enter value -50 in the second number of the percent Calculator
        driver.findElement(By.id("cpar2")).sendKeys("-50");
        // Click Calculate Button
        driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/table/tbody/tr[2]/td/input[2]")).click();
        // Get the Result Text based on its xpath
        String result = driver.findElement(By.xpath(".//*[@id = 'content']/p[2]/font/b")).getText();
        // Assert actual value
        assertEquals(result, "5");
    }
    
    @Test
    public void testCampoVacio() {
    	// Click on Percent Calculators
        driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[3]/a")).click();
        // Prueba si la aplicación maneja correctamente campos vacíos
        driver.findElement(By.id("cpar1")).sendKeys("");
        driver.findElement(By.id("cpar2")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/table/tbody/tr[2]/td/input[2]")).click();
        WebElement errorElement = driver.findElement(By.xpath("//*[@id='content']/p[2]/font"));
        assertTrue(errorElement.isDisplayed());
        assertEquals("Please enter a valid number", errorElement.getText());
    }

    @Test
    public void testNavegacionAtras() {
    	// Click on Percent Calculators
        driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[3]/a")).click();
        // Prueba si la aplicación maneja correctamente la navegación hacia atrás
        driver.navigate().back();
        String title = driver.getTitle();
        assertEquals("Math Calculators", title);
    }
    // Cambiamos de porcentaje a generar numeros ramdon
    @Test
    public void testnumRandom() {
	      
	    // Click on Random Numbers
	    driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[4]/a")).click();
	    // Click Calculate Button
	    driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/table/tbody/tr/td/table/tbody/tr[3]/td/input[3]")).click();
	    // Get the Result Text based on its xpath
	    String result =
	       driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]")).getText();
	    // Assert actual value
	    assertEquals(result, "5");
    }

    // Este método se ejecuta después de cada prueba
    @After
    public void tearDown() {
        // Limpieza posterior
        if (driver != null) {
            driver.quit();
        }
    }
}




