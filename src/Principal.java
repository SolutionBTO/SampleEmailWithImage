
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
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
			
			File file1=new File("./Commons Email - Examples.pdf");
			List<File> files=new ArrayList<File>();
			files.add(file1);
			
			if(files.size() > 0){
				//sending one or more attachments
				EmailAttachment attachment = new EmailAttachment(); 
				attachment.setDisposition(EmailAttachment.ATTACHMENT);

				//for each in files
				for(File fileIn:files){
					if(fileIn != null && fileIn.isFile()){
						int indexOfExtension=fileIn.getName().indexOf(".");
						indexOfExtension=indexOfExtension == -1 ? 0 :indexOfExtension;
						attachment.setDescription(fileIn.getName().substring(indexOfExtension));//extenção
						attachment.setName(fileIn.getName());	  
						attachment.setURL(fileIn.toURI().toURL());//set file local, convert in URL
						email.attach(attachment);
					}
				}
			}
			
			//show log
			email.setDebug(true);
			//set port
			email.setSmtpPort(587);  
			//check this rules for security
			email.setStartTLSEnabled(true);
			//email.setSSLCheckServerIdentity(true);

			email.setHostName("smtp.live.com");//server SMTP for Hotmail, alter other
			email.setAuthentication("yourEmail@hotmail.com", "yourPassword");
			email.setFrom("yourEmail@hotmail.com", "yourName");

			//destiny's
			email.addTo("yourDestinyEmail1", "yourDestinyName1");
			email.addTo("yourDestinyEmail2", "yourDestinyName2");
			email.addTo("yourDestinyEmail3", "yourDestinyName3");

			email.setSubject("Test email html with image and file attachment");

			//put the format to recognize the image,
			//the reason for not accepting all image formats
			String cid = email.embed(new File("./Apache_logo.gif"));

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
