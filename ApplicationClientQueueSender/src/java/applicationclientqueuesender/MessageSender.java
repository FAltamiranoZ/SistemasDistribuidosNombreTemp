/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationclientqueuesender;

/**
 *
 * @author JGUTIERRGARC
 */
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageSender{  
    
    
    
    @Resource(mappedName = "jms/__defaultConnectionFactory") 
    private static ConnectionFactory connectionFactory;  
    
    

    public void produceMessages()  {    
        MessageProducer messageProducer;    
        TextMessage textMessage;    
        try    {      
            Connection connection = connectionFactory.createConnection();      
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);      
                            
            Queue myQueue = session.createQueue("queue1");
            messageProducer = session.createProducer(myQueue);      
            textMessage = session.createTextMessage();      

            textMessage.setText("Testing, 1, 2, 3. Can you hear me?");      
            System.out.println("Sending the following message: "        + textMessage.getText());      
            messageProducer.send(textMessage);      

            textMessage.setText("Do you copy?");      
            System.out.println("Sending the following message: "        + textMessage.getText());      
            messageProducer.send(textMessage);      
            
            textMessage.setText("Good bye!");      
            System.out.println("Sending the following message: "        + textMessage.getText());      
            messageProducer.send(textMessage);      
            
            messageProducer.close();      
            session.close();      
            connection.close();    
        }    catch (JMSException e)    
        {      
            e.printStackTrace();    
        }  
    }  
    
    
    public static void main(String[] args)  {    
        new MessageSender().produceMessages();  
    }
}