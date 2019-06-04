package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	WebDriver driver;
	public Utilities(WebDriver driver){
		this.driver = driver;
	}

	public void screenshot(String Name) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File("src//Screenshots//Screen"+Name+".jpeg");
		 FileUtils.copyFile(SrcFile, DestFile);
	}
}

