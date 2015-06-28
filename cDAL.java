import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;

public class cDAL {
	
	private static DB db;
	
	 public static void main( String args[] ){
	      try{   
	    	  boolean x;
	    	  ConnectToDB();
	    	  x=UpdateRecord();
	    	  
		  }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }
	

	 public static void ConnectToDB(){
	      try{
	    	  
    	  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    	  db = mongoClient.getDB( "test" );
	    	  
	    	  
	 	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }


	public static boolean UpdateRecord(){
/*        DBCollection coll = db.getCollection("floor3");

        DBObject query = new BasicDBObject("customId", "3D000033012100000000562300010203");
        DBObject update = new BasicDBObject();
        update.put("$set", new BasicDBObject("data_temp","4000"));
  */       
        return true;
     }
	
}
