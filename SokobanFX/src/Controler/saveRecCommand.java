package Controler;

import db.DBManager;
import db.Record;
import db.RecordsKey;

public class saveRecCommand extends FunctionalCommand implements Command
{
	public void execute()
	{
		DBManager dm=new DBManager();
		String []s=this.getStr().split(" ");
		Record rec=new Record(new RecordsKey(s[1],Integer.parseInt(s[2])),Integer.parseInt(s[4]),Integer.parseInt(s[3]));
		dm.addRecord(rec);
		
		
	}


}
