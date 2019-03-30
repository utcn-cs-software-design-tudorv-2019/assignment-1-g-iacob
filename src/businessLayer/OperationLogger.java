package businessLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class OperationLogger {
	
	HashMap<Integer, ArrayList<String>> log = new HashMap<Integer, ArrayList<String>>();
	 
	private void serializeLog() {
		try {
			FileOutputStream file = new FileOutputStream("studentsLog");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(log);
			out.close(); 
            file.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace()[0].getLineNumber());
		}
	}
	
	private void deserializeLog() {
		try {
			FileInputStream file = new FileInputStream("studentsLog");
			ObjectInputStream in = new ObjectInputStream(file);
			log = (HashMap<Integer, ArrayList<String>>)in.readObject();
			in.close(); 
            file.close();
		} catch (Exception e) {
			System.out.println(e);
			log = new HashMap<Integer, ArrayList<String>>();
		}
	}
	
    public void logAction(Integer id, String action) {
        ArrayList<String> actionList = log.get(id);
        if (actionList == null){
            actionList = new ArrayList<String>();
            actionList.add(action);
            log.put(id, actionList);
        } else {
            actionList.add(action);
        }
        serializeLog();
    }
    
    public HashMap<Integer, ArrayList<String>> getLog() {
    	deserializeLog();
    	return log;
    }
    
    public void reset() {
    	log = new HashMap<Integer, ArrayList<String>>();
    	serializeLog();
    }
}
