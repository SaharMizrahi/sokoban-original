package db;

import java.util.List;

public interface DBManagerInterface
{
	public void addRecord(Object o);
	public void deleteRecord(Object o);
	public Object findRecord(String username,int levelID);

	public List<Record> showAllRecords();
	public List<Record> showLevelRecords(int levelID);
	public List<Record> showRecordsByMember(String username);
	public List<Record> showRecordsByLevelID(int levelID);

	
	public boolean isMemberExist(String username);
	public boolean isLevelExist(int levelID);
	public boolean isRecordExist(String username,int levelID);

}
