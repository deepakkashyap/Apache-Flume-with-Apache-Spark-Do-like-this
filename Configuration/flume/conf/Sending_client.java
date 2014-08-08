import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;
public class Send 
{
public static void main(String[] arg) throws Exception
{   
	   String host="localhost";
	   int port=44444;  //port where your source will listen in flume agent
	   RpcClient client=RpcClientFactory.getDefaultInstance(host, port);
	
	   String message ="Your message";
	 while (!Thread.currentThread ().isInterrupted ())
	   {     
		 
	   Event event = EventBuilder.withBody(message.getBytes());
	   
	   try 
	       {
		      client.append(event);
		    }
	   catch (EventDeliveryException e)
	      {
		      client.close();
		      client = null;
		      client = RpcClientFactory.getDefaultInstance(host, port);
		    }
	   }
	 
	 
	   client.close();
}
}
