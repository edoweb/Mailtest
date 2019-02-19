/*******************************************************************************
 * Copyright 2019 Jan Schnasse
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Jan Schnasse
 *
 */
public class Mailtest {
	public static void send() {
		try {
			Properties properties = new Properties();
    		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
			properties.forEach((k,v)->{System.out.println(k+" : "+v);});
    					Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(properties.getProperty("sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("recipient")));
			message.setSubject(properties.getProperty("subject"), "ISO-8859-1");
			message.setText(readFile(properties.getProperty("mailText")), "UTF-8");
			message.setSentDate(new Date());
			Transport.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String readFile(String filePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] vargs) {
		Mailtest.send();
	}
}
