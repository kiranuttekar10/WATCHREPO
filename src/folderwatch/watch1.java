package folderwatch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
public class watch1 {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         try 
         {
        	
        	 //new watchservice 
        	 WatchService watcher=FileSystems.getDefault().newWatchService();
             
        	 //getting the directory for which watchservice is to be registered
        	 Path dir=Paths.get("G:\\KIRAN");
        	 
        	 //registering the directory for watchservice and event moitoring
             dir.register(watcher,ENTRY_CREATE);
             
             
             System.out.println("Watch Service registered for dir: " + dir.getFileName());
             
             
             //loop
             while(true)
             {
            	 WatchKey key;
            	
            	 try
            	 {
            		 
            		//retrieve events in the key 
            		 key=watcher.take();
            	 }catch(InterruptedException ex) {
                     return;
                 }
            	
            	 
            	 
            	 for(WatchEvent<?> event:key.pollEvents())
            	 {
            		 //getting the kind of event
            		 WatchEvent.Kind<?> kind=event.kind();
            		 
            		 @SuppressWarnings("unchecked")
            		 //getting the path  of the event for filename
            		 WatchEvent<Path> ev=(WatchEvent<Path>)event;
            		 Path filename=ev.context();
            		 
            		 System.out.println(kind.name()+"  "+filename);
            		 
            	 }
            	 
            	 //reseting the key for retrieving new events
            	 boolean valid = key.reset();
                 if (!valid) {
                     break;
                 }
             }
             
         }catch(IOException ex)
         {
        	 System.out.println(ex);
         }
	}

}
