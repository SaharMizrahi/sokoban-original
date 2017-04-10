package db;

import java.util.List;

public interface DBManagerInterface
{
	public void addRecord(Object o);
	public void deleteRecord(Object o);
	public List<Record> showAllRecords();
	public List<Record> showLevelRecords(int levelID);
	


}
