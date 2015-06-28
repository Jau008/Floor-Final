import java.io.PrintStream;
import java.net.Socket;
		
	public class cFloorClient {
	    public static void main( String[] args )
	    {
	        if( args.length > 2 )
	        {
	            System.out.println( "Usage: Floor Client <port> <hex>" ); //8000 3D0000330121000000005623000102033000
	            System.exit( 0 );
	        }
	        String sPort = args[ 0 ];
	        String sHex = args[ 1 ];
	      
		        try
		        {
		            //Connect to the server
		            Socket socket = new Socket( "localhost", Integer.valueOf(sPort));
		           
		            //Create Stream for output
		            PrintStream out = new PrintStream( socket.getOutputStream() );
		            
		            //Write into the socket a binary representation of the Hex string in order to simulate a floor element.
		            out.write(cUtils.hexStringToByteArray(sHex)) ;
	
		            //Close when done
		            out.close();
		            socket.close();
		        }
		        catch( Exception e )
		        {
		            e.printStackTrace();
		        }
	        
	    }

	}	
	
	

