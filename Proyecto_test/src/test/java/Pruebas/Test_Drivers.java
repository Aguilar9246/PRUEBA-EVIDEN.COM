package Pruebas;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Test_Drivers {
	WebDriver driver;
    int screenshotCounter = 1;
	
	@Before
	public void driverChrome() {
		System.setProperty("webdriver.chrome.driver", "..\\Proyecto_test\\Drivers\\chromedriver.exe");
		
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
	}
	
	
	@Test
	    public void pruebaEviden() throws InterruptedException, IOException {
        //ENTRAR EN LA PAGINA DE EVIDEN
		driver.get("https://www.eviden.com/");
		takeScreenshot("1_homepage");
		
		//CLICK ABOUT US
		WebElement aboutUsLink = driver.findElement(By.xpath("//a[text()='About Us']"));
		aboutUsLink.click();
		takeScreenshot("2_aboutUs");
		
		// DESPLAZARSE HASTA ABAJO
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// CLICK CONTACT US
		Thread.sleep(2000);
		WebElement contactUsLink = driver.findElement(By.xpath("//a[text()='Contact Us']"));
		contactUsLink.click();
		takeScreenshot("3_contactUs");
		
		//RELLENAR EL FORMULARIO
		
		//SELECCIONAR SELECT
        WebElement selectElement = driver.findElement(By.id("enquiryType"));
        Select select = new Select(selectElement);
        select.selectByValue("New solution/New Business");
		     
		//ACEPTAR COOKIES
        Thread.sleep(3000);
        acceptCookies();       
        
		//TEXTO
		WebElement textAreaInput = driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']/textarea[@id='enquiry']"));
		textAreaInput.sendKeys("Buenas, escribo esto para preguntar por la disposición de trabajo en Sevilla");
		
		//NOMBRE
		WebElement nameInput = driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']/input[@id='FirstName']"));
		nameInput.sendKeys("Alejandro");
        
		//APELLIDO
		WebElement lastNameInput = driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']/input[@id='LastName']"));
		lastNameInput.sendKeys("Nogueras");
        
		//EMAIL
		WebElement emailInput = driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']/input[@id='Email']"));
		emailInput.sendKeys("alejandro.aguilar.external@eviden.com");
       
        //COMPAÑIA
     	WebElement companyInput = driver.findElement(By.xpath("//div[@class='mktoFieldWrap mktoRequiredField']/input[@id='Company']"));
     	companyInput.sendKeys("Eviden");
		takeScreenshot("4_formulario");
        
        //PAIS
        WebElement pais = driver.findElement(By.xpath("//select[@id='Country']"));
        pais.click();
        WebElement opcion = driver.findElement(By.xpath("//select[@id='Country']/option[@value='Spain']"));
        opcion.click();
        Thread.sleep(2000);
		takeScreenshot("5_pais");
        
       	//HACER CLICK SECTORES
		WebElement sectoresLink = driver.findElement(By.xpath("//a[@title='Industries']"));
		sectoresLink.click();
        Thread.sleep(2000);
		takeScreenshot("6_industries(sectores)");
        
        
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,500);");
        Thread.sleep(500);
        
        //HACER CLICK EN LEARN MORE
		WebElement energyLink = driver.findElement(By.xpath("//div[@class='serText']//h3[contains(text(),'Ener')]/../div//a[@href]"));
		energyLink.click();
        Thread.sleep(2000);
		takeScreenshot("7_energy");
        
        
	    //HACER CLICK EN CON QUIEN TRABAJAMOS
	    WebElement workLink = driver.findElement(By.xpath("//div[@class='swiper-slide swiper-slide-next']//a[@href='#who-we-work-with']"));
	    workLink.click();
	    Thread.sleep(2000);
		takeScreenshot("8_workIt");
	    
	    //HACER CLICK EN EL LOGO DE EVIDEN
	    js.executeScript("window.scrollTo(0, 0)");
	    Thread.sleep(2000);
	    WebElement evidenLink = driver.findElement(By.xpath("//header//a[@href='https://eviden.com/']"));
	    evidenLink.click();
	    Thread.sleep(2000);
		takeScreenshot("9_eviden");
	    
       	//HACER CLICK SOLUTIONS
		WebElement solucionesLink = driver.findElement(By.xpath("//header//a[@href='https://eviden.com/solutions/']"));
		solucionesLink.click();
        Thread.sleep(2000);
		takeScreenshot("10_eviden");
              
	    //HACER CLICK EN DIGITAL SECURITY
	    WebElement digitalLink = driver.findElement(By.xpath("//div[@class='swiper-slide']//a[@href='#secondary-menu-target-3']"));
	    digitalLink.click();
	    Thread.sleep(2000);
		takeScreenshot("11_security");
	    
        //HACER CLICK EN LEARN MORE DE DIGITAL SECURITY
		WebElement digitalMoreLink = driver.findElement(By.xpath("//div[@class='serText']//h3[contains(text(),'Secur')]/../div/a"));
		digitalMoreLink.click();
        Thread.sleep(2000);
		takeScreenshot("12_securityMore");
        
	}	
	
	@After
	public void tearDown() {
        driver.quit();
    }
	
	
    private void takeScreenshot(String name) {
        try {
            // TOMAR CAPTURA
            File screenshot = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
            // FECHA
            LocalDateTime now = LocalDateTime.now();
            // TIPO DE FECHA
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = now.format(formatter);
            // NOMBRE DE LA CAPTURA
            String filename = "screenshot_" + name + "_" + formattedDateTime + ".png";
            // RUTA COMPLETA DE GUARDADO
            String filePath = "C:\\Users\\aleag\\OneDrive\\Escritorio\\Capturas\\" + filename;
            // GUARDAR RUTA
            File destFile = new File(filePath);
            ImageIO.write(ImageIO.read(screenshot), "png", destFile);
            // IMPRIMIR RUTA DE ARCHIVO
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void acceptCookies() {
        try {
            WebElement cookiesButton = driver.findElement(By.xpath("//button[text()='Accept All']"));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            // SI NO SE MUESTRA EL BOTON, SEGUIR CON LA EJECUCION
            System.out.println("El mensaje de aceptar cookies no está presente. Continuando con la ejecución...");
        }
    }
    
}
