
import java.io.File;
import org.apache.commons.mail.HtmlEmail;

/**
 * Sample send email with image and hiperlink.
 * For more samples in : http://commons.apache.org/proper/commons-email/userguide.html
 * 
 * @author Roberto Silva
 * criado em: 07/04/2014
 */
public class Principal {
	
	public static void main(String[] args) {		
		try {
			 // Create the email message
			  HtmlEmail email = new HtmlEmail();
			  
			  //show log
			  email.setDebug(true);
			  
			  //set port
			  email.setSmtpPort(587);  
			  //check this rules for security
			  email.setStartTLSEnabled(true);
			  //email.setSSLCheckServerIdentity(true);
			
			  email.setHostName("smtp.live.com");//server SMTP
			  email.setAuthentication("yourEmail@hotmail.com", "yourPassword");
			  email.setFrom("yourEmail@hotmail.com", "yourName");
			  
			  //destiny's
			  email.addTo("emailDest1", "NameDest1");
			  email.addTo("emailDest2", "NameDest2");
			  email.addTo("emailDest3", "NameDest3");
			  
			  email.setSubject("Test Send Email with Image");
			  
			  //put the format to recognize the image,
			  //the reason for not accepting all image formats
			  String cid = email.embed(new File("./java.jpg"));
			  
			  //html text
			  String txtHtml=
					  "<html>"
							  + "Hello,<br><br>"
							  + "This one test for send email with image,<br>"
							  + "thanks for cooperate.<br><br>"
							  + "<a href=\"http://solutionbto.wordpress.com/\">"
							  + "<img src=\"cid:"+cid+"\">"
							  + "</a>"
							  + "</html>";
			  	  
			  // set the html message
			  email.setHtmlMsg(txtHtml);
			  // send the email
			  email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
