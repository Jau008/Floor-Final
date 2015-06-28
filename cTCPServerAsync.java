import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class cTCPServerAsync {

    public cTCPServerAsync()
    {
    	System.out.println( "TCP Server Started");
        try
        {
        	//Asynchronous Socket set on port 80000
            final AsynchronousServerSocketChannel listener =
                    AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8000));

            // Listen for a new request
            listener.accept( null, new CompletionHandler<AsynchronousSocketChannel,Void>() {

                @Override
                public void completed(AsynchronousSocketChannel ch, Void att)
                {
                    // Accept the next connection
                    listener.accept( null, this );

                    // Allocate a byte buffer (4K) to read from the client
                    ByteBuffer byteBuffer = ByteBuffer.allocate( 4096 );
                    try
                    {
                        //Read the first line
                        int bytesRead = ch.read( byteBuffer ).get( 20, TimeUnit.SECONDS );

                        boolean running = true;
                        while( bytesRead != -1 && running )
                        {
                            System.out.println( "bytes read: " + bytesRead );

                            //Make sure that we have data to read
                            if( byteBuffer.position() > 2 )
                            {
                                //Make the buffer ready to read
                                byteBuffer.flip();

                                // Convert the buffer into a line
                                byte[] lineBytes = new byte[ bytesRead ];
                                byteBuffer.get( lineBytes, 0, bytesRead );

                                //Create DataManager, then give data communication to the DataManager and set it to TCP server
                                cDataManager cDM = new cDataManager(lineBytes, cEnums.SocketType.TCP);
                                cDM.SaveRecord();
                                
                                //DataManager, returns the result in binary. cUtils convert it to Hex in this case for Debug purpose
                                String sLine = new String(cUtils.bytesToHex(cDM.getID()));
                                System.out.println( "\nID: " + sLine );
                                sLine = new String(cUtils.bytesToHex(cDM.getValue()));
                                System.out.println( "Value: " + sLine);
                                
                                byteBuffer.clear();
                                bytesRead = ch.read( byteBuffer ).get( 20, TimeUnit.SECONDS );
                            }
                            else
                            {
                                //No info, end of communication
                                running = false;
                            }
                        }
                    }
                    catch (InterruptedException e)
                    {
                       e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    catch (TimeoutException e)
                    {
                        //20 seconds Timeout
                        System.out.println( "Time out, closing connection" );
                    }

                    System.out.println( "End of communication" );
                    try
                    {
                        // Close the connection if we need to
                        if( ch.isOpen() )
                        {
                            ch.close();
                        }
                    }
                    catch (IOException e1)
                    {
                         e1.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void att) {
                    //On Error do something
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
    	cTCPServerAsync server = new cTCPServerAsync();
        try
        {
            Thread.sleep( 60000 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
